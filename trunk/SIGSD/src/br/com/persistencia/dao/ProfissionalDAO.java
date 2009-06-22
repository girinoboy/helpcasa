package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ConstantesENUM;
import br.com.persistencia.dto.AdicionalDTO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ProfissionalDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class ProfissionalDAO extends GenericDAO{

	protected static final String strConsult ="SELECT servico.nome AS nomeServico,periodo,pessoa.nome AS nomeCliente,endereco,cep,telefone,statusAtual,solicitacao.idSolicitacao,solicitacao.data,precoVisita" +
			"			FROM solicitacao" +
			"			INNER JOIN servico ON servico.idServico = solicitacao.idServico" +
			"			INNER JOIN cliente ON cliente.idCliente = solicitacao.idCliente" +
			"			INNER JOIN pessoa ON pessoa.idPessoa = cliente.idCliente" +
			"      INNER JOIN funcionario ON funcionario.idFuncionario = solicitacao.idFuncionario" +
			"      INNER JOIN profissao ON profissao.idProfissao = funcionario.idProfissao" ;
			//"      INNER JOIN historico ON historico.idSolicitacao = solicitacao.idSolicitacao ";
	
	protected static final String strConsultAdicionais ="SELECT * FROM adicional";
	
	public List<HistoricoDTO> consultarAgenda(Date data, Long idFuncionario, Connection conn) throws Exception {
		List<HistoricoDTO> list =null;
		HistoricoDTO historicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE solicitacao.data = ?");
		qBuffer.append(" AND solicitacao.idFuncionario = ?");
		qBuffer.append(" AND statusAtual = ?");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setTimestamp(1, new Timestamp(data.getTime()));	
			ps.setLong(2, idFuncionario);
			ps.setLong(3, ConstantesENUM.STATUS_SOLICITADO.id());
			
			rs = ps.executeQuery();
			list = new ArrayList<HistoricoDTO>();
			while(rs.next()){
				HistoricoDTO dto = new HistoricoDTO();
				
				historicoDTO = this.populaHistoricoDTO(dto,rs);
				//historicoDTO = (HistoricoDTO) DTOFactory.getDTO(HistoricoDTO.class, rs);
				
				list.add(historicoDTO);
				
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
	private SolicitacaoDTO populaSolicitacaoDTO(SolicitacaoDTO dto, ResultSet rs) throws SQLException {
		dto.setId(rs.getLong("idSolicitacao"));
		dto.setPeriodo(rs.getInt("periodo"));
		
		ServicoDTO servico = new ServicoDTO();
		servico.setNome(rs.getString("nomeServico"));
		dto.setServico(servico);
		
		ClienteDTO cliente = new ClienteDTO();
		cliente.setNome(rs.getString("nomeCliente"));
		cliente.setEndereco(rs.getString("endereco"));
		cliente.setCep(rs.getString("cep"));
		cliente.setTelefone(rs.getString("telefone"));
		dto.setCliente(cliente );
		
		
		return dto;
	}
	public List<AdicionalDTO> consultarAgendaDetalhada(Long idSolicitacao,	Connection conn) throws Exception {
		List<AdicionalDTO> list =null;
		AdicionalDTO adicionaisDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsultAdicionais);
		qBuffer.append(" WHERE idSolicitacao = ?");		
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());			
			ps.setLong(1, idSolicitacao);
			
			rs = ps.executeQuery();
			list = new ArrayList<AdicionalDTO>();
			while(rs.next()){
				AdicionalDTO dto = new AdicionalDTO();
				
				adicionaisDTO = this.populaAdicionaisDTO(dto,rs);
				//servicoDTO = (ServicoDTO) DTOFactory.getDTO(ServicoDTO.class, rs);
				
				list.add(adicionaisDTO);
				
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
	private AdicionalDTO populaAdicionaisDTO(AdicionalDTO dto, ResultSet rs) throws Exception {
		dto.setId(rs.getLong("idAdicional"));
		dto.setDescricao(rs.getString("descricao"));
		dto.setValor(rs.getDouble("valor"));
		dto.setObservacao(rs.getString("observacao"));
		dto.setData(rs.getTimestamp("data"));
		return dto;
	}
	public HistoricoDTO consultarHistoricoAgendaDetalhada(Long idSolicitacao, Connection conn) throws Exception {		
		HistoricoDTO historicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE solicitacao.idSolicitacao = ?");
		
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());			
			ps.setLong(1, idSolicitacao);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				HistoricoDTO dto = new HistoricoDTO();
				
				historicoDTO = this.populaHistoricoDTO(dto,rs);
															
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return historicoDTO;
	}
	
	private HistoricoDTO populaHistoricoDTO(HistoricoDTO dto, ResultSet rs) throws SQLException {
		
		dto.setStatus(rs.getInt("statusAtual"));
		SolicitacaoDTO solicitacao = new SolicitacaoDTO();
		solicitacao.setId(rs.getLong("idSolicitacao"));
		solicitacao.setPeriodo(rs.getInt("periodo"));
		solicitacao.setData(rs.getDate("data"));
		solicitacao.setStatusAtual(rs.getLong("statusAtual"));
		
		ServicoDTO servico = new ServicoDTO();
		servico.setNome(rs.getString("nomeServico"));
		solicitacao.setServico(servico);
		
		FuncionarioDTO funcionario = new FuncionarioDTO();
		ProfissaoDTO profissao = new ProfissaoDTO();
		profissao.setPrecoVisita(rs.getDouble("precoVisita"));
		funcionario.setProfissao(profissao);
		solicitacao.setFuncionario(funcionario);
		
		ClienteDTO cliente = new ClienteDTO();
		cliente.setNome(rs.getString("nomeCliente"));
		cliente.setEndereco(rs.getString("endereco"));
		cliente.setCep(rs.getString("cep"));
		cliente.setTelefone(rs.getString("telefone"));
		solicitacao.setCliente(cliente);
		
		dto.setSolicitacao(solicitacao);
		return dto;
	}
	
	public void finalizarServico(Long idSolicitacao, Long alteradoPor, Connection conn) throws Exception {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;

		String sql = "INSERT INTO historico(data,status,alteradoPor,observacao,idSolicitacao)VALUES(now(),?,?,?,?)";
		
		String sql2 ="UPDATE solicitacao SET statusAtual=? where idSolicitacao=?";
		try {

			ps = conn.prepareStatement(sql);
			ps.setLong(1, ConstantesENUM.STATUS_FINALIZADO.id());
			ps.setLong(2, alteradoPor);
			ps.setString(3, "Finalizado");
			ps.setLong(4,idSolicitacao);
			
			ps.executeUpdate();
			
			ps2 = conn.prepareStatement(sql2);
			ps2.setLong(1, ConstantesENUM.STATUS_FINALIZADO.id());
			ps2.setLong(1, idSolicitacao);
			ps2.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
	}

}
