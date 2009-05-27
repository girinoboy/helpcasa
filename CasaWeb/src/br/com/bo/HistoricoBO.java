package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.HistoricoDAO;
import br.com.persistencia.dao.NotasDAO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.NotaDTO;
import br.com.persistencia.dto.ServicoDTO;

public class HistoricoBO extends GenericBO{
	
	private HistoricoDAO historicoDAO;
	private NotasDAO notasDAO;

	public HistoricoBO() {
		historicoDAO = FactoryDAO.getInstance().getHistoricoDAO();
		notasDAO = FactoryDAO.getInstance().getNotasDAO();
	}

	public void aplicaClassificacao(Long id, Long idNota) throws Exception {
		Connection con = Conexao.getConnection();
		
		try {
			this.historicoDAO.aplicaClassificacao(id,idNota, con);
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
		
	}

	public List<HistoricoDTO> historicoListar(Long idCliente) throws Exception {
		Connection conn = Conexao.getConnection();
		List<HistoricoDTO> list = null;
		try{
			list = historicoDAO.historicoListar(idCliente,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public List<NotaDTO> consultarNotas() throws Exception {
		Connection conn = Conexao.getConnection();
		List<NotaDTO> list = null;
		try{
			list = notasDAO.consultarNotas(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public List<HistoricoDTO> consultarHistorico(Long idSolicitacao) throws Exception {
		Connection conn = Conexao.getConnection();
		List<HistoricoDTO> list = null;
		try{
			list = historicoDAO.consultarHistorico(idSolicitacao,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}
	
	
	

}
