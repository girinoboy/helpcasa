package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import br.com.persistencia.dto.PessoaDTO;

public abstract class GenericDAO {
	
	private PreparedStatement ps;
	private ResultSet rs;
	private PessoaDTO sessaoPessoa; 

	public GenericDAO() {
		super();		
	}
	
	/**
	 * @return PessoaDTO sessaoPessoa
	 */
	public PessoaDTO getSessaoPessoa() {
		return sessaoPessoa;
	}

	/**
	 * @param Seta o PessoaDTO sessaoPessoa
	 */
	public void setSessaoPessoa(PessoaDTO sessaoPessoa) {
		this.sessaoPessoa = sessaoPessoa;
	}
	
	
	/**
	 * Function para pegar o id da table conforme o nome passado para ela
	 * @param nomeTabela
	 * @param nomeColuna
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	protected Long getLastIdTable(String nomeTabela, String nomeColuna,Connection con) throws SQLException,Exception{
		Long id = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer qBuffer = new StringBuffer("SELECT MAX(" + nomeColuna.trim() + ") as id FROM "+nomeTabela.trim());
		
		
		try{
			
			ps = con.prepareStatement(qBuffer.toString());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				id = Long.valueOf(rs.getInt("id"));	
			}
		}catch (SQLException sqlE){
			sqlE.printStackTrace();
			throw sqlE;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		return id;
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
