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
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.PerfilDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.UfDTO;
import br.com.persistencia.util.DTOFactory;

public class FuncionarioDAO extends GenericDAO{

	protected static final String strInsertPessoa ="INSERT INTO casaweb.Pessoa(usuario,senha,nome,cpf,rg,email,nasc,ativo,dataCadastro,telefone,celular,idPerfil) VALUES(?,?,?,?,?,?,?,?,now(),?,?,?);";
	protected static final String strInsertFuncionario ="INSERT INTO casaweb.funcionario(idFuncionario,matricula,idProfissao)VALUES((SELECT MAX(idPessoa) FROM casaweb.Pessoa),?,?)";
	protected static final String strConsult ="select ps.idpessoa as id, " +			
			"ps.usuario," +
			"ps.senha," +
			"ps.nome," +
			"ps.rg," +
			"ps.cpf," +
			"ps.email,"+
			"ps.nasc," +
			"ps.ativo," +
			"ps.dataCadastro," +
			"ps.telefone," +
			"ps.celular," +
			"ps.cep,"+
			"pr.descricao," +
			"f.matricula, " +
			"p.idProfissao," +
			"p.nome as nomeProfissao," +
			"p.precovisita," +				
			"p.descricao as descricaoProfissao  " +
			"from casaweb.funcionario f " +
			"inner join casaweb.pessoa ps on idpessoa = f.idfuncionario " +
			"inner join casaweb.perfil pr on pr.idperfil = ps.idperfil "+
			"INNER JOIN casaweb.profissao p ON f.idProfissao=p.idProfissao";

	public List<FuncionarioDTO> funcionariosListar(Connection conn) throws Exception {
		List<FuncionarioDTO> list =null;
		FuncionarioDTO funcionarioDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" ORDER BY pr.idPerfil,ps.nome");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			rs = ps.executeQuery();
			list = new ArrayList<FuncionarioDTO>();
			while(rs.next()){
				funcionarioDTO = new FuncionarioDTO();
				
				this.populaFuncionarioDTO(funcionarioDTO,rs);
				
				list.add(funcionarioDTO);
				
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

	private void populaFuncionarioDTO(FuncionarioDTO dto,ResultSet rs) throws SQLException {
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
		
		//dados do funcionario
		dto.setMatricula(rs.getString("matricula"));
		
		ProfissaoDTO profissao = new ProfissaoDTO();
		profissao.setId(rs.getLong("idProfissao"));
		profissao.setNome(rs.getString("nomeProfissao"));
		profissao.setPrecoVisita(rs.getDouble("precovisita"));		
		profissao.setDescricao(rs.getString("descricaoProfissao"));
		dto.setProfissao(profissao);
		
		//indica o perfil
		PerfilDTO perfil = new PerfilDTO();
		//perfil.setId(rs.getLong("perfil.id"));
		perfil.setDescricao(rs.getString("descricao"));
		dto.setPerfil(perfil);
		
	}

	public void inclui(FuncionarioDTO funcionarioDTO, Connection con) throws Exception {

		try {
			
			insertPessoa(funcionarioDTO, con);
			insertFuncionario(funcionarioDTO, con);

		} catch (SQLException sqlE) {
			throw sqlE;
		} catch (Exception e) {
			throw e;
		} finally {

		}

	}

	private Boolean insertFuncionario(FuncionarioDTO funcionario, Connection con) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean executado=false;
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strInsertFuncionario);
		
		try{
			ps = con.prepareStatement(qBuffer.toString());
			
			//ps.setLong(1, this.getLastIdTable("Pessoa", "idPessoa", con));
			ps.setString(1, funcionario.getMatricula());
			ps.setLong(2, funcionario.getProfissao().getId());

			ps.executeUpdate();
			executado=true;
			con.commit();
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

	public Boolean insertPessoa(FuncionarioDTO funcionario,Connection con) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean executado=false;
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strInsertPessoa);
		
		try{
			ps = con.prepareStatement(qBuffer.toString());

			ps.setString(1, funcionario.getUsuario());
			ps.setString(2, funcionario.getSenha());
			ps.setString(3, funcionario.getNome());
			ps.setString(4, funcionario.getCpf());
			ps.setString(5, funcionario.getRg());
			ps.setString(6, funcionario.getEmail());
			ps.setTimestamp(7, new Timestamp(funcionario.getNasc().getTime()));
			ps.setBoolean(8, funcionario.getAtivo());
			ps.setString(9, funcionario.getTelefone());
			ps.setString(10, funcionario.getCelular());
			ps.setLong(11, funcionario.getPerfil().getId());

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

	public void exclui(Long[] idsFuncionario, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql="DELETE FROM casaweb.Funcionario WHERE Funcionario.idFuncionario=?";
		try{
			for (Long id : idsFuncionario) {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, id);
				ps.executeUpdate();
			}

		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
		}
		
	}

	public void altera(FuncionarioDTO funcionarioDTO, Connection conn) throws Exception {
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
				
				ps.setString(1, funcionarioDTO.getSenha());
				ps.setString(2, funcionarioDTO.getNome());
				ps.setString(3, funcionarioDTO.getCpf());
				ps.setString(4, funcionarioDTO.getRg());
				ps.setString(5, funcionarioDTO.getEmail());
				ps.setTimestamp(6, new Timestamp(funcionarioDTO.getNasc().getTime()));
				ps.setString(7, funcionarioDTO.getTelefone());
				ps.setString(8, funcionarioDTO.getCelular());
				ps.setString(9, funcionarioDTO.getCep());
				ps.setLong(10, funcionarioDTO.getId());
				
				ps.executeUpdate();

		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
		}
		
	}

	public FuncionarioDTO consultarPor(Long id, Connection con) throws Exception {
		FuncionarioDTO funcionarioDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE idFuncionario = ? ");	

		try {
			ps = con.prepareStatement(qBuffer.toString());

			ps.setLong(1, id);

			rs = ps.executeQuery();
			
			// Seta no DTO o objetoo encontrado
			
			while (rs.next()) {
				funcionarioDTO = new FuncionarioDTO();

				this.populaFuncionarioDTO(funcionarioDTO,rs);
				
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

		return funcionarioDTO;
	}

}
