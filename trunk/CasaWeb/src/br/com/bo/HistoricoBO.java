package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.dao.HistoricoDAO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.ServicoDTO;

public class HistoricoBO extends GenericBO{
	
	private HistoricoDAO historicoDAO;

	public HistoricoBO() {
		// TODO Auto-generated constructor stub
	}

	public void concedeDesistencia(Long id, Long idRespondavelClassificar) throws Exception {
		Connection con = getConnection();
		
		try {
			this.historicoDAO.concedeDesistencia(id,idRespondavelClassificar, con);
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
		
	}

	public List<HistoricoDTO> historicoListar() throws Exception {
		Connection conn = Conexao.getConnection();
		List<HistoricoDTO> list = null;
		try{
			list = historicoDAO.historicoListar(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}
	
	
	

}
