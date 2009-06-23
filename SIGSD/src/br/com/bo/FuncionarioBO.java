package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.Mensagem;
import br.com.MensagemLista;
import br.com.RegraNegocioException;
import br.com.persistencia.Conexao;
import br.com.persistencia.dao.FuncionarioDAO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.FuncionarioDTO;
import br.com.persistencia.dto.ProfissaoDTO;
import br.com.persistencia.dto.ServicoDTO;

public class FuncionarioBO extends GenericBO{

	FuncionarioDAO funcionarioDAO;
	
	public FuncionarioBO() {
		funcionarioDAO = getFactoryDAOInstance().getInstance().getFuncionarioDAO();
	}

	public List<FuncionarioDTO> funcionariosListar() throws Exception {
		Connection conn = Conexao.getConnection();
		List<FuncionarioDTO> list = null;
		try{
			list = funcionarioDAO.funcionariosListar(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}

	public void inclui(FuncionarioDTO funcionarioDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		try{
			aplicaRegraDeNegocio(funcionarioDTO,conn);
			funcionarioDAO.inclui(funcionarioDTO,conn);			
		}catch(Exception e){
			conn.rollback();
			throw e;
		}finally{
			conn.close();
		}
		
	}
	
	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCPF(String cpf) {
		if ((cpf==null) || (cpf.length()!=11)) return false;

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	}
	
	private Boolean aplicaRegraDeNegocio(FuncionarioDTO funcionarioDTO, Connection conn) throws RegraNegocioException, Exception {		
		MensagemLista mensagens = new  MensagemLista();
		String strCpf = funcionarioDTO.getCpf().replace("-", "").replace(".", "");

		if(!isValidCPF(strCpf)){	
			mensagens.addMensagem("CPF invalido.", Mensagem.ALERTA);
			throw new RegraNegocioException(mensagens);
		
		}
		if(funcionarioDAO.existeCadastro(funcionarioDTO,conn)){	
			mensagens.addMensagem("Cadastro existente no sistema.", Mensagem.ALERTA);
			throw new RegraNegocioException(mensagens);
		}
		if(funcionarioDAO.existeMatricula(funcionarioDTO,conn)){
			mensagens.addMensagem("Matricula existente no sistema.", Mensagem.ALERTA);
			throw new RegraNegocioException(mensagens);
		}
		
		
		return true;		
	}

	public void exclui(Long[] idsFuncionario) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			funcionarioDAO.exclui(idsFuncionario,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
	}

	public void altera(FuncionarioDTO funcionarioDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		try{
			funcionarioDAO.altera(funcionarioDTO,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
	}

	public FuncionarioDTO consultarPor(Long id) throws Exception {
		Connection con = Conexao.getConnection();
		FuncionarioDTO funcionarioDTOConsultada = null;
		try {
			funcionarioDTOConsultada = this.funcionarioDAO.consultarPor(id, con);
		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
		return funcionarioDTOConsultada;
	}

}
