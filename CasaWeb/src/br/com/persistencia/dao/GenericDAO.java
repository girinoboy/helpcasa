package br.com.persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

public abstract class GenericDAO {



	public GenericDAO() {
		super();		
	}

	protected void psSetInt(PreparedStatement ps, int indice, int valor)throws SQLException, Exception {
		if (valor > 0) {
			ps.setInt(indice, valor);
		}else{
			ps.setNull(indice, Types.INTEGER);
		}
	}

	protected void psSetFloat(PreparedStatement ps, int indice, float valor)throws SQLException, Exception {
		if (valor > 0) {
			ps.setFloat(indice, valor);
		}else{
			ps.setNull(indice, Types.FLOAT);
		}
	}

	protected void psSetString(PreparedStatement ps, int indice, String valor)throws SQLException, Exception {
		if (valor != null && !"".equals(valor.trim())) {
			ps.setString(indice, valor);
		}else{
			ps.setNull(indice, Types.VARCHAR);
		}
	}

	protected void psSetChar(PreparedStatement ps, int indice, char valor)throws SQLException, Exception {
		if (valor != '\0' && valor != '0') {
			ps.setString(indice, String.valueOf(valor));
		}else{
			ps.setNull(indice, Types.CHAR);	
		}
	}
	protected void psSetInt(PreparedStatement ps, int indice, Integer valor)throws SQLException, Exception {
		if (valor != null) {
			ps.setInt(indice, valor);
		}else{
			ps.setNull(indice, Types.INTEGER);
		}
	}
	protected void psSetLong(PreparedStatement ps, int indice, Long valor)throws SQLException, Exception {
		if (valor != null) {
			ps.setLong(indice, valor);
		}else{
			ps.setNull(indice, Types.LONGVARCHAR);
		}
	}
	protected void psSetDate(PreparedStatement ps, int indice, Date valor)throws SQLException, Exception {
		if (valor != null) {
			ps.setTimestamp(indice, new Timestamp(valor.getTime()));
		}else{
			ps.setNull(indice, Types.TIMESTAMP);
		}
	}
	protected void psSetBoolean(PreparedStatement ps, int indice, Boolean valor)throws SQLException, Exception {
		if(valor != null){
			ps.setBoolean(indice, valor);
		}else{
			ps.setNull(indice, Types.BOOLEAN);
		}
	}

}
