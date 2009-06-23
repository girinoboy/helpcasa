package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ConstantesENUM;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.NotaDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class HistoricoDAO extends GenericDAO{

	private static final String strUpdateClassificacao = "UPDATE solicitacao SET idNota = ? WHERE idSolicitacao = ?";
	
	private static final String strConsult = "SELECT h.idHistorico,sv.nome as nomeServico,h.data,sl.periodo,sl.statusAtual,h.status,p.nome as nomeFuncionario,n.idNota,n.descricao, precoVisita,sl.idSolicitacao, sum(valor)+precovisita as total FROM historico h " +
			"inner join solicitacao sl on sl.idSolicitacao = h.idSolicitacao " +
			"inner join servico sv on sv.idServico = sl.idServico " +
			"inner join funcionario f on sl.idFuncionario = f.idFuncionario " +
			"inner join profissao pr on pr.idProfissao = f.idProfissao "+
			"inner join pessoa p on p.idPessoa = f.idFuncionario " +
			"left  join adicional a on a.idSolicitacao = sl.idSolicitacao "+
			"inner join nota n on n.idnota= sl.idnota";

	public void aplicaClassificacao(Long idSolicitacao, Long idNota,Connection conn) throws Exception {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strUpdateClassificacao);
		//String sql = "INSERT INTO historico(data,status,perfil,alteradoPor,observacao,idSolicitacao)VALUES(now(),?,?,?,?,?)";
		try{

			ps = conn.prepareStatement(qBuffer.toString());	
			ps.setLong(1, idNota);
			ps.setLong(2, idSolicitacao);
			ps.executeUpdate();
			/*
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ConstantesENUM.STATUS_CLASSIFICADO.id());
			ps.setString(2, getSessaoPessoa().getPerfil().getId()+"-"+getSessaoPessoa().getPerfil().getDescricao());
			ps.setString(3, getSessaoPessoa().getId()+"-"+getSessaoPessoa().getNome());
			ps.setString(4,null);
			ps.setLong(5,idSolicitacao);
			
			ps.executeUpdate();*/		
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				throw e;
			}
		}
		
	}

	public List<HistoricoDTO> historicoListar(Long idCliente, Connection conn) throws Exception {
		List<HistoricoDTO> list =null;
		HistoricoDTO historicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);	
		qBuffer.append(" WHERE sl.idCliente=?");	
		qBuffer.append(" group by sl.idSolicitacao");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1, idCliente);
			rs = ps.executeQuery();
			list = new ArrayList<HistoricoDTO>();
			Double total;
			while(rs.next()){
			//	total = rs.getDouble("total");
				//if(total != null && total > 0){
					HistoricoDTO dto = new HistoricoDTO();
					
					historicoDTO = this.populaHistoricoDTO(dto,rs);
					//historicoDTO = (HistoricoDTO) DTOFactory.getDTO(HistoricoDTO.class, rs);
					
					list.add(historicoDTO);
			//	}												
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

	private HistoricoDTO populaHistoricoDTO(HistoricoDTO dto, ResultSet rs) throws Exception {
		dto.setId(rs.getLong("idHistorico"));
		if(rs.getDate("data") !=null )
		dto.setData(new Date(rs.getDate("data").getTime()));
		dto.setTotal(rs.getDouble("total"));
		dto.setStatus(rs.getInt("status"));
		//dto.setPerfil(rs.getString("perfil"));
		//dto.setAuteradoPor(rs.getString("auteradoPor"));
		//dto.setObservacao(rs.getString("observacao"));
		
		SolicitacaoDTO solicitacao = new SolicitacaoDTO();
		
		ServicoDTO servico = new ServicoDTO();
		servico.setNome(rs.getString("nomeServico"));
		
		solicitacao.setId(rs.getLong("idSolicitacao"));
		solicitacao.setServico(servico);
		solicitacao.setPeriodo(rs.getInt("periodo"));
		solicitacao.setStatusAtual(rs.getLong("statusAtual"));
		dto.setSolicitacao(solicitacao);
		
		NotaDTO nota = new NotaDTO();
		nota.setId((rs.getLong("idNota")));
		nota.setDescricao(rs.getString("descricao"));
		
		solicitacao.setNota(nota);
		
		ProfissaoDTO profissao = new ProfissaoDTO();
		profissao.setPrecoVisita(rs.getDouble("precoVisita"));
		
		FuncionarioDTO funcionario = new FuncionarioDTO();
		funcionario.setNome(rs.getString("nomeFuncionario"));
		funcionario.setProfissao(profissao);
		solicitacao.setFuncionario(funcionario);
		
		dto.setSolicitacao(solicitacao);
		return dto;
	}

	public List<HistoricoDTO> consultarHistorico(Long idSolicitacao, Connection conn) throws Exception {
		List<HistoricoDTO> list =null;
		HistoricoDTO historicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE sl.idSolicitacao=?");
		qBuffer.append(" group by h.idHistorico");
	
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1,idSolicitacao);
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

}
