package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.NotaDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class HistoricoDAO extends GenericDAO{

	private static final String strUpdateClassificacao = "";
	
	private static final String strConsult = "SELECT h.idHistorico,sv.nome as nomeServico,h.data,sl.periodo,p.nome as nomeFuncionario,n.idNota,n.descricao, precoVisita, sum(valor)+precovisita as total FROM casaweb.historico h " +
			"inner join casaweb.solicitacao sl on sl.idSolicitacao = h.idSolicitacao " +
			"inner join casaweb.servico sv on sv.idServico = sl.idServico " +
			"inner join casaweb.funcionario f on sl.idFuncionario = f.idFuncionario " +
			"inner join casaweb.profissao pr on pr.idProfissao = f.idProfissao "+
			"inner join casaweb.pessoa p on p.idPessoa = f.idFuncionario " +
			"left join casaweb.adicionais a on a.idSolicitacao = sl.idSolicitacao "+
			"inner join casaweb.nota n on n.idnota= sl.idnota";

	public void aplicaClassificacao(Long id, Long idRespondavelClassificar,
			Connection con) throws Exception {
		PreparedStatement ps = null;
		
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strUpdateClassificacao);

		try{

			ps = con.prepareStatement(qBuffer.toString());	
			ps.setLong(1, idRespondavelClassificar);
			ps.setLong(2, id);
			ps.executeUpdate();
			
		} catch (SQLException sqlE) {
			throw sqlE;
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

	public List<HistoricoDTO> historicoListar(Connection conn) throws Exception {
		List<HistoricoDTO> list =null;
		HistoricoDTO historicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);		
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
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

	private HistoricoDTO populaHistoricoDTO(HistoricoDTO dto, ResultSet rs) throws Exception {
		dto.setId(rs.getLong("idHistorico"));
		dto.setData(new Date(rs.getDate("data").getTime()));
		dto.setTotal(rs.getDouble("total"));
		//dto.setStatus(rs.getInt("status"));
		//dto.setPerfil(rs.getString("perfil"));
		//dto.setAuteradoPor(rs.getString("auteradoPor"));
		//dto.setObservacao(rs.getString("observacao"));
		
		SolicitacaoDTO solicitacao = new SolicitacaoDTO();
		
		ServicoDTO servico = new ServicoDTO();
		servico.setNome(rs.getString("nomeServico"));
		
		solicitacao.setServico(servico);
		solicitacao.setPeriodo(rs.getInt("periodo"));
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

}
