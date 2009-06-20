package br.com;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

public class Boleto{
   
/*   byte[] arquivo = null;
   File file = new File("\\bradesco.pdf");
   
    public static byte[] fileToByte(File arquivo) throws Exception {  
        FileInputStream fis = new FileInputStream(arquivo);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        byte[] buffer = new byte[8192];  
        int bytesRead = 0;  
        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {  
            baos.write(buffer, 0, bytesRead);  
        }  
        return baos.toByteArray();  
    }  
    public static InputStream byteToInputStream(byte[] bytes) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);  
        return bais;  
    }
    
    public void Exibir(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("application/pdf");  
        response.setContentLength(arquivo.length);  
        ServletOutputStream ouputStream = response.getOutputStream();  
        ouputStream.write(arquivo, 0, arquivo.length);  
        ouputStream.flush();  
        ouputStream.close();  
    }
   
   
   public void Boleto()
   {        
          try {
               JBoletoBean jBoletoBean = new JBoletoBean();

               jBoletoBean.setDataDocumento("25/03/2009");
               jBoletoBean.setDataProcessamento("25/03/2009");

               jBoletoBean.setCedente("Empresa Fict�cia Ltda");

               jBoletoBean.setNomeSacado("Xpto");
               jBoletoBean.setEnderecoSacado("Rua Xpto, 06");
               jBoletoBean.setBairroSacado("Jardim Xpto");
               jBoletoBean.setCidadeSacado("S�o Paulo");
               jBoletoBean.setUfSacado("SP");
               jBoletoBean.setCepSacado("00000-000");
               jBoletoBean.setCpfSacado("111.222.333-44");

               jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO BRADESCO");
               jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO BRADESCO");

               Vector descricoes = new Vector();
               descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
               descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
               descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
               descricoes.add("Extra - teste de descricao4 - R$ 78,90");
               jBoletoBean.setDescricoes(descricoes);

               jBoletoBean.setDataVencimento("31/03/2009");
               jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
               jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
               jBoletoBean.setInstrucao3("");
               jBoletoBean.setInstrucao4("");

               jBoletoBean.setCarteira("06");
               jBoletoBean.setAgencia("2949");
               jBoletoBean.setDvAgencia("1");

               jBoletoBean.setContaCorrente("0006580");
               jBoletoBean.setDvContaCorrente("3");

               jBoletoBean.setNossoNumero("003",11);
               jBoletoBean.setValorBoleto("500.00");

               Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BRADESCO);

               JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BRADESCO);
              
               jBoleto.addBoleto();

               jBoleto.closeBoleto("bradesco.pdf");
               
           }
           catch (Exception ex) {

               ex.printStackTrace();
           }
   }*/
   
   

	  private JBoletoBean jBoletoBean = new JBoletoBean();
	  private Vector descricoes = new Vector();
	  private boolean envia=false;
	 // private Clientes cliente=new Clientes();
	  private Date dataVencimento=new Date();

	 
	  byte[]  arquivo=null;

	  String s;
	    /**
	     * @return the jBoletoBean
	     */

	  public void exibir(HttpServletRequest request, HttpServletResponse response){

		  // ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		  try {
			  File arq=new File(s);
			  byte[] b = fileToByte(arq);


			  response.setContentType("application/pdf");
			  response.setHeader("Content-disposition", "inline; filename=\"" + arq.getName() + ".pdf\"");
			  response.getOutputStream().write(b);
			  response.getCharacterEncoding();
			  response.getOutputStream().flush();  
			  response.getOutputStream().close();


		  } catch (Exception e) {
			  e.printStackTrace();
		  }

	  }
	public void gerarBoleto(JBoletoBean jBoletoBean,String banco, HttpServletRequest request, HttpServletResponse response){
	     /*   if(banco.equals("Caixa Econ�mica")){//OK
	           geraCaixa(jBoletoBean);
	        }
	        else   if(banco.equals("Bradesco")){//oK
	           geraBradesco(jBoletoBean);
	        }
	        else  */ if(banco.equals("Banco do Brasil")){//oK
	         geraBB(jBoletoBean,request,response);
	        }
	      
	         /*
	        
	         else  if(banco.equals("Unibanco")){//OK
	         geraUnibanco(jBoletoBean);
	      }
	      

	        else if(banco.equals("Ita�")){//ok
	       geraItau(jBoletoBean);
	        }
	        if(banco.equals("Banco Real")){
	           geraReal(jBoletoBean);
	       }
*/


	      

	}

















