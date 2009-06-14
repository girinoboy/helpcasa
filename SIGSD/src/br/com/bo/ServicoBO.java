package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.ServicoDAO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;

public class ServicoBO extends GenericBO{
	
	private ServicoDAO servicoDAO;

	public ServicoBO() {
		servicoDAO = FactoryDAO.getInstance().getServicoDAO();
	}

	public List<ServicoDTO> servicosListar() throws Exception {
		Connection conn = Conexao.getConnection();
		List<ServicoDTO> list = null;
		try{
			list = servicoDAO.servicosListar(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public void exclui(Long[] idsServico) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			servicoDAO.exclui(idsServico,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

	public void inclui(ServicoDTO servicoDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		
		try{
			servicoDAO.inclui(servicoDTO,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

	public void altera(ServicoDTO servicoDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			servicoDAO.altera(servicoDTO,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

	public ServicoDTO consultarPor(Long id) throws Exception {
		Connection con = Conexao.getConnection();
		ServicoDTO servicoDTOConsultada = null;
		try {
			servicoDTOConsultada = this.servicoDAO.consultarPor(id, con);
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
		return servicoDTOConsultada;
	}

}
