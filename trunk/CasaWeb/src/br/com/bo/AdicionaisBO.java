package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.AdicionaisDAO;
import br.com.persistencia.dto.AdicionaisDTO;

public class AdicionaisBO extends GenericBO {

	private AdicionaisDAO adicionaisDAO;

	public AdicionaisBO() {
		adicionaisDAO = FactoryDAO.getInstance().getAdicionaisDAO();
	}

	public void inclui(AdicionaisDTO adicionaisDTO) throws Exception {
		Connection conn = Conexao.getConnection();

		try {
			adicionaisDAO.inclui(adicionaisDTO, conn);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}

	}

	public void exclui(Long[] idsAdicional) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			adicionaisDAO.exclui(idsAdicional,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

}
