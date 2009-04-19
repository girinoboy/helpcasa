package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.ProfissaoDAO;
import br.com.persistencia.dto.ProfissaoDTO;

public class ProfissaoBO extends GenericBO{

	private ProfissaoDAO profissaoDAO;
	public ProfissaoBO() {
		profissaoDAO = FactoryDAO.getInstance().getProfissaoDAO();
	}

	public List<ProfissaoDTO> profissaoListar() throws Exception {
		Connection conn = Conexao.getConnection();
		List<ProfissaoDTO> list = null;
		try{
			list = profissaoDAO.profissaoListar(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public void inclui(ProfissaoDTO profissaoDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		
		try{
			profissaoDAO.inclui(profissaoDTO,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

	public void exclui(Long[] idsProfissao) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			profissaoDAO.exclui(idsProfissao,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
	}

}
