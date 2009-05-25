package br.com.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.Mensagem;
import br.com.MensagemLista;
import br.com.RegraNegocioException;
import br.com.persistencia.Conexao;
import br.com.persistencia.FactoryDAO;
import br.com.persistencia.dao.ClienteDAO;
import br.com.persistencia.dto.ClienteDTO;
import br.com.persistencia.dto.ServicoDTO;
import br.com.persistencia.dto.UfDTO;

public class ClienteBO extends GenericBO{
	
	//DAOs
	private ClienteDAO clienteDAO;
	
	public ClienteBO(){
		clienteDAO = FactoryDAO.getInstance().getClienteDAO();
	}
	

	public ClienteDTO consulta(String cpf) throws Exception {
		Connection conn = Conexao.getConnection();
		ClienteDTO clienteDTOConsultada =  null;
		try{
			clienteDTOConsultada = this.clienteDAO.consulta(cpf, conn);
		} catch(SQLException sqlE) {
			throw sqlE;
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
		return clienteDTOConsultada;
	}

	public ClienteDTO inclui(ClienteDTO clienteDTO) throws RegraNegocioException, Exception {
		Connection conn = Conexao.getConnection();
		ClienteDTO clienteDTOConsultada =  null;
		conn.setAutoCommit(false);
		MensagemLista mensagens = new  MensagemLista();
		
		try{
			System.out.printf("CPF Valido:%s \n", isValidCPF("01115375502"));
			System.out.printf("CPF Valido:%s \n", isValidCPF("98765232456"));
			System.out.printf("CPF Valido:%s \n", isValidCPF("19214849823"));
		    
			aplicaRegraDeNegocio(clienteDTO,conn);
			
			clienteDTOConsultada = this.clienteDAO.inclui(clienteDTO, conn);
		} catch(SQLException sqlE) {
			conn.rollback();
			throw sqlE;
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return clienteDTOConsultada;
	}


	private Boolean aplicaRegraDeNegocio(ClienteDTO cliente, Connection conn) throws RegraNegocioException, Exception {		
		MensagemLista mensagens = new  MensagemLista();
		String strCpf = cliente.getCpf().replace("-", "").replace(".", "");

		if(clienteDAO.existeCadastro(cliente,conn)){	
			mensagens.addMensagem("Cadastro existente no sistema.", Mensagem.ALERTA);
			throw new RegraNegocioException(mensagens);
		}
		if(!isValidCPF(strCpf)){	
			mensagens.addMensagem("CPF invalido.", Mensagem.ALERTA);
			throw new RegraNegocioException(mensagens);
		
		}
		
		return true;		
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


	public List<UfDTO> listUf() throws Exception {
		Connection conn = Conexao.getConnection();
		List<UfDTO> list = null;
		try{
			list = clienteDAO.listUf(conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		
		return list;
	}


	public ClienteDTO altera(ClienteDTO clienteDTO) throws Exception {
		Connection conn = Conexao.getConnection();
		ClienteDTO clienteDTOConsultada =  null;
		try{
			clienteDTOConsultada = clienteDAO.altera(clienteDTO,conn);
		}catch(Exception e){
			throw e;
		}finally{
			conn.close();
		}
		return clienteDTOConsultada;
		
	}

}
