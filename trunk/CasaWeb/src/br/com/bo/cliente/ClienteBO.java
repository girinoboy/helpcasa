package br.com.bo.cliente;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bo.GenericBO;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.cliente.ClienteDAO;
import br.com.persistencia.dto.cliente.ClienteDTO;

public class ClienteBO extends GenericBO{
	
	//DAOs
	private ClienteDAO clienteDAO;
	
	//DAOs
	protected ClienteDAO getClienteDAO() {
		if(clienteDAO == null){
			clienteDAO = FactoryDAO.getInstance().getClienteDAO();
		}
		return clienteDAO;
	}

	public ClienteDTO consulta(Long idCliente) throws Exception {
		Connection con = null;//getConnection();
		ClienteDTO clienteDTOConsultada =  null;
		try{
			clienteDTOConsultada = this.getClienteDAO().consulta(idCliente, con);
		} catch(SQLException sqlE) {
			throw sqlE;
		} catch (Exception e) {
			throw e;
		} finally {
		//	con.close();
		}
		return clienteDTOConsultada;
	}

}
