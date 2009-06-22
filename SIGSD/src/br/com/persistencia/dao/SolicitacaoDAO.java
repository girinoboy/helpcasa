package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ConstantesENUM;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class SolicitacaoDAO extends GenericDAO{

	protected static final String strConsultListaPorIdCliente = "SELECT servico.nome as nomeServico, solicitacao.data, solicitacao.periodo,pessoa.idPessoa as idFuncionario, pessoa.cep, solicitacao.ocupado, pessoa.nome as nomeFuncionario, idPessoa as total, solicitacao.idSolicitacao,precoVisita " +
			"FROM solicitacao " +			
			"INNER JOIN funcionario ON solicitacao.idFuncionario = funcionario.idFuncionario " +
			"INNER JOIN profissao ON profissao.idprofissao = funcionario.idprofissao " +
			" INNER JOIN servico ON profissao.idProfissao = servico.idProfissao " +
			"INNER JOIN pessoa on pessoa.idPessoa = funcionario.idFuncionario " ;
			
	
	protected static final String strConsultFaturaBasica = "SELECT v.nome as nomeServico, p.nome as 'Profissional',s.data,s.periodo,p.idPessoa as idFuncionario, s.ocupado, p.nome as nomeFuncionario,p.cep, SUM(a.valor) as 'Total', s.idSolicitacao,precoVisita " +
			"FROM solicitacao s " +
			"INNER JOIN cliente c ON c.idCliente = s.idCliente " +
			"INNER JOIN funcionario f ON f.idFuncionario = s.idFuncionario " +
			"INNER JOIN profissao ON profissao.idprofissao = f.idprofissao " +
			"INNER JOIN pessoa p ON f.idFuncionario = p.idPessoa " +
			"INNER JOIN servico v ON v.idServico = s.idServico " +
			"LEFT JOIN adicional a ON a.idSolicitacao = s.idSolicitacao ";
		
	protected static final String strConsultHorariosDisponiveis = "SELECT servico.nome as nomeServico,periodo,solicitacao.data,idPessoa as idFuncionario,cep,ocupado, pessoa.nome as nomeFuncionario,idPessoa as total, solicitacao.idSolicitacao,precoVisita " +
			"FROM solicitacao " +
			"RIGHT JOIN pessoa ON idpessoa=idfuncionario " +
			"INNER JOIN funcionario on funcionario.idfuncionario = pessoa.idpessoa " +
			"INNER JOIN profissao ON profissao.idprofissao = funcionario.idprofissao " +
			"INNER JOIN servico ON servico.idprofissao = profissao.idprofissao";
			


	protected static final String strConsultFaturaDetalhada = "SELECT sum(valor)+precoVisita as total,precoVisita,adicional.descricao,adicional.data,valor, observacao, solicitacao.idSolicitacao,servico.nome as nomeServico,periodo,solicitacao.data,idPessoa as idFuncionario,cep,ocupado, pessoa.nome as nomeFuncionario,pessoa.idPessoa,precoVisita " +
			"	FROM adicional " +
			"	inner join solicitacao on solicitacao.idSolicitacao = adicional.idSolicitacao " +
			"	inner join funcionario on solicitacao.idFuncionario = funcionario.idFuncionario " +
			"	INNER JOIN pessoa  ON funcionario.idFuncionario = pessoa.idPessoa " +
			"	inner join profissao on profissao.idProfissao = funcionario.idProfissao " +
			"	INNER JOIN servico ON servico.idprofissao = profissao.idprofissao ";

	public void inclui(SolicitacaoDTO solicitacao, Connection conn)
			throws Exception {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;

		String sql = "INSERT INTO solicitacao(data,periodo,idCliente,idFuncionario,idServico,statusAtual) VALUES(?,?,?,?,?,?)";
		String sql2 = "UPDATE pessoa SET cep = ? WHERE idPessoa=?";//updade para o local atual do profissional para fazer a consulta do google api
		
		String sql4 ="INSERT INTO historico SET data=now(),status=1,alteradoPor=?,observacao='solicitado pelo cliente',idSolicitacao=(select max(idSolicitacao) from solicitacao)";
		try {

			ps = conn.prepareStatement(sql);
			ps.setDate(1, new Date (solicitacao.getData().getTime()));
			ps.setInt(2, solicitacao.getPeriodo());
			ps.setLong(3, solicitacao.getCliente().getId());
			ps.setLong(4, solicitacao.getFuncionario().getId());			
			ps.setDouble(5, solicitacao.getServico().getId());
			ps.setLong(6, ConstantesENUM.STATUS_SOLICITADO.id());
			
			ps.executeUpdate();
			//set o novo endere�o do funcionario como o cep 70390-130 da empresa
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, solicitacao.getCliente().getCep());
			ps2.setLong(2, solicitacao.getFuncionario().getId());
			
			ps2.executeUpdate();				
			
			//atualiza o historico
			ps4 =conn.prepareStatement(sql4);
			ps4.setLong(1, solicitacao.getCliente().getId());
			ps4.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (ps2 != null)
				ps2.close();
		}

	}

	public List<SolicitacaoDTO> consultarFaturaBasica(Long idCliente,
			Connection conn) throws Exception {
		List<SolicitacaoDTO> list =null;
		SolicitacaoDTO solicitacaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsultFaturaBasica);
		qBuffer.append(" WHERE s.idCliente = ?");
		qBuffer.append(" group by a.valor");
			
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1, idCliente);
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs,dto);				
				
				list.add(solicitacaoDTO);
				
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return list;
	}

	private SolicitacaoDTO populaSolicitacaoDTO(SolicitacaoDTO dto, ResultSet rs, SolicitacaoDTO solicitacao)  throws SQLException {
		dto.setId(rs.getLong("idSolicitacao"));
		Date data = rs.getDate("data");
		
		dto.setData(data);
		if(data != null && (solicitacao.getData().getTime()==data.getTime()))
			dto.setPeriodo(rs.getInt("periodo"));
		else
			dto.setPeriodo(0);
		dto.setTotal(rs.getDouble("total"));
		dto.setOcupado(rs.getBoolean("ocupado"));
		
		FuncionarioDTO funcionario = new FuncionarioDTO();
		funcionario.setId(rs.getLong("idFuncionario"));
		funcionario.setNome(rs.getString("nomeFuncionario"));
		funcionario.setCep(rs.getString("cep"));		
		dto.setFuncionario(funcionario);
		
		ProfissaoDTO profissao = new ProfissaoDTO();/*
		profissao.setId(rs.getLong("idProfissao"));
		profissao.setNome(rs.getString("nomeProfissao"));
		profissao.setDescricao(rs.getString("descricaoProfissao"));*/
		profissao.setPrecoVisita(rs.getDouble("precoVisita"));		
		funcionario.setProfissao(profissao);
		
		ServicoDTO servico = new ServicoDTO();
		servico.setNome(rs.getString("nomeServico"));
		dto.setServico(servico);
		//servico.setDescricao(rs.getString("descricaoServico"));
		/*
		ProfissaoDTO profissao = new ProfissaoDTO();
		profissao.setId(rs.getLong("idProfissao"));
		profissao.setNome(rs.getString("nomeProfissao"));
		profissao.setPrecoVisita(rs.getDouble("precovisita"));		
		profissao.setDescricao(rs.getString("descricaoProfissao"));
		dto.setProfissaoDTO(profissao);*/
		
		return dto;
	}

	public List<SolicitacaoDTO> consultarFaturaDetalhada(Long idSolicitacao,
			Connection conn) throws Exception {
		List<SolicitacaoDTO> list =null;
		SolicitacaoDTO solicitacaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsultFaturaDetalhada);
		qBuffer.append(" WHERE solicitacao.idSolicitacao=?");
					
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1, idSolicitacao);
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs,dto);				
				
				list.add(solicitacaoDTO);
				
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return list;
	}

	public List<SolicitacaoDTO> horariosDisponiveisListar(
			SolicitacaoDTO solicitacao, Connection conn) throws Exception {
		List<SolicitacaoDTO> list =null;
		SolicitacaoDTO solicitacaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append("SELECT servico.nome as nomeServico, solicitacao.data, solicitacao.periodo,pessoa.idPessoa as idFuncionario, pessoa.cep, solicitacao.ocupado, pessoa.nome as nomeFuncionario, idPessoa as total, solicitacao.idSolicitacao,precoVisita ");
		qBuffer.append(" FROM pessoa ");
		qBuffer.append(" left join solicitacao on idPessoa = idFuncionario ");
		qBuffer.append(" left JOIN servico ON solicitacao.idServico = servico.idServico ");
		qBuffer.append(" left JOIN funcionario ON solicitacao.idFuncionario = funcionario.idFuncionario ");
		qBuffer.append(" left JOIN profissao ON profissao.idprofissao = funcionario.idprofissao ");
		qBuffer.append(" WHERE idPessoa not in(select idFuncionario FROM solicitacao WHERE (? <= DATE_ADD(NOW(),INTERVAL 30 DAY)) AND data = ? AND ocupado =1  AND idservico =? )"); 
		qBuffer.append(" AND idPerfil = 4 AND pessoa.ativo = 1");
		qBuffer.append(" and (? <= DATE_ADD(NOW(),INTERVAL 30 DAY))"); 
		qBuffer.append(" AND ? >= now()");
		qBuffer.append(" and ocupado =0");

		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setDate(1, new Date(solicitacao.getData().getTime()));
			ps.setDate(2, new Date(solicitacao.getData().getTime()));			
			ps.setLong(3, solicitacao.getServico().getId());
			ps.setDate(4, new Date(solicitacao.getData().getTime()));
			ps.setDate(5, new Date(solicitacao.getData().getTime()));
			
			System.out.println(qBuffer.toString());
			
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs,solicitacao);				
				
				list.add(solicitacaoDTO);
				
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return list;
	}

	private SolicitacaoDTO populaHorarios(SolicitacaoDTO dto, ResultSet rs) {
	//	dto.setId(id);
		return dto;
	}

	public Boolean existeSolicitacao(SolicitacaoDTO solicitacao, Connection conn) throws Exception {
		Boolean ocupado = new Boolean(false);		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append("SELECT * FROM solicitacao ");
		qBuffer.append(" INNER JOIN servico ON servico.idservico=solicitacao.idservico ");
		qBuffer.append(" WHERE data =? ");
		qBuffer.append(" AND ocupado =1 ");
		qBuffer.append(" AND solicitacao.idServico =? ");
		qBuffer.append(" AND idFuncionario=? ");
		qBuffer.append(" AND statusAtual<>? ");
		
		
	//	System.out.println("Existe Solicitacao: "+qBuffer.toString());
		StringBuffer qBuffer2 = new StringBuffer();	
		qBuffer2.append("UPDATE solicitacao SET ocupado = 1 where data = ? and idFuncionario=? and idServico=?");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setDate(1, new Date(solicitacao.getData().getTime()));
			ps.setLong(2, solicitacao.getServico().getId());
			ps.setLong(3, solicitacao.getFuncionario().getId());
			ps.setLong(4, ConstantesENUM.STATUS_SOLICITADO.id());
			
		//	System.out.println(qBuffer.toString());
			rs = ps.executeQuery();
			
			Double total;
			if(rs.next()){								
					
				ps2 = conn.prepareStatement(qBuffer2.toString());
				ps2.setDate(1, new Date(solicitacao.getData().getTime()));
				ps.setLong(2, solicitacao.getFuncionario().getId());
				ps.setLong(3, solicitacao.getServico().getId());
				int periodo = rs.getInt("periodo");
				if(rs.next() || periodo ==3){
					ps2.executeUpdate();
					ocupado = true;
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(ps2!=null)
				ps2.close();
			if(rs!=null)
				rs.close();
		}
		
		return ocupado;		
		
	}

	public List<SolicitacaoDTO> solicitacaoListar(Long id, Connection conn) throws Exception {
		List<SolicitacaoDTO> list =null;
		SolicitacaoDTO solicitacaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsultListaPorIdCliente);
		qBuffer.append(" WHERE solicitacao.idCliente = ?");
		qBuffer.append(" AND solicitacao.statusAtual = ?");
		qBuffer.append(" AND servico.idservico = solicitacao.idservico");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1, id);
			ps.setLong(2, ConstantesENUM.STATUS_SOLICITADO.id());
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs,dto);				
				
				list.add(solicitacaoDTO);
				
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return list;
	}

	public void cancela(Long[] idsSolicitacao, Long alteradoPor, Connection conn) throws Exception {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		String sql="UPDATE solicitacao SET statusAtual=? where idSolicitacao=?";
		String sql2 = "INSERT INTO historico(data,status,alteradoPor,observacao,idSolicitacao)VALUES(now(),?,?,?,?)";
		
		try{
			for (Long id : idsSolicitacao) {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, ConstantesENUM.STATUS_CANCELADO.id());
				ps.setLong(2, id);
				ps.executeUpdate();
				
				ps2 = conn.prepareStatement(sql2);
				ps2.setLong(1, ConstantesENUM.STATUS_CANCELADO.id());
				ps2.setLong(2, alteradoPor);
				ps2.setString(3, "Cancelado");
				ps2.setLong(4,id);
				
				ps2.executeUpdate();
			}

		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
		}
		
	}

}
