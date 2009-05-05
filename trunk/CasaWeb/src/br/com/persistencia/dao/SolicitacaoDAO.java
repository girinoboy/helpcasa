package br.com.persistencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import br.com.persistencia.dto.SolicitacaoDTO;

public class SolicitacaoDAO extends GenericDAO{

	public void inclui(SolicitacaoDTO solicitacao, Connection conn)
			throws Exception {
		PreparedStatement ps = null;

		String sql = "INSERT INTO casaweb.Solicitacao(data,periodo,idCliente,idFuncionario,idServico) VALUES(now(),?,?,?,?,?)";
		try {

			ps = conn.prepareStatement(sql);
			ps.setDate(1, new Date (solicitacao.getData().getTime()));
			ps.setInt(2, solicitacao.getPeriodo());
			ps.setLong(3, solicitacao.getCliente().getId());
			ps.setLong(4, solicitacao.getFuncionario().getId());
			ps.setDouble(5, solicitacao.getServico().getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}

	}

}
