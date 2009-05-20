package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.com.persistencia.dto.PerfilDTO;
import br.com.persistencia.dto.PessoaDTO;
import br.com.persistencia.util.DTOFactory;

public class LoginDAO extends GenericDAO{

	public PessoaDTO login(Connection conn, PessoaDTO pessoaDTO) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		PessoaDTO pessoa = null;
		
		String sql="SELECT idPessoa as \"id\", " +
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
				"perfil.descricao as \"perfil.descricao\" " +
				"FROM casaweb.pessoa " +
				"INNER JOIN casaweb.perfil ON pessoa.idPerfil = perfil.idPerfil " +
				"WHERE pessoa.usuario=? " +
				"	AND pessoa.senha=?";
		
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, pessoaDTO.getUsuario());
			ps.setString(2, pessoaDTO.getSenha());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PessoaDTO dto = new PessoaDTO();
				pessoa = this.populaPessoa(dto,rs);
			}
			
			//pessoa = (PessoaDTO) DTOFactory.getDTO(PessoaDTO.class, rs);
			
		}catch(Exception e){
			throw e;
		}finally{
			if(rs !=null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		return pessoa;
	}

	private PessoaDTO populaPessoa(PessoaDTO dto, ResultSet rs) throws Exception {
		
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
		
		PerfilDTO perfil = new PerfilDTO();
		perfil.setId(rs.getLong("perfil.id"));
		perfil.setDescricao(rs.getString("perfil.descricao"));
		dto.setPerfil(perfil);
		
		return dto;
	}

}
