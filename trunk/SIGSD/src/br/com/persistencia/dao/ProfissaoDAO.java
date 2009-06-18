package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ConstantesENUM;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.util.DTOFactory;

public class ProfissaoDAO extends GenericDAO{

	protected static final String strConsult ="SELECT idProfissao as id,nome,precoVisita,descricao FROM casaweb.profissao ";

	public List<ProfissaoDTO> profissaoListar(Connection conn) throws Exception {
		List<ProfissaoDTO> list =null;
		ProfissaoDTO profissaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE ativo = 1");
	//	qBuffer.append(" AND idProfissao <> ?");
	//	qBuffer.append(" AND idProfissao <> ?");
		try{
			ps = conn.prepareStatement(qBuffer.toString());
		//	ps.setLong(1, ConstantesENUM.ADMINISTRADOR_ID.id());
		//	ps.setLong(2, ConstantesENUM.ATENDENTE_ID.id());
			rs = ps.executeQuery();
			list = new ArrayList<ProfissaoDTO>();
			while(rs.next()){
				ProfissaoDTO dto = new ProfissaoDTO();
				
				//profissaoDTO = (ProfissaoDTO) DTOFactory.getDTO(ProfissaoDTO.class, rs);
				profissaoDTO = this.populaProfissaoDTO(dto,rs);
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

	private ProfissaoDTO populaProfissaoDTO(ProfissaoDTO dto, ResultSet rs) {
		try{
			dto.setId(rs.getLong("id"));
			dto.setNome(rs.getString("nome"));
			dto.setPrecoVisita(rs.getDouble("precoVisita"));
			dto.setDescricao(rs.getString("descricao"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	public void inclui(ProfissaoDTO profissao, Connection conn) throws Exception {
		
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO casaweb.profissao(nome,precovisita,descricao) VALUES(?,?,?)";
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

		String sql="UPDATE casaweb.profissao SET ativo = false WHERE profissao.idProfissao=?";
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

	public void altera(ProfissaoDTO profissaoDTO, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql="UPDATE casaweb.profissao SET nome = ?, precovisita = ?, descricao = ? WHERE profissao.idProfissao=?";
		try{
			
				ps = conn.prepareStatement(sql);
				ps.setString(1, profissaoDTO.getNome());
				ps.setDouble(2, profissaoDTO.getPrecoVisita());
				ps.setString(3, profissaoDTO.getDescricao());
				ps.setLong(4, profissaoDTO.getId());
				ps.executeUpdate();

		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
		}
		
	}

	public ProfissaoDTO consultarPor(Long id, Connection con) throws Exception {		
		ProfissaoDTO profissaoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE Profissao.idProfissao = ? ");

		try {
			ps = con.prepareStatement(qBuffer.toString());

			ps.setLong(1, id);

			rs = ps.executeQuery();
			
			// Seta no DTO o objetoo encontrado
			
			while (rs.next()) {
				profissaoDTO = new ProfissaoDTO();

				this.populaProfissaoDTO(profissaoDTO,rs);
				
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

		return profissaoDTO;
	}

}
