package br.com.boleto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

public class Boleto{
   
   byte[] arquivo = null;
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

               jBoletoBean.setCedente("Empresa Fictícia Ltda");

               jBoletoBean.setNomeSacado("Xpto");
               jBoletoBean.setEnderecoSacado("Rua Xpto, 06");
               jBoletoBean.setBairroSacado("Jardim Xpto");
               jBoletoBean.setCidadeSacado("São Paulo");
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
   }
}


