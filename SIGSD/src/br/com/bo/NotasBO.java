package br.com.bo;

import java.sql.Connection;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.NotasDAO;
import br.com.persistencia.dto.NotaDTO;

public class NotasBO extends GenericBO {

	private NotasDAO notasDAO;

	public NotasBO() {
		notasDAO = FactoryDAO.getInstance().getNotasDAO();
	}

	public List<NotaDTO> consultarNotas() throws Exception {
		Connection conn = Conexao.getConnection();
		List<NotaDTO> list = null;
		try {
			list = notasDAO.consultarNotas(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}

		return list;
	}

}
