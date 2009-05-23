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
			if(aplicaRegraDeNegocio(clienteDTO)){	
				mensagens.addMensagem("CPF invalido.", Mensagem.ALERTA);
				throw new RegraNegocioException(mensagens);
			//	throw new Exception("CPF invalido.");
			}if(clienteDAO.existeCadastro(clienteDTO,conn)){	
				mensagens.addMensagem("Cadastro existente no sistema.", Mensagem.ALERTA);
				throw new RegraNegocioException(mensagens);
			}
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


	private Boolean aplicaRegraDeNegocio(ClienteDTO cliente) {			
		
		/** Realiza a validação do CPF.
	    *
	    * @param   strCPF número de CPF a ser validado
	    * @return  true se o CPF é válido e false se não é válido
	    */
		  int     d1, d2;
	      int     digito1, digito2, resto;
	      int     digitoCPF;
	      String  nDigResult;

	      d1 = d2 = 0;
	      digito1 = digito2 = resto = 0;

	      String strCpf = cliente.getCpf().replace("-", "").replace(".", "");
		for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
	      {
	         digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

	         //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
	         d1 = d1 + ( 11 - nCount ) * digitoCPF;

	         //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
	         d2 = d2 + ( 12 - nCount ) * digitoCPF;
	      };

	      //Primeiro resto da divisão por 11.
	      resto = (d1 % 11);

	      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	      if (resto < 2)
	         digito1 = 0;
	      else
	         digito1 = 11 - resto;

	      d2 += 2 * digito1;

	      //Segundo resto da divisão por 11.
	      resto = (d2 % 11);

	      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	      if (resto < 2)
	         digito2 = 0;
	      else
	         digito2 = 11 - resto;

	      //Digito verificador do CPF que está sendo validado.
	      String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

	      //Concatenando o primeiro resto com o segundo.
	      nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

	      //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
	      return nDigVerific.equals(nDigResult);		
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
