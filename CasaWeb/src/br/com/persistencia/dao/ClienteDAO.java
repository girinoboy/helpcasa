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
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.UfDTO;
import br.com.persistencia.util.DTOFactory;

public class ClienteDAO extends GenericDAO{

	public ClienteDTO consulta(String cpf, Connection con) throws SQLException, Exception {
		
		ClienteDTO clienteDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append("WHERE Pessoa.cpf = ?");

		try {
			ps = con.prepareStatement(qBuffer.toString());

			ps.setString(1, cpf);

			rs = ps.executeQuery();
			
			// Seta no DTO o objetoo encontrado
			
			while (rs.next()) {
				clienteDTO = new ClienteDTO();

				this.populaClienteDTO(clienteDTO,rs);

				
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
			dto.setRg(rs.getString("rg"));
			dto.setCpf(rs.getString("cpf"));
			dto.setEmail(rs.getString("email"));
			dto.setNasc(new Date(rs.getTimestamp("nasc").getTime()));
			dto.setAtivo(rs.getBoolean("ativo"));
			dto.setDataCadastro(rs.getTimestamp("dataCadastro"));
			dto.setTelefone(rs.getString("telefone"));
			dto.setCelular(rs.getString("celular"));
			//dados do cliente
			dto.setEndereco(rs.getString("endereco"));			
			dto.setCep(rs.getString("cep"));
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
	
	public ClienteDTO inclui(ClienteDTO clienteDTO, Connection conn) throws SQLException, Exception {
			List<ClienteDTO> list = null;

			PreparedStatement ps = null;
			ResultSet rs = null;

			StringBuffer qBuffer = new StringBuffer();		
			qBuffer.append(strConsult);
			qBuffer.append("WHERE Pessoa.idPessoa = ?");
			insertPessoa(clienteDTO, conn);
			insertCliente(clienteDTO, conn);
			conn.commit();
			try {
				ps = conn.prepareStatement(qBuffer.toString());

				String nomeTabela = "Cliente";
				String nomeColuna = "idCliente";
				ps.setLong(1, this.getLastIdTable(nomeTabela, nomeColuna, conn));

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
				ps.setString(5, cliente.getRg());
				ps.setString(6, cliente.getEmail());
				ps.setTimestamp(7, new Timestamp(cliente.getNasc().getTime()));
				ps.setBoolean(8, cliente.getAtivo());
				ps.setString(9, cliente.getTelefone());
				ps.setString(10, cliente.getCelular());
				ps.setLong(11, cliente.getPerfil().getId());
				ps.setString(12, cliente.getCep());

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
				//ps.setBoolean(3, cliente.getAtivo());				
				ps.setString(3, cliente.getCidade());
				ps.setLong(4, cliente.getUf().getId());

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
		
		public List<UfDTO> listUf(Connection conn) throws Exception {
			List<UfDTO> list =null;
			UfDTO ufDTO = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			
			String sql = "SELECT uf.idUf as id , uf.uf,uf.descricao FROM casaweb.uf";
					
			try{
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				list = new ArrayList<UfDTO>();
				while(rs.next()){
					UfDTO dto = new UfDTO();
					
					ufDTO = this.populaUF(dto,rs);
					//ufDTO = (UfDTO) DTOFactory.getDTO(UfDTO.class, rs);
					
					list.add(ufDTO);
					
				}
			}catch(Exception e){
				throw e;
			}finally{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			return list;
		}

		private UfDTO populaUF(UfDTO dto, ResultSet rs) {
			try{

				dto.setId(new Long (rs.getLong("id")));
				dto.setUf(rs.getString("uf"));
				dto.setDescricao(rs.getString("descricao"));

			}catch(Exception e){
				e.printStackTrace();
			}	
			return dto;
		}

		public ClienteDTO altera(ClienteDTO clienteDTO, Connection conn) throws Exception {
			PreparedStatement ps = null;

			String sql="UPDATE casaweb.Pessoa " +
			"SET senha=?," +
			"nome=?," +
			"cpf=?," +
			"rg=?," +
			"email=?," +
			"nasc=?," +
			"telefone=?," +
			"celular=?," +
			"cep=? " +
			"where idPessoa=?";
			try{

				ps = conn.prepareStatement(sql);																

				ps.setString(1, clienteDTO.getSenha());
				ps.setString(2, clienteDTO.getNome());
				ps.setString(3, clienteDTO.getCpf());
				ps.setString(4, clienteDTO.getRg());
				ps.setString(5, clienteDTO.getEmail());
				ps.setTimestamp(6, new Timestamp(clienteDTO.getNasc().getTime()));
				ps.setString(7, clienteDTO.getTelefone());
				ps.setString(8, clienteDTO.getCelular());
				ps.setString(9, clienteDTO.getCep());
				ps.setLong(10, clienteDTO.getId());

				ps.executeUpdate();

			}catch(Exception e){
				throw e;
			}finally{
				if(ps!=null)
					ps.close();
			}
			return clienteDTO;

		}
		
		public Boolean existeCadastro(ClienteDTO cliente, Connection conn) throws Exception {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			
			String sql = "SELECT casaweb.existe_cpf_usuario(?,?) as existe";
					
			try{
				ps = conn.prepareStatement(sql);
				ps.setString(1, cliente.getUsuario());
				ps.setString(2, cliente.getCpf());
				rs = ps.executeQuery();
				
				while(rs.next()){
					return rs.getBoolean("existe");
				}
			}catch(Exception e){
				throw e;
			}finally{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			return true;
		}

	protected static final String strConsult ="SELECT idPessoa as \"id\", " +
				"usuario," +
				"senha," +
				"nome," +
				"rg," +
				"cpf," +
				"email,"+
				"nasc," +
				"ativo," +
				"dataCadastro," +
				"telefone," +
				"celular," +
				"Perfil.idPerfil as \"perfil.id\", " +
				"Perfil.descricao as \"perfil.descricao\", " +
				"Cliente.endereco,"+				
				"Pessoa.cep,"+
				"Cliente.cidade,"+
				"uf.iduf,"+
				"uf.uf,"+
				"uf.descricao "+
				"FROM casaweb.cliente " +
				"inner join casaweb.pessoa on cliente.idcliente=pessoa.idpessoa " +
				"inner join casaweb.perfil on perfil.idperfil=pessoa.idperfil " +
				"INNER JOIN CASAWEB.UF ON uf.iduf=cliente.iduf ";

	
	protected static final String strInsertPessoa ="INSERT INTO casaweb.Pessoa(usuario,senha,nome,cpf,rg,email,nasc,ativo,dataCadastro,telefone,celular,idPerfil,cep) VALUES(?,?,?,?,?,?,?,?,now(),?,?,?,?);";
	protected static final String strInsertCliente ="INSERT INTO casaweb.Cliente(idCliente,endereco,cidade,idUF)VALUES(?,?,?,?);";
	
	
}