/*

	public void geraItau(JBoletoBean jBoletoBean){
	            jBoletoBean.setCarteira("175");
	            jBoletoBean.setAgencia("2971");
	            jBoletoBean.setContaCorrente("08690");
	            jBoletoBean.setDvContaCorrente("1");
	            jBoletoBean.setNossoNumero("34556",8);
	            jBoletoBean.setNoDocumento("34556");
	            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.ITAU);
	            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.ITAU);
	             s="/"+cliente.getCliNome()+".pdf" ;
	            jBoleto.addBoleto();
	            jBoleto.closeBoleto(s);
	            exibir(null, null);
	 }*/
	public void geraBB(JBoletoBean jBoletoBean, HttpServletRequest request, HttpServletResponse response){
	            jBoletoBean.setCarteira("17");
	            jBoletoBean.setAgencia("3415");
	            jBoletoBean.setContaCorrente("00543004"); //completar com zeros quando necessario
	            jBoletoBean.setNumConvenio("1101354");
	            jBoletoBean.setNossoNumero("0005963971",10);
	            jBoletoBean.setDvContaCorrente("2");

	            jBoletoBean.setDvAgencia("8");
	            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_DO_BRASIL);
	            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BANCO_DO_BRASIL);
	            jBoleto.addBoleto();
	             s=ServletActionContext.getServletContext().getRealPath("/")+"Banco do Brasil"+".pdf" ;
	            jBoleto.addBoleto();
	            jBoleto.closeBoleto(s);
	            exibir(request, response);
	 }/*
	 public void geraCaixa(JBoletoBean jBoletoBean){
	      jBoletoBean.setCarteira("57");
	       jBoletoBean.setAgencia("0155");
	       jBoletoBean.setContaCorrente("13877");
	       jBoletoBean.setDvContaCorrente("4");
	       jBoletoBean.setCarteira("80"); //pode ser 80 ou 81 ou 82 (Confirmar com gerente)
	       jBoletoBean.setCodigoOperacao("870");
	       jBoletoBean.setCodigoFornecidoAgencia("00000324");
	       jBoletoBean.setDvCodigoFornecidoAgencia("02");
	       jBoletoBean.setNossoNumero("19525086",8);
	       jBoletoBean.setNoDocumento("987656123");
	       Generator generator = new PDFGenerator(jBoletoBean, JBoleto.CAIXA_ECONOMICA);
	       JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.CAIXA_ECONOMICA);
	       jBoleto.addBoleto();
	       s="/"+cliente.getCliNome()+".pdf" ;
	       jBoleto.closeBoleto(s);
	       exibir(null, null);
	}

	public void geraBradesco(JBoletoBean jBoletoBean){
	            jBoletoBean.setCarteira("06");
	            jBoletoBean.setAgencia("2949");
	            jBoletoBean.setDvAgencia("1");
	            jBoletoBean.setNoDocumento("321546");

	            jBoletoBean.setContaCorrente("0006580");
	            jBoletoBean.setDvContaCorrente("3");
	            jBoletoBean.setNossoNumero("003",11);
	            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BRADESCO);
	            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BRADESCO);
	            jBoleto.addBoleto();
	             s="/"+cliente.getCliNome()+".pdf" ;
	             jBoleto.closeBoleto(s);

	             exibir(null, null);
	}
	public void geraUnibanco(JBoletoBean jBoletoBean){
	           jBoletoBean.setCarteira("20");
	           

	            jBoletoBean.setAgencia("0123");
	            jBoletoBean.setContaCorrente("100618");
	            jBoletoBean.setDvContaCorrente("9");

	            jBoletoBean.setNossoNumero("1803029901",14);
	           

	            //c�digo do cliente fornecido pelo unibanco
	            jBoletoBean.setCodCliente("2031671");

	            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.UNIBANCO);
	            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.UNIBANCO);
	             jBoleto.addBoleto();
	             s="/"+cliente.getCliNome()+".pdf" ;
	             jBoleto.closeBoleto(s);
	             exibir(null, null);

	}


	public void geraReal(JBoletoBean jBoletoBean){
	            //ag. 0957
	            //cc. 5003709 6
	            jBoletoBean.setCarteira("57");
	            jBoletoBean.setAgencia("0957");
	            jBoletoBean.setContaCorrente("5003709");
	            jBoletoBean.setDvContaCorrente("6");

	            jBoletoBean.setNossoNumero("3020",13);
	            jBoletoBean.setNoDocumento("3020");
	           

	            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_REAL);
	            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BANCO_REAL);
	            jBoleto.addBoleto();
	             s="/"+cliente.getCliNome()+".pdf" ;
	             jBoleto.closeBoleto(s);
	             exibir(null, null);

	}*/


	// Este metodo abaixo foi obtido em http://www.guj.com.br/posts/list/55341.java
	public static byte[] fileToByte(File arquivo) throws Exception {
	        FileInputStream fis = new FileInputStream(arquivo);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buffer = new byte[8192];
	        int bytesRead = 0;
	        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
	            baos.write(buffer, 0, bytesRead);
	        }
	        return baos.toByteArray();
	    }





	  public JBoletoBean getJBoletoBean() {
	        return jBoletoBean;
	    }
   
}


