package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class SolicitacaoDAO extends GenericDAO{

	protected static final String strConsult = "";
	
	protected static final String strConsultFaturaBasica = "SELECT v.nome as 'Serviço', p.nome as 'Profissional', SUM(a.valor) as 'Total'FROM casaweb.solicitacao s " +
			"INNER JOIN casaweb.cliente c ON c.idCliente = s.idCliente " +
			"INNER JOIN casaweb.funcionario f ON f.idFuncionario = s.idFuncionario " +
			"INNER JOIN casaweb.pessoa p ON f.idFuncionario = p.idPessoa " +
			"INNER JOIN casaweb.Servico v ON v.idServico = s.idServico " +
			"INNER JOIN casaweb.adicionais a ON a.idSolicitacao = s.idSolicitacao ";
		
	protected static final String strConsultHorariosDisponiveis = "SELECT periodo,idPessoa as idFuncionario,cep FROM casaweb.solicitacao " +
			"RIGHT JOIN casaweb.pessoa ON idpessoa=idfuncionario " +
			"INNER JOIN casaweb.funcionario on funcionario.idfuncionario = pessoa.idpessoa " +
			"INNER JOIN casaweb.profissao ON profissao.idprofissao = funcionario.idprofissao " +
			"INNER JOIN casaweb.servico ON servico.idprofissao = profissao.idprofissao";

	public void inclui(SolicitacaoDTO solicitacao, Connection conn)
			throws Exception {
		PreparedStatement ps = null;

		String sql = "INSERT INTO casaweb.Solicitacao(data,periodo,idCliente,idFuncionario,idServico) VALUES(now(),?,?,?,?,?)";
		try {

			ps = conn.prepareStatement(sql);
			ps.setDate(1, new Date (solicitacao.getData().getTime()));
			ps.setInt(2, solicitacao.getPeriodo());
			ps.setLong(3, solicitacao.getCliente().getId());
			ps.setLong(4, solicitacao.getFuncionario().getId());
			ps.setDouble(5, solicitacao.getServico().getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
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
		qBuffer.append(" WHERE idPessoa = ?");
		qBuffer.append(" group by a.valor");
			
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1, idCliente);
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs);				
				
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

	private SolicitacaoDTO populaSolicitacaoDTO(SolicitacaoDTO dto, ResultSet rs)  throws SQLException {
		//dto.setId(rs.getLong("idServico"));
		//dto.setData((Date)rs.getDate("data"));
		dto.setPeriodo(rs.getInt("periodo"));
		
		FuncionarioDTO funcionario = new FuncionarioDTO();
		funcionario.setId(rs.getLong("idFuncionario"));
		//funcionario.setNome(rs.getString("nomeFuncionario"));
		funcionario.setCep(rs.getString("cep"));
		dto.setFuncionario(funcionario);
		/*
		ProfissaoDTO profissao = new ProfissaoDTO();
		profissao.setId(rs.getLong("idProfissao"));
		profissao.setNome(rs.getString("nomeProfissao"));
		profissao.setPrecoVisita(rs.getDouble("precoVisita"));
		profissao.setDescricao(rs.getString("descricaoProfissao"));
		funcionario.setProfissao(profissao);
		*/
		/*
		dto.setNome(rs.getString("nomeServico"));
		dto.setDescricao(rs.getString("descricaoServico"));
		
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

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE idSolicitacao = ?");
		
		
		
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1, idSolicitacao);
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs);				
				
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

		qBuffer.append(strConsultHorariosDisponiveis);
		qBuffer.append(" WHERE (? <= DATE_ADD(NOW(),INTERVAL 30 DAY)) ");
		qBuffer.append(" AND servico.idServico =?");
		qBuffer.append(" AND idPerfil = 4");
		qBuffer.append(" AND (periodo IS NULL OR periodo <> 3 OR cep = '70390-130')");//CEP da empresa

		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setDate(1, new Date(solicitacao.getData().getTime()));
			ps.setLong(2, solicitacao.getServico().getId());
			System.out.println(qBuffer.toString());
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs);				
				
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

}
