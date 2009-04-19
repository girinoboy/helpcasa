package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.PerfilDTO;
import br.com.persistencia.dto.UfDTO;

public class ClienteDAO extends GenericDAO{

	public ClienteDTO consulta(String cpf, Connection con) throws SQLException, Exception {
		List<ClienteDTO> list = null;
		ClienteDTO clienteDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);

		try {
			ps = con.prepareStatement(qBuffer.toString());

			ps.setString(1, cpf);

			rs = ps.executeQuery();
			
			// Seta no DTO o objetoo encontrado
			list = new ArrayList<ClienteDTO>();
			while (rs.next()) {
				clienteDTO = new ClienteDTO();

				this.populaClienteDTO(clienteDTO,rs);

				list.add(clienteDTO);
			}
		//	this.populaClienteDTO(clienteDTO,null);
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
			//dados da pessoa
			dto.setId(rs.getLong("id"));
			dto.setPessoaId(new Integer((int) rs.getLong("id")));
			dto.setUsuario(rs.getString("usuario"));
			dto.setSenha(rs.getString("senha"));
			dto.setNome(rs.getString("nome"));
			dto.setRg(rs.getInt("rg"));
			dto.setCpf(rs.getString("cpf"));
			dto.setEmail(rs.getString("email"));
			dto.setNasc(new Date(rs.getTimestamp("nasc").getTime()));
			dto.setStatus(rs.getBoolean("status"));
			dto.setDataCadastro(rs.getTimestamp("dataCadastro"));
			dto.setTelefone(rs.getInt("telefone"));
			dto.setCelular(rs.getInt("celular"));
			//dados do cliente
			dto.setEndereco(rs.getString("endereco"));
			dto.setSituacao(rs.getString("situacao"));
			dto.setCep(rs.getInt("cep"));
			dto.setCidade(rs.getString("cidade"));
			
			UfDTO uf = new UfDTO();
			uf.setId(rs.getLong("iduf"));
			uf.setUf(rs.getString("uf"));
			uf.setDescricao(rs.getString("descricao"));
			dto.setUf(uf);
			
			PerfilDTO perfil = new PerfilDTO();
			perfil.setId(rs.getLong("perfil.id"));
			perfil.setDescricao(rs.getString("perfil.descricao"));
			dto.setPerfil(perfil);
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
	}
	
	public ClienteDTO inclui(ClienteDTO clienteDTO, Connection con) throws SQLException, Exception {
			List<ClienteDTO> list = null;

			PreparedStatement ps = null;
			ResultSet rs = null;

			StringBuffer qBuffer = new StringBuffer();		
			qBuffer.append(strConsult);
			insertPessoa(clienteDTO, con);
			insertCliente(clienteDTO, con);

			try {
				ps = con.prepareStatement(qBuffer.toString());

				String nomeTabela = "Cliente";
				String nomeColuna = "idCliente";
				ps.setLong(1, this.getLastIdTable(nomeTabela, nomeColuna, con));

				rs = ps.executeQuery();


				// Seta no DTO o objetoo encontrado
				list = new ArrayList<ClienteDTO>();
				while (rs.next()) {
					clienteDTO = new ClienteDTO();

					this.populaClienteDTO(clienteDTO,rs);

					list.add(clienteDTO);
				}
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
		
		public Boolean insertPessoa(ClienteDTO cliente,Connection con) throws Exception{
			PreparedStatement ps = null;
			ResultSet rs = null;
			Boolean executado=false;
			StringBuffer qBuffer = new StringBuffer();		

			qBuffer.append(strInsertPessoa);
			
			try{
				ps = con.prepareStatement(qBuffer.toString());

				ps.setString(1, cliente.getUsuario());
				ps.setString(2, cliente.getSenha());
				ps.setString(3, cliente.getNome());
				ps.setString(4, cliente.getCpf());
				ps.setLong(5, cliente.getRg());
				ps.setString(6, cliente.getEmail());
				ps.setTimestamp(7, new Timestamp(cliente.getNasc().getTime()));
				ps.setBoolean(8, cliente.getStatus());
				ps.setLong(9, cliente.getTelefone());
				ps.setLong(10, cliente.getCelular());
				ps.setLong(11, cliente.getTelefoneComercial());
				ps.setLong(12, cliente.getPerfil().getId());

				ps.executeUpdate();
				executado=true;
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
			
			return executado;
		}
			
			
		
		
		public Boolean insertCliente(ClienteDTO cliente,Connection con) throws Exception{
			PreparedStatement ps = null;
			ResultSet rs = null;
			Boolean executado=false;
			StringBuffer qBuffer = new StringBuffer();		

			qBuffer.append(strInsertCliente);
			
			try{
				ps = con.prepareStatement(qBuffer.toString());
				
				ps.setLong(1, this.getLastIdTable("Pessoa", "idPessoa", con));
				ps.setString(2, cliente.getEndereco());
				ps.setBoolean(3, cliente.getStatus());
				ps.setLong(4, cliente.getCep());
				ps.setString(5, cliente.getCidade());
				ps.setLong(6, cliente.getUf().getId());

				ps.executeUpdate();
				executado=true;
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
			
			return executado;
			
			
		}

	protected static final String strConsult ="SELECT idPessoa as \"id\", " +
				"usuario," +
				"senha," +
				"nome," +
				"rg," +
				"cpf," +
				"email,"+
				"nasc," +
				"status," +
				"dataCadastro," +
				"telefone," +
				"celular," +
				"Perfil.idPerfil as \"perfil.id\", " +
				"Perfil.descricao as \"perfil.descricao\", " +
				"Cliente.endereco,"+
				"Cliente.situacao,"+
				"Cliente.cep,"+
				"Cliente.cidade,"+
				"uf.iduf,"+
				"uf.uf,"+
				"uf.descricao "+
				"FROM casaweb.cliente " +
				"inner join casaweb.pessoa on cliente.idcliente=pessoa.idpessoa " +
				"inner join casaweb.perfil on perfil.idperfil=pessoa.idperfil " +
				"INNER JOIN CASAWEB.UF ON uf.iduf=cliente.iduf "+
				"WHERE Pessoa.cpf = ?";

	
	protected static final String strInsertPessoa ="INSERT INTO casaweb.Pessoa(usuario,senha,nome,cpf,rg,email,nasc,status,dataCadastro,telefone,celular,telefoneComercial,idPerfil) VALUES(?,?,?,?,?,?,?,?,now(),?,?,?,?);";
	protected static final String strInsertCliente ="INSERT INTO casaweb.Cliente(idCliente,endereco,situacao,cep,cidade,idUF)VALUES(?,?,?,?,?,?);";
}
