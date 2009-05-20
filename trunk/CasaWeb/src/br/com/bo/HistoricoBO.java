package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.HistoricoDAO;
import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.ServicoDTO;

public class HistoricoBO extends GenericBO{
	
	private HistoricoDAO historicoDAO;

	public HistoricoBO() {
		historicoDAO = FactoryDAO.getInstance().getHistoricoDAO();
	}

	public void aplicaClassificacao(Long id, Long idRespondavelClassificar) throws Exception {
		Connection con = Conexao.getConnection();
		
		try {
			this.historicoDAO.aplicaClassificacao(id,idRespondavelClassificar, con);
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
