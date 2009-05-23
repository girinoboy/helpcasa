package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.ProfissionalDAO;
import br.com.persistencia.dto.AdicionaisDTO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.ProfissionalDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class ProfissionalBO extends GenericBO{
	
	ProfissionalDAO profissionalDAO;
	
	public ProfissionalBO() {
		profissionalDAO = FactoryDAO.getInstance().getProfissionalDAO();
	}

	public List<HistoricoDTO> consultarAgenda(Date data, Long idFuncionario) throws Exception {
		Connection conn = Conexao.getConnection();
		List<HistoricoDTO> list = null;
		try{
			list = profissionalDAO.consultarAgenda(data,idFuncionario,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public List<AdicionaisDTO> consultarAgendaDetalhada(Long idSolicitacao) throws Exception {
		Connection conn = Conexao.getConnection();
		List<AdicionaisDTO> list = null;
		try{
			list = profissionalDAO.consultarAgendaDetalhada(idSolicitacao,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public HistoricoDTO consultarHistoricoAgendaDetalhada(Long idSolicitacao) throws Exception {
		Connection conn = Conexao.getConnection();
		HistoricoDTO historicoDTO = null;
		try{
			historicoDTO = profissionalDAO.consultarHistoricoAgendaDetalhada(idSolicitacao,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return historicoDTO;
	}

	public void finalizarServico(Long idSolicitacao) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			profissionalDAO.finalizarServico(idSolicitacao,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

}
