package br.com.web.actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.model.DataModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboleto.JBoletoBean;

import br.com.boleto.Boleto;

public class BoletoAction  extends GenericAction {

	byte[] arquivo=null;
	File file=new File("C://boleto.pdf");
//	private Clientes cliente=new Clientes();
	private String caminho="";
	//ListlistaClientes=new ArrayList();
	DataModel modelo;
	boolean s=true;
	Boleto boletos=new Boleto();
//	private Clientes cliente1=new Clientes();
	private Date dataVencimento=new Date();
	private String banco;
	private  String cedente="";
	private Vector<String> descricoes = new Vector<String>();
	private String valor;
	private JBoletoBean jBoletoBean=new  JBoletoBean();
	private String localDePagamento="ATE O VENCIMENTO, PREFERENCIALMENTE NA CAIXA ECONOMICA";
	private String localDePagamentoAposVencimento="APOS O VENCIMENTO, SOMENTE NA CAIXA ECONOMICA";
	private String instrucao1="APOS O VENCIMENTO COBRAR MULTA DE 2%";
	private String instrucao2="APOS O VENCIMENTO, COBRAR MULTA DE  R$ 0.50 AO DIA";
	private String instrucao3="APOS O VENCIMENTO NÂO APLICAR DESCONTO";
	private String instrucao4="APOS O VENCIMENTO COBRAR R$ 1.50 DE TAXA DE OPERAÇÂO ";
	private String instrucao5="ANTES DO VENCIMENTO APLICAR DESCONTO DE 5%";
	private String descricao1="Boleta referente a cobrança de serviços prestados. ";
	private String descricao2="Os serviços do SIGSD foram encerrados no dia 19/12/2009";
	private Date dataDocumento=new Date();
	public BoletoAction() {
    }
	public void gerarBoleto(){
	    SimpleDateFormat fm=new SimpleDateFormat("dd/MM/yyyy");
	    getJBoletoBean().setDataProcessamento(fm.format(dataDocumento));
	    descricoes.add(getDescricao1());
	    descricoes.add(getDescricao1());
	    getDescricoes().clear();
	    getDescricoes().add(getDescricao1());
	    getDescricoes().add(getDescricao2());
	    getJBoletoBean().setDescricoes(descricoes);
	    getJBoletoBean().setLocalPagamento(localDePagamento);
	    getJBoletoBean().setLocalPagamento2(localDePagamentoAposVencimento);
	    getJBoletoBean().setInstrucao1(instrucao1);
	    getJBoletoBean().setInstrucao2(instrucao2);
	    getJBoletoBean().setInstrucao3(instrucao3);
	    getJBoletoBean().setInstrucao4(instrucao4);
	    getJBoletoBean().setInstrucao5(instrucao5);
	 /*   jBoletoBean.setNomeSacado(cliente1.getCliNome());
	    jBoletoBean.setEnderecoSacado(cliente1.getCliRua());
	    jBoletoBean.setBairroSacado(cliente1.getCliBairro());
	    jBoletoBean.setCidadeSacado(cliente1.getCliCidade());
	    jBoletoBean.setUfSacado(cliente1.getCliEstado());
	    jBoletoBean.setCepSacado(cliente1.getCliCep());
	    jBoletoBean.setCpfSacado(cliente1.getCliCPFCNPJ());*/
	    
	    
	    jBoletoBean.setNomeSacado(getSessaoPessoa().getNome());
	    jBoletoBean.setEnderecoSacado("Endereço");
	    jBoletoBean.setBairroSacado("Bairo");
	    jBoletoBean.setCidadeSacado("Cidade");
	    jBoletoBean.setUfSacado("UF");
	    jBoletoBean.setCepSacado(getSessaoPessoa().getCep());
	    jBoletoBean.setCpfSacado(getSessaoPessoa().getCpf());
	    jBoletoBean.setDataVencimento(fm.format(getDataVencimento()));
	    jBoletoBean.setValorBoleto("25");
	   jBoletoBean.setDataDocumento(fm.format(dataDocumento));
	   boletos.gerarBoleto(jBoletoBean,"Banco do Brasil",getRequest(),getResponse());

	    
	 
	}



/*

	 
	   
	    public Clientes getCliente() {
	        return cliente;
	    }

	    
	    public void setCliente(Clientes cliente) {
	        this.cliente = cliente;
	    }
	 public void salvarCliente(){
	 listaClientes.add(this.cliente);
	 salvarClientes();
	 limpar();
	 Mensagem.mostraMensagem("Cliente Cadastrado com Sucesso!!!!");
	 
	 }

	   public void limpar() {
	       this.cliente=new Clientes();
	    }
	public DataModel getTodosClientes(){
	if(s){
	   carregaClientes();
	  
	   s=false;
	}
	modelo=new ListDataModel(this.listaClientes);
	return modelo;
	}
	public void carregaClientes(){
	 String[] cnpjs={"02.666.391/0001-03","09.999.345/0001-02","05.985.985/0001-05"};
	 String[] nomes={"Loja de Doces","Mercearia Tia Joana","Fazenda João Soares"} ;
	 String[] Telefones={"(37)3289-1521","(31)3221-4598","(11)3214-2154"};
	 String[] Fax={"(45)3215-4562","(12)3215-7852","(35)3221-4568"};
	 String[] Celular={"(45)9982-4562","(12)9852-7852","(35)9824-4568"};
	 String[] email={"lojadoces@lojadoces.com.br","tiajoana@joana.com.br","joasoares@fazenda.com.br"};
	 String[] bairro={"Nova Fortaleza","Vila Romana","Juda Fonseca" };
	 String[] rua={"Av. 21 de Abril","Av. Afonso Pena", "Av. Conceição Barbosa"};
	 String[] cep={"35485-256","35500-458","35500-489"};
	 String[] numero={"250","230","485"};
	 String[] estado={"MG","SP","RJ"};
	 String[] cidade={"Belo Horizonte","Barueri","Niteroi"};

	for(int i=0;i<3;i++){
	Clientes cliente=new Clientes();
	cliente.setCliNome(nomes[i]);
	cliente.setCliCPFCNPJ(cnpjs[i]);
	cliente.setCliTelefone(Telefones[i]);
	cliente.setCliFax(Fax[i]);
	cliente.setCliCelular(Celular[i]);
	cliente.setCliEmail(email[i]);
	cliente.setCliBairro(bairro[i]);
	cliente.setCliRua(rua[i]);
	cliente.setCliCep(cep[i]);
	cliente.setCliNumero(numero[i]);
	cliente.setCliCidade(cidade[i]);
	cliente.setCliEstado(estado[i]);
	listaClientes.add(cliente);
	} 
	}
	  public List getBancos() {
	   List lista = new ArrayList();
	    List<String> situacao=new ArrayList<String>();
	    situacao.add("Banco do Brasil");
	    situacao.add("Bradesco");
	    situacao.add("Banco Real");
	    situacao.add("Caixa Econômica");
	    situacao.add("Unibanco");
	    situacao.add("Itaú");
	   for(String banco:situacao){
	   lista.add(banco);


	}
	  return lista;




	}






	public void salvarClientes(){
	try {
				 
				 
				 FileOutputStream fs=new FileOutputStream("ListaClientes.txt");
				 ObjectOutputStream os=new ObjectOutputStream(fs);
				 os.writeObject(this.listaClientes);
				 os.flush();
	             os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public List<Clientes> lerClientes(){
	List<Clientes> lista=null;
	    try {
					FileInputStream fi=new FileInputStream("ListaClientes.txt");
					 ObjectInputStream in=new ObjectInputStream(fi);
					 lista=(List<Clientes>)in.readObject();


			 } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	return lista;





	}

	public void limparC(){
	this.cliente1=new Clientes();
	setCedente("");
	setDataVencimento(new Date());
	setDataDocumento(new Date());
	setDescricao1("");
	setDescricao2("");
	setInstrucao1("");
	setDescricao2("");
	setInstrucao1("");
	setInstrucao2("");
	setInstrucao3("");
	setInstrucao4("");
	setInstrucao5("");
	setValor("");
	}

	public void selecionar(){
	 
	        this.setCliente1((Clientes) modelo.getRowData());

	}

	    
	    public Clientes getCliente1() {
	        return cliente1;
	    }

	    
	    public void setCliente1(Clientes cliente1) {
	        this.cliente1 = cliente1;
	    }
	public static void main(String[] args) {
	 File file=new File(".");
	System.out.println(file.getAbsoluteFile());


	}*/

