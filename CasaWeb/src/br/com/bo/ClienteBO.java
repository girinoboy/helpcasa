package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.ClienteDAO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.UfDTO;

public class ClienteBO extends GenericBO{
	
	//DAOs
	private ClienteDAO clienteDAO;
	
	public ClienteBO(){
		clienteDAO = FactoryDAO.getInstance().getClienteDAO();
	}
	

	public ClienteDTO consulta(String cpf) throws Exception {
		Connection con = Conexao.getConnection();
		ClienteDTO clienteDTOConsultada =  null;
		try{
			clienteDTOConsultada = this.clienteDAO.consulta(cpf, con);
		} catch(SQLException sqlE) {
			throw sqlE;
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
		return clienteDTOConsultada;
	}

	public ClienteDTO inclui(ClienteDTO clienteDTO) throws Exception {
		Connection con = Conexao.getConnection();
		ClienteDTO clienteDTOConsultada =  null;
		
		try{
			clienteDTOConsultada = this.clienteDAO.inclui(clienteDTO, con);
		} catch(SQLException sqlE) {
			throw sqlE;
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
		return clienteDTOConsultada;
	}


	public List<UfDTO> listUf() throws Exception {
		Connection conn = Conexao.getConnection();
		List<UfDTO> list = null;
		try{
			list = clienteDAO.listUf(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

}
