package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.util.DTOFactory;

public class ProfissaoDAO extends GenericDAO{

	public List<ProfissaoDTO> profissaoListar(Connection conn) throws Exception {
		List<ProfissaoDTO> list =null;
		ProfissaoDTO profissaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT idProfissao as id,nome,precoVisita,descricao FROM casaweb.Profissao";
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<ProfissaoDTO>();
			while(rs.next()){
				profissaoDTO = new ProfissaoDTO();
				
				profissaoDTO = (ProfissaoDTO) DTOFactory.getDTO(ProfissaoDTO.class, rs);
				
				list.add(profissaoDTO);
				
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

	public void inclui(ProfissaoDTO profissao, Connection conn) throws Exception {
		
		PreparedStatement ps = null;
		
		String sql = "INSET INTO casaweb.Profissao(nome,precovisita,dataCadastro,descricao) VALUES(?,?,now(),?)";
		try{
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, profissao.getNome());
			ps.setDouble(2, profissao.getPrecoVisita());
			ps.setString(3, profissao.getDescricao());
			
			ps.executeUpdate();	
			
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
		}
		
	}

	public void exclui(Long[] idsProfissao, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql="DELETE FROM casaweb.Profissao WHERE Profissao.idProfissao=?";
		try{
			for (Long id : idsProfissao) {
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
