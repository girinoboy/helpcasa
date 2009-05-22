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
import br.com.persistencia.dto.AdicionaisDTO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.ProfissionalDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class ProfissionalDAO extends GenericDAO{

	protected static final String strConsult ="SELECT servico.nome AS nomeServico,periodo,pessoa.nome AS nomeCliente,endereco,cep,telefone,status,solicitacao.idSolicitacao " +
			"FROM casaweb.solicitacao " +
			"INNER JOIN casaweb.servico ON servico.idServico = solicitacao.idServico " +
			"INNER JOIN casaweb.cliente ON cliente.idCliente = solicitacao.idCliente " +
			"INNER JOIN casaweb.pessoa ON pessoa.idPessoa = cliente.idCliente " +
			"INNER JOIN casaweb.historico ON historico.idSolicitacao = solicitacao.idSolicitacao ";
	
	protected static final String strConsultAdicionais ="SELECT * FROM adicionais";
	
	public List<SolicitacaoDTO> consultarAgenda(Date data, Long idFuncionario, Connection conn) throws Exception {
		List<SolicitacaoDTO> list =null;
		SolicitacaoDTO solicitacaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE solicitacao.data = ?");
		qBuffer.append(" AND idFuncionario = ?");
		qBuffer.append(" AND status = ?");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setTimestamp(1, new Timestamp(data.getTime()));	
			ps.setLong(2, idFuncionario);
			ps.setLong(2, ConstantesENUM.STATUS_SOLICITADO.id());
			
			rs = ps.executeQuery();
			list = new ArrayList<SolicitacaoDTO>();
			while(rs.next()){
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs);
				//servicoDTO = (ServicoDTO) DTOFactory.getDTO(ServicoDTO.class, rs);
				
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
	public List<AdicionaisDTO> consultarAgendaDetalhada(Long idSolicitacao,	Connection conn) throws Exception {
		List<AdicionaisDTO> list =null;
		AdicionaisDTO adicionaisDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsultAdicionais);
		qBuffer.append(" WHERE idSolicitacao = ?");		
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());			
			ps.setLong(1, idSolicitacao);
			
			rs = ps.executeQuery();
			list = new ArrayList<AdicionaisDTO>();
			while(rs.next()){
				AdicionaisDTO dto = new AdicionaisDTO();
				
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
	private AdicionaisDTO populaAdicionaisDTO(AdicionaisDTO dto, ResultSet rs) throws Exception {
		dto.setId(rs.getLong("idAdicionais"));
		dto.setDescricao(rs.getString("descricao"));
		dto.setValor(rs.getDouble("valor"));
		dto.setObservacao(rs.getString("observacao"));
		dto.setData(rs.getTimestamp("data"));
		return dto;
	}
	public SolicitacaoDTO consultarSolicitacaoAgendaDetalhada(Long idSolicitacao, Connection conn) throws Exception {		
		SolicitacaoDTO solicitacaoDTO = null;
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
				SolicitacaoDTO dto = new SolicitacaoDTO();
				
				solicitacaoDTO = this.populaSolicitacaoDTO(dto,rs);
															
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return solicitacaoDTO;
	}
	public void finalizarServico(Long idSolicitacao, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql = "INSERT INTO casaweb.historico(data,status,perfil,alteradoPor,observacao,idSolicitacao)VALUES(now(),?,?,?,?,?)";
		try {

			ps = conn.prepareStatement(sql);
			ps.setLong(1, ConstantesENUM.STATUS_FINALIZADO.id());
			ps.setString(2, getSessaoPessoa().getPerfil().getId()+"-"+getSessaoPessoa().getPerfil().getDescricao());
			ps.setString(3, getSessaoPessoa().getId()+"-"+getSessaoPessoa().getNome());
			ps.setString(4,null);
			ps.setLong(5,idSolicitacao);
			
			ps.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
	}

}
