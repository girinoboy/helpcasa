package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.RelatoriosDAO;
import br.com.persistencia.dto.RelatorioDTO;

public class RelatoriosBO extends GenericBO{
	private RelatoriosDAO relatoriosDAO;
	
	public RelatoriosBO(){
		relatoriosDAO =FactoryDAO.getInstance().getRelatoriosDAO();
	}

	public Collection<RelatorioDTO> resumoFaturamentoMensal() throws Exception {
		Connection conn = Conexao.getConnection();
		List<RelatorioDTO> list =null;
		
		try {
			list = relatoriosDAO.resumoFaturamentoMensal(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		return list;
	}

}
