package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import br.com.persistencia.dto.AdicionaisDTO;

public class AdicionaisDAO extends GenericDAO {

	public void inclui(AdicionaisDTO adicional, Connection conn) throws Exception {
		PreparedStatement ps = null;

		String sql = "INSERT INTO casaweb.adicionais(descricao,valor,observacao,data,idSolicitacao)VALUES(?,?,?,now(),?)";
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

		String sql="DELETE FROM casaweb.adicionais WHERE idAdicionais=?";
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

}
