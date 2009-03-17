package br.com.persistencia.dao.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.persistencia.dao.GenericDAO;
import br.com.persistencia.dto.cliente.ClienteDTO;

public class ClienteDAO extends GenericDAO{

	public ClienteDTO consulta(Long idCliente, Connection con) throws SQLException, Exception {
		List<ClienteDTO> list = null;
		ClienteDTO clienteDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);

		try {
			ps = con.prepareStatement(qBuffer.toString());

			ps.setLong(1, idCliente);

			rs = ps.executeQuery();
			
			// Seta no DTO o objetoo encontrado
			list = new ArrayList<ClienteDTO>();
			while (rs.next()) {
				clienteDTO = new ClienteDTO();

				this.populaClienteDTO(clienteDTO,rs);

				list.add(clienteDTO);
			}
			this.populaClienteDTO(clienteDTO,null);
		} catch (SQLException sqlE) {
			throw sqlE;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				throw e;
			}
		}

		return clienteDTO;
	}
	
	private void populaClienteDTO(ClienteDTO dto, ResultSet rs) {
		try{
			//dto.setNome(rs.getString("nome"));
			dto.setNome("Marcleônio");
		}catch(Exception e){
			e.printStackTrace();
		}		
		
	}

	protected static final String strConsult ="SELECT * FROM cliente WHERE id_cliente =?";

}
