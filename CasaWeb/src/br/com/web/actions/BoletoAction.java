package br.com.web.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boleto.Boleto;

public class BoletoAction  extends GenericAction {

	public String gerarBoleto(){
		Boleto boleto = new Boleto();
		
		try {
			boleto.Exibir(getRequest(), getResponse());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "gerado.fwd";
	}

	
}
