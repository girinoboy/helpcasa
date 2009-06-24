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

	protected static final String strConsult = "SELECT s.idServico," +
	"s.nome as nomeServico," +
	"s.descricao as descricaoServico," +				
	"p.idProfissao," +
	"p.nome as nomeProfissao," +
	"p.precovisita," +				
	"p.descricao as descricaoProfissao  " +
	"FROM servico s " +
	"INNER JOIN profissao p ON s.idProfissao=p.idProfissao "+
	"INNER JOIN funcionario f ON f.idProfissao=p.idProfissao ";

	public List<ServicoDTO> servicosListar(Connection conn) throws Exception {
		List<ServicoDTO> list =null;
		ServicoDTO servicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE s.ativo = 1");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			rs = ps.executeQuery();
			list = new ArrayList<ServicoDTO>();
			while(rs.next()){
				ServicoDTO dto = new ServicoDTO();
				
				servicoDTO = this.populaServicoDTO(dto,rs);
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

	private ServicoDTO populaServicoDTO(ServicoDTO dto, ResultSet rs) throws SQLException {


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

		String sql="UPDATE servico SET ativo = false WHERE servico.idServico=?";
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
		
		String sql = "INSERT INTO servico(nome,idProfissao,descricao) VALUES(?,?,?)";
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

	public void altera(ServicoDTO servicoDTO, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql="UPDATE servico SET nome = ?, idProfissao = ?, descricao = ? WHERE servico.idServico=?";
		try{
			
				ps = conn.prepareStatement(sql);
				ps.setString(1, servicoDTO.getNome());
				ps.setDouble(2, servicoDTO.getProfissaoDTO().getId());
				ps.setString(3, servicoDTO.getDescricao());
				ps.setLong(4, servicoDTO.getId());
				ps.executeUpdate();

		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
		}
		
	}

	public ServicoDTO consultarPor(Long id, Connection con) throws Exception {
		ServicoDTO servicoDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE s.idServico = ? ");

		try {
			ps = con.prepareStatement(qBuffer.toString());

			ps.setLong(1, id);

			rs = ps.executeQuery();
			
			// Seta no DTO o objetoo encontrado
			
			while (rs.next()) {
				servicoDTO = new ServicoDTO();

				this.populaServicoDTO(servicoDTO,rs);
				
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

		return servicoDTO;
	}



}