	    /**
	     * @return the caminho
	     */
	    public String getCaminho() {
	        return caminho;
	    }

	    /**
	     * @param caminho the caminho to set
	     */
	    public void setCaminho(String caminho) {
	        this.caminho = caminho;
	    }

	    /**
	     * @return the dataVencimento
	     */
	    public Date getDataVencimento() {
	        return dataVencimento;
	    }

	    /**
	     * @param dataVencimento the dataVencimento to set
	     */
	    public void setDataVencimento(Date dataVencimento) {

	        this.dataVencimento = dataVencimento;
	    }

	    /**
	     * @return the banco
	     */
	    public String getBanco() {
	        return banco;
	    }

	    /**
	     * @param banco the banco to set
	     */
	    public void setBanco(String banco) {
	        this.banco = banco;
	    }

	    /**
	     * @return the valor
	     */
	    public String getValor() {
	        return valor;
	    }

	    /**
	     * @param valor the valor to set
	     */
	    public void setValor(String valor) {
	        this.valor = valor;
	    }

	    /**
	     * @return the jBoletoBean
	     */
	    public JBoletoBean getJBoletoBean() {
	        return jBoletoBean;
	    }

	    /**
	     * @param jBoletoBean the jBoletoBean to set
	     */
	    public void setJBoletoBean(JBoletoBean jBoletoBean) {
	        this.jBoletoBean = jBoletoBean;
	    }

