package br.com.persistencia.util;

import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import br.com.DTOFactoryException;
import br.com.persistencia.Conexao;

public class DTOFactory {

	public static Object getDTO(Class classType, ResultSet rs)
		throws DTOFactoryException {

		Object dto = null;

		try {
			Collection lista = getDTOCollection(classType, rs);
			Iterator i = lista.iterator();
			if (i != null && i.hasNext()) {
				dto = i.next();	
			}
		} catch (Exception e) {
			throw new DTOFactoryException(e.getMessage());
		}

		return dto;
	}

	public static Collection getDTOCollection(Class classType, ResultSet rs)
		throws DTOFactoryException {

		try {
			Class cls = classType;
			Object obj = null;

			Collection lista = new LinkedList();

			ResultSetMetaData rsmd = rs.getMetaData();
			StringBuffer sbAux = new StringBuffer();
			
			int i = 0;
			String nomeColuna = null;
			int tipoColuna = 0;
			String nomeMetodo = null;
			Method metodo = null;
			String nomeTipoArgumento = null;
			Class tipoArgumento = null;
			Class argumentTypes[] = new Class[1];
			Object[] objArray = new Object[1];

			while (rs.next()) {
				obj = cls.newInstance(); //Instância do DTO
				
				for (i = 1; i <= rsmd.getColumnCount(); i++) {

					nomeColuna = rsmd.getColumnName(i);
					nomeColuna = nomeColuna.substring(0, 1).toUpperCase() + nomeColuna.substring(1);
					tipoColuna = rsmd.getColumnType(i);
					
					if (!nomeColuna.equalsIgnoreCase("row_position")) {
						
						nomeMetodo = "set" + nomeColuna;
						tipoArgumento = getTipoArgumento(obj, nomeMetodo);
	
						if (tipoArgumento != null) {
	
							argumentTypes[0] = tipoArgumento;
							nomeTipoArgumento = tipoArgumento.getName();
							sbAux.setLength(0);

							if (rs.getObject(i) != null) {
								
								sbAux.append(rs.getObject(i).toString());
								
								if (nomeTipoArgumento.equals("java.lang.Boolean") || 
								    nomeTipoArgumento.equals("boolean") ) {
	
									boolean b = false;
									if (sbAux != null && sbAux.toString().equals("1")) {
										b = true;	
									}
									objArray[0] = new Boolean(b);
	
								} else if (nomeTipoArgumento.equals("java.lang.Byte") || 
										    nomeTipoArgumento.equals("byte") ) {
	
									objArray[0] = new Byte(sbAux.toString());
	
								} else if (nomeTipoArgumento.equals("java.lang.Character") || 
										    nomeTipoArgumento.equals("char") ) {
	
									objArray[0] = new Character(sbAux.toString().charAt(0));
	
								} else if (nomeTipoArgumento.equals("java.lang.Double") || 
										    nomeTipoArgumento.equals("double") ) {
	
									objArray[0] = new Double(sbAux.toString());
	
								} else if (nomeTipoArgumento.equals("java.lang.Float") || 
										    nomeTipoArgumento.equals("float") ) {
	
									objArray[0] = new Float(sbAux.toString());
	
								} else if (nomeTipoArgumento.equals("java.lang.Integer") || 
										    nomeTipoArgumento.equals("int") ) {
	
									objArray[0] = new Integer(sbAux.toString());
	
								} else if (nomeTipoArgumento.equals("java.lang.String")) {
	
									objArray[0] = sbAux.toString().trim();

								}  else if (nomeTipoArgumento.equals("java.util.Date")) {
	
								    Timestamp time = rs.getTimestamp(i);
									if (time != null) {
										objArray[0] = new Date(time.getTime());
									}	

								}  else if (nomeTipoArgumento.equals("java.lang.Object")) {
	
									Blob blob = rs.getBlob(i);
									byte b[] = blob.getBytes(1L, (int) blob.length());
									objArray[0] = b;

								} 

								metodo = cls.getMethod(nomeMetodo, argumentTypes);
								metodo.invoke(obj, objArray);
							}
	
						} else {
							throw new DTOFactoryException("Método " + nomeMetodo + " inexistente.");
						}	
					}	
				}
				lista.add(obj);
			}	

			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DTOFactoryException(e.getMessage());
		}

	}

	private static Class getTipoArgumento(Object dto, String nomeMetodo) {
		
		Class[] argTypes = null;
		Method metodos[] = dto.getClass().getMethods();
		Method metodo = null;
		Class tipoArgumento = null;

		
		try {
			for (int i=0; i < metodos.length; i++) {
				metodo = metodos[i];
				argTypes = metodo.getParameterTypes();
				if (metodo.getName().equals(nomeMetodo)) {
					if (argTypes.length > 0) {
						tipoArgumento = argTypes[0];
					}
					i = metodos.length;
				}	
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		    
		return tipoArgumento;		
	}

	
	public static void executaComando(String comando) throws SQLException{
		
		PreparedStatement ps = null;
		Connection conn = Conexao.getConnection();
		
		ps = conn.prepareStatement(comando);
		ps.execute();
		conn.close();
	}
}
