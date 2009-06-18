package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.persistencia.dto.AdicionalDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.util.DTOFactory;

public class AdicionaisDAO extends GenericDAO {
	
	protected static final String strConsult ="SELECT idAdicional as id,descricao,valor,data,observacao FROM adicional ";

	public void inclui(AdicionalDTO adicional, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql = "INSERT INTO adicional(descricao,valor,observacao,data,idSolicitacao)VALUES(?,?,?,now(),?)";
		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, adicional.getDescricao());
			ps.setDouble(2, adicional.getValor());
			ps.setString(3, adicional.getObservacao());
			ps.setLong(4,adicional.getSolicitacao().getId());
			
			ps.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}

	}

	public void exclui(Long[] idsAdicional, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql="DELETE FROM adicional WHERE idAdicional=?";
		try{
			for (Long id : idsAdicional) {
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

	public List<AdicionalDTO> adicionalListar(Long idSolicitacao, Connection conn) throws Exception {
		List<AdicionalDTO> list =null;
		AdicionalDTO adicionalDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);
		qBuffer.append(" WHERE idSolicitacao = ?");
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			ps.setLong(1, idSolicitacao);
			rs = ps.executeQuery();
			list = new ArrayList<AdicionalDTO>();
			while(rs.next()){
				AdicionalDTO dto = new AdicionalDTO();
				
				adicionalDTO = this.populaAdicionalDTO(dto,rs);
				//adicionalDTO = (AdicionalDTO) DTOFactory.getDTO(AdicionalDTO.class, rs);
				
				list.add(adicionalDTO);
				
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

	private AdicionalDTO populaAdicionalDTO(AdicionalDTO dto, ResultSet rs) throws Exception {
		dto.setId(rs.getLong("id"));
		dto.setDescricao(rs.getString("descricao"));
		dto.setData(rs.getDate("data"));
		dto.setObservacao(rs.getString("observacao"));
		dto.setValor(rs.getDouble("valor"));
		return dto;
	}

}
