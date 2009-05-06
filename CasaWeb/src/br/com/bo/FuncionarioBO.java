package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.dao.FuncionarioDAO;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.ProfissaoDTO;

public class FuncionarioBO extends GenericBO{

	FuncionarioDAO funcionarioDAO;
	
	public FuncionarioBO() {
		funcionarioDAO = getFactoryDAOInstance().getInstance().getFuncionarioDAO();
	}

	public List<FuncionarioDTO> funcionariosListar() throws Exception {
		Connection conn = Conexao.getConnection();
		List<FuncionarioDTO> list = null;
		try{
			list = funcionarioDAO.profissaoListar(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public void inclui(FuncionarioDTO funcionarioDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		try{
			funcionarioDAO.inclui(funcionarioDTO,conn);			
		}catch(Exception e){
			conn.rollback();
			throw e;
		}finally{
			conn.close();
		}
		
	}

	public void exclui(Long[] idsFuncionario) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			funcionarioDAO.exclui(idsFuncionario,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

}
