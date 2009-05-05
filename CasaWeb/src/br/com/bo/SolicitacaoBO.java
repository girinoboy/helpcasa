package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.SolicitacaoDAO;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class SolicitacaoBO extends GenericBO{
	
	private SolicitacaoDAO solicitacaoDAO;

	public SolicitacaoBO() {
		solicitacaoDAO = FactoryDAO.getInstance().getSolicitacaoDAO();
	}

	public void solicitacaoInclui(SolicitacaoDTO solicitacaoDTO)
			throws Exception {
		Connection conn = Conexao.getConnection();

		try {
			FuncionarioDTO funcionario=aplicaRegraDeNegocio();;
			solicitacaoDTO.setFuncionario(funcionario);
			solicitacaoDAO.inclui(solicitacaoDTO, conn);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}

	}

	private FuncionarioDTO aplicaRegraDeNegocio() {
		return null;
		
		
	}

}