	    /**
	     * @return the cedente
	     */
	    public String getCedente() {
	        return cedente;
	    }

	    /**
	     * @param cedente the cedente to set
	     */
	    public void setCedente(String cedente) {
	       jBoletoBean.setCedente(cedente);
	        this.cedente = cedente;
	    }

	    /**
	     * @return the localDePagamentoAposVencimento
	     */
	    public String getLocalDePagamentoAposVencimento() {

	        return localDePagamentoAposVencimento;
	    }

	    /**
	     * @param localDePagamentoAposVencimento the localDePagamentoAposVencimento to set
	     */
	    public void setLocalDePagamentoAposVencimento(String localDePagamentoAposVencimento) {
	       
	        this.localDePagamentoAposVencimento = localDePagamentoAposVencimento;
	    }

	    /**
	     * @return the instrucao1
	     */
	    public String getInstrucao1() {
	        return instrucao1;
	    }

	    /**
	     * @param instrucao1 the instrucao1 to set
	     */
	    public void setInstrucao1(String instrucao1) {
	       
	        this.instrucao1 = instrucao1;
	    }

	    /**
	     * @return the instrucao2
	     */
	    public String getInstrucao2() {
	        return instrucao2;
	    }

	    /**
	     * @param instrucao2 the instrucao2 to set
	     */
	    public void setInstrucao2(String instrucao2) {
	      
	        this.instrucao2 = instrucao2;
	    }

	    /**
	     * @return the instrucao3
	     */
	    public String getInstrucao3() {
	        return instrucao3;
	    }

	    /**
	     * @param instrucao3 the instrucao3 to set
	     */
	    public void setInstrucao3(String instrucao3) {
	       
	        this.instrucao3 = instrucao3;
	    }

	    /**
	     * @return the localDePagamento
	     */
	    public String getLocalDePagamento() {
	        return localDePagamento;
	    }

	    /**
	     * @param localDePagamento the localDePagamento to set
	     */
	    public void setLocalDePagamento(String localDePagamento) {
	        
	        this.localDePagamento = localDePagamento;
	    }

	    /**
	     * @return the instrucao4
	     */
	    public String getInstrucao4() {
	        return instrucao4;
	    }

	    /**
	     * @param instrucao4 the instrucao4 to set
	     */
	    public void setInstrucao4(String instrucao4) {
	       
	        this.instrucao4 = instrucao4;
	    }

	    /**
	     * @return the instrucao5
	     */
	    public String getInstrucao5() {
	         
	          return instrucao5;
	    }

	    /**
	     * @param instrucao5 the instrucao5 to set
	     */
	    public void setInstrucao5(String instrucao5) {
	        this.instrucao5 = instrucao5;
	    }

	    /**
	     * @return the descricoes
	     */
	    public Vector<String> getDescricoes() {
	        return descricoes;
	    }

	    /**
	     * @param descricoes the descricoes to set
	     */
	    public void setDescricoes(Vector<String> descricoes) {
	        this.descricoes = descricoes;
	    }

	    /**
	     * @return the descricao1
	     */
	    public String getDescricao1() {
	        return descricao1;
	    }

	    /**
	     * @param descricao1 the descricao1 to set
	     */
	    public void setDescricao1(String descricao1) {
	      
	        this.descricao1 = descricao1;
	    }

	    /**
	     * @return the descricao2
	     */
	    public String getDescricao2() {
	        return descricao2;
	    }

	    /**
	     * @param descricao2 the descricao2 to set
	     */
	    public void setDescricao2(String descricao2) {
	     
	        this.descricao2 = descricao2;
	    }

	    /**
	     * @return the dataDocumento
	     */
	    public Date getDataDocumento() {
	        return dataDocumento;
	    }

	    /**
	     * @param dataDocumento the dataDocumento to set
	     */
	    public void setDataDocumento(Date dataDocumento) {
	        this.dataDocumento = dataDocumento;
	    }




	
}
