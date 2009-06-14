package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.AdicionaisDAO;
import br.com.persistencia.dto.AdicionalDTO;
import br.com.persistencia.dto.ServicoDTO;

public class AdicionaisBO extends GenericBO {

	private AdicionaisDAO adicionaisDAO;

	public AdicionaisBO() {
		adicionaisDAO = FactoryDAO.getInstance().getAdicionaisDAO();
	}

	public void inclui(AdicionalDTO adicionaisDTO) throws Exception {
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
	
	public List<AdicionalDTO> adicionalListar(Long idSolicitacao) throws Exception {
		Connection conn = Conexao.getConnection();
		List<AdicionalDTO> list = null;
		try{
			list = adicionaisDAO.adicionalListar(idSolicitacao,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

}
