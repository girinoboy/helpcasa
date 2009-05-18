package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.SolicitacaoDAO;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.SolicitacaoDTO;

public class SolicitacaoBO extends GenericBO{
	
	private SolicitacaoDAO solicitacaoDAO;

	public SolicitacaoBO() {
		solicitacaoDAO = FactoryDAO.getInstance().getSolicitacaoDAO();
	}

	public void solicitacaoInclui(SolicitacaoDTO solicitacaoDTO, List<SolicitacaoDTO> listHorariosDisponiveis) throws Exception {
		Connection conn = Conexao.getConnection();

		try {
			FuncionarioDTO funcionario=aplicaRegraDeNegocio(solicitacaoDTO,listHorariosDisponiveis);
			solicitacaoDTO.setFuncionario(funcionario);
			solicitacaoDAO.inclui(solicitacaoDTO, conn);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}

	}

	private FuncionarioDTO aplicaRegraDeNegocio(SolicitacaoDTO solicitacaoDTO, List<SolicitacaoDTO> listHorariosDisponiveis) {
		FuncionarioDTO funcionarioDTO =null;
		if(solicitacaoDTO.getPeriodo()==2){//faz a pesquisa se escolher periodo da manha
			solicitacaoDTO.getCliente().getCep();
			
			funcionarioDTO = new FuncionarioDTO();
			Long id = new Long(1);
			funcionarioDTO.setId(id);
			
		}
		
		return funcionarioDTO;
		
		
	}

	public List<SolicitacaoDTO> consultarFaturaBasica(Long idCliente) throws Exception {
		Connection conn = Conexao.getConnection();
		List<SolicitacaoDTO> list = null;
		try{
			list = solicitacaoDAO.consultarFaturaBasica(idCliente,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public List<SolicitacaoDTO> consultarFaturaDetalhada(Long idSolicitacao) throws Exception {
		Connection conn = Conexao.getConnection();
		List<SolicitacaoDTO> list = null;
		try{
			list = solicitacaoDAO.consultarFaturaDetalhada(idSolicitacao,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public List<SolicitacaoDTO> horariosDisponiveisListar(SolicitacaoDTO solicitacaoDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		List<SolicitacaoDTO> list = null;
		try{
			list = solicitacaoDAO.horariosDisponiveisListar(solicitacaoDTO,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

}
