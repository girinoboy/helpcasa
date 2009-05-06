package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.util.DTOFactory;

public class ServicoDAO extends GenericDAO{

	public List<ServicoDTO> servicosListar(Connection conn) throws Exception {
		List<ServicoDTO> list =null;
		ServicoDTO servicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT s.idServico," +
				"s.nome as nomeServico," +
				"s.descricao as descricaoServico," +				
				"p.idProfissao," +
				"p.nome as nomeProfissao," +
				"p.precovisita," +				
				"p.descricao as descricaoProfissao  " +
				"FROM casaweb.servico s " +
				"INNER JOIN casaweb.profissao p ON s.idProfissao=p.idProfissao";
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<ServicoDTO>();
			while(rs.next()){
				ServicoDTO dto = new ServicoDTO();
				
				servicoDTO = this.populaServico(dto,rs);
				//servicoDTO = (ServicoDTO) DTOFactory.getDTO(ServicoDTO.class, rs);
				
				list.add(servicoDTO);
				
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

	private ServicoDTO populaServico(ServicoDTO dto, ResultSet rs) throws SQLException {


		dto.setId(rs.getLong("idServico"));
		dto.setNome(rs.getString("nomeServico"));
		dto.setDescricao(rs.getString("descricaoServico"));
		
		ProfissaoDTO profissao = new ProfissaoDTO();
		profissao.setId(rs.getLong("idProfissao"));
		profissao.setNome(rs.getString("nomeProfissao"));
		profissao.setPrecoVisita(rs.getDouble("precovisita"));		
		profissao.setDescricao(rs.getString("descricaoProfissao"));
		dto.setProfissaoDTO(profissao);
		
		return dto;
	}

	public void exclui(Long[] idsServico, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql="UPDATE casaweb.Servico SET ativo = false WHERE Servico.idServico=?";
		try{
			for (Long id : idsServico) {
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

	public void inclui(ServicoDTO servico, Connection conn) throws Exception {
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO casaweb.Servico(nome,idProfissao,descricao) VALUES(?,?,?)";
		try{
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, servico.getNome());
			ps.setDouble(2, servico.getProfissaoDTO().getId());
			ps.setString(3, servico.getDescricao());
			
			ps.executeUpdate();	
			
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
		}
		
	}



}
