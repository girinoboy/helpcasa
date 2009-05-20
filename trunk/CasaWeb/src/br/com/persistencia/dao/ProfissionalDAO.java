package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class ProfissionalDAO extends GenericDAO{

	protected static final String strConsult ="SELECT servico.nome AS nomeServico,periodo,pessoa.nome AS nomeCliente,endereco,cep,telefone,status " +
			"FROM casaweb.solicitacao " +
			"INNER JOIN casaweb.servico ON servico.idServico = solicitacao.idServico " +
			"INNER JOIN casaweb.cliente ON cliente.idCliente = solicitacao.idCliente " +
			"INNER JOIN casaweb.pessoa ON pessoa.idPessoa = cliente.idCliente " +
			"INNER JOIN casaweb.historico ON historico.idSolicitacao = solicitacao.idSolicitacao ";
	
	public List<SolicitacaoDTO> consultarAgenda(Date data, Long idFuncionario, Connection conn) throws Exception {
		List<SolicitacaoDTO> list =null;
		SolicitacaoDTO solicitacaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE solicitacao.data = ?");
		qBuffer.append(" AND idFuncionario = ?");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setTimestamp(1, new Timestamp(data.getTime()));	
			ps.setLong(1, idFuncionario);
			
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
		ServicoDTO servico = new ServicoDTO();
		servico.setNome(rs.getString("nomeSerico"));
		dto.setServico(servico);
		dto.setPeriodo(rs.getInt("periodo"));
		
		ClienteDTO cliente = new ClienteDTO();
		cliente.setNome(rs.getString("nomeCliente"));
		cliente.setEndereco(rs.getString("endereco"));
		cliente.setCep(rs.getString("cep"));
		cliente.setTelefone(rs.getString("telefone"));
		dto.setCliente(cliente );
		
		
		return dto;
	}

}
