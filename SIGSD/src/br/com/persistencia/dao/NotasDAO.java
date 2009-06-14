package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.persistencia.dto.HistoricoDTO;
import br.com.persistencia.dto.NotaDTO;

public class NotasDAO extends GenericDAO{

	private static final String strConsult = "select * from casaweb.nota";
	
	public List<NotaDTO> consultarNotas(Connection conn) throws Exception {
		List<NotaDTO> list =null;
		NotaDTO notaDTO = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		StringBuffer qBuffer = new StringBuffer();		

		qBuffer.append(strConsult);		
		
		try{
			ps = conn.prepareStatement(qBuffer.toString());
			rs = ps.executeQuery();
			list = new ArrayList<NotaDTO>();
			while(rs.next()){
				NotaDTO dto = new NotaDTO();
				
				notaDTO = this.populaNotasDTO(dto,rs);
				//historicoDTO = (HistoricoDTO) DTOFactory.getDTO(HistoricoDTO.class, rs);
				
				list.add(notaDTO);
				
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

	private NotaDTO populaNotasDTO(NotaDTO dto, ResultSet rs) throws Exception {
		dto.setId(rs.getLong("idNota"));
		dto.setDescricao(rs.getString("descricao"));
		return dto;
	}

}
