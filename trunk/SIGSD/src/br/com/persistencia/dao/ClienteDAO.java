package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.util.StrutsTypeConverter;

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
		qBuffer.append("WHERE pessoa.cpf = ?");
		qBuffer.append(" AND ativo=1");
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
		try{ DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			//dados da pessoa
			dto.setId(rs.getLong("id"));
			dto.setPessoaId(new Integer((int) rs.getLong("id")));
			dto.setUsuario(rs.getString("usuario"));
			dto.setSenha(rs.getString("senha"));
			dto.setSenhaRepita(rs.getString("senha"));
			dto.setNome(rs.getString("nome"));
			dto.setRg(rs.getString("rg"));
			dto.setCpf(rs.getString("cpf"));
			dto.setEmail(rs.getString("email"));
			dto.setNasc(rs.getDate("nasc"));
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
			qBuffer.append("WHERE pessoa.idPessoa = ?");
			insertPessoa(clienteDTO, conn);
			insertCliente(clienteDTO, conn);
			conn.commit();
			try {
				ps = conn.prepareStatement(qBuffer.toString());

				String nomeTabela = "cliente";
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
				
				ps.setLong(1, this.getLastIdTable("pessoa", "idPessoa", con));
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
			
			
			String sql = "SELECT uf.idUf as id , uf.uf,uf.descricao FROM uf";
					
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
			PreparedStatement ps2 = null;

			String sql="UPDATE pessoa " +
			"SET senha=?," +
			"nome=?," +			
			"email=?," +
			"nasc=?," +
			"telefone=?," +
			"celular=?," +
			"cep=? " +			
			"where idPessoa=?";
			
			String sql2 ="UPDATE cliente set cidade=?, endereco=?,idUf=? where idCliente=?";
			try{

				ps = conn.prepareStatement(sql);																

				ps.setString(1, clienteDTO.getSenha());
				ps.setString(2, clienteDTO.getNome());				
				ps.setString(3, clienteDTO.getEmail());
				ps.setTimestamp(4, new Timestamp(clienteDTO.getNasc().getTime()));
				ps.setString(5, clienteDTO.getTelefone());
				ps.setString(6, clienteDTO.getCelular());
				ps.setString(7, clienteDTO.getCep());
				
				ps.setLong(8, clienteDTO.getId());

				ps.executeUpdate();
				
				ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, clienteDTO.getCidade());
				ps2.setString(2, clienteDTO.getEndereco());
				ps2.setLong(3, clienteDTO.getUf().getId());
				ps2.setLong(4, clienteDTO.getId());
				ps2.executeUpdate();
				
			}catch(Exception e){
				throw e;
			}finally{
				if(ps!=null)
					ps.close();
				if(ps2!=null)
					ps2.close();
			}
			return clienteDTO;

		}
		
		public Boolean existeCadastro(ClienteDTO cliente, Connection conn) throws Exception {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			
			String sql = "SELECT usuario FROM pessoa WHERE ativo=1 and (usuario = ? or cpf=?) ";
					
			try{
				ps = conn.prepareStatement(sql);
				ps.setString(1, cliente.getUsuario());
				ps.setString(2, cliente.getCpf());
				rs = ps.executeQuery();
				
				while(rs.next()){
					return true;
				}
			}catch(Exception e){
				throw e;
			}finally{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			return false;
		}
		
		public void exclui(String cpf, Connection conn) throws Exception {
			PreparedStatement ps = null;

			String sql="UPDATE pessoa SET ativo = 0 WHERE pessoa.cpf=?";
			try{
				
					ps = conn.prepareStatement(sql);
					ps.setString(1, cpf);
					ps.executeUpdate();
				

			}catch(Exception e){
				throw e;
			}finally{
				if(ps!=null)
					ps.close();
			}
			
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
				"perfil.idPerfil as \"perfil.id\", " +
				"perfil.descricao as \"perfil.descricao\", " +
				"cliente.endereco,"+				
				"pessoa.cep,"+
				"cliente.cidade,"+
				"uf.iduf,"+
				"uf.uf,"+
				"uf.descricao "+
				"FROM cliente " +
				"inner join pessoa on cliente.idcliente=pessoa.idpessoa " +
				"inner join perfil on perfil.idperfil=pessoa.idperfil " +
				"INNER JOIN uf ON uf.iduf=cliente.iduf ";

	
	protected static final String strInsertPessoa ="INSERT INTO pessoa(usuario,senha,nome,cpf,rg,email,nasc,ativo,dataCadastro,telefone,celular,idPerfil,cep) VALUES(?,?,?,?,?,?,?,?,now(),?,?,?,?);";
	protected static final String strInsertCliente ="INSERT INTO cliente(idCliente,endereco,cidade,idUF)VALUES(?,?,?,?);";
	
}
