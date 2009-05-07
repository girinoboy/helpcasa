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
import br.com.persistencia.dto.UfDTO;
import br.com.persistencia.util.DTOFactory;

public class FuncionarioDAO extends GenericDAO{

	protected static final String strInsertPessoa ="INSERT INTO casaweb.Pessoa(usuario,senha,nome,cpf,rg,email,nasc,ativo,dataCadastro,telefone,celular,idPerfil) VALUES(?,?,?,?,?,?,?,?,now(),?,?,?);";
	protected static final String strInsertFuncionario ="INSERT INTO casaweb.funcionario(idFuncionario,matricula,idProfissao)VALUES((SELECT MAX(idPessoa) FROM casaweb.Pessoa),?,?)";
	protected static final String strConsult ="select ps.idpessoa as id, ps.nome,ps.cpf,pr.descricao,f.matricula from casaweb.funcionario f " +
	"inner join casaweb.pessoa ps on idpessoa = idfuncionario " +
	"inner join casaweb.perfil pr on pr.idperfil = ps.idperfil " +
	"GROUP BY pr.idperfil " +
	"ORDER BY ps.nome";

	public List<FuncionarioDTO> profissaoListar(Connection conn) throws Exception {
		List<FuncionarioDTO> list =null;
		FuncionarioDTO funcionarioDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		
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
		//dto.setUsuario(rs.getString("usuario"));
		//dto.setSenha(rs.getString("senha"));
		dto.setNome(rs.getString("nome"));
		//dto.setRg(rs.getInt("rg"));
		dto.setCpf(rs.getString("cpf"));
		//dto.setEmail(rs.getString("email"));
		//dto.setNasc(new Date(rs.getTimestamp("nasc").getTime()));
		//dto.setStatus(rs.getBoolean("status"));
		//dto.setDataCadastro(rs.getTimestamp("dataCadastro"));
		//dto.setTelefone(rs.getInt("telefone"));
		//dto.setCelular(rs.getInt("celular"));
		
		//dados do funcionario
		dto.setMatricula(rs.getString("matricula"));
		
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

}
