package br.com;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.StringArrayConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.collections.IteratorUtils;


public class Util {

	/**
     * @param http
     * @return Os parâmetros do request
     */
    public static String getParametros(ServletRequest request) {

    	Enumeration nomeParametros = request.getParameterNames();
    	String nomeParametro = "";
    	String parametros = "";
    	while(nomeParametros.hasMoreElements()){
    		
    		if (nomeParametro.equals("")){
    			parametros = "?";
    		}
    		nomeParametro = ((String)nomeParametros.nextElement());
    		parametros += nomeParametro + "=" + request.getParameter(nomeParametro) + "&";
    	}

        return parametros;
    }
    
    /**
     * Retorna a exceção base da exceção que foi lançada.
     * @param	Throwable	e
     * @return	BaseException
     */
    public static BaseException getBaseException(Throwable e) {
        if (e instanceof BaseException) {
            return (BaseException) e;
        } else if (e instanceof ServletException) {
            if (((ServletException) e).getRootCause() != null) {
                return getBaseException(((ServletException) e).getRootCause());
            } 

            return new SystemException(e);
        } else {
            return new SystemException(e);
        }
    }
    
    /**
     * Transforma um array de objetos em uma string com os itens do array separadas por vírgula como no exemplo abaixo.<p>
     * Ex: 1, abc, @, 234, bc, &%*
     * @param	Object[]	strings
     * @return	String
     */
    public static String toCommaStr(Object[] strings) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            result.append(strings[i]).append(',').append(' ');
        }
        if (strings.length > 0) {
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }

    /**
     * Converte, de forma simples, um valor de qualquer tipo para um primitivo int.
     * @param	Object	value	valor a ser convertido.
     * @return	int				o valor convertido.
     */
    public static int toInt(Object value) {
        return ((Integer) new IntegerConverter().convert(Integer.class, value)).intValue();
    }

    /**
     * Converte, de forma simples, um valor de qualquer tipo para Integer.
     * @param	Object	valor a ser convertido.
     * @return	Integer	o valor convertido ou null para valor igual a null.
     * @see 	org.apache.commons.beanutils.converters.IntegerConverter
     */
    public static Integer toInteger(Object value) {
        return (value == null) ? null : (Integer) new IntegerConverter().convert(Integer.class, value);
    }

    /**
     * Converte o valor de uma propriedade de um bean de qualquer tipo para Integer.
     * @param	Object	objeto que possui a propriedade.
     * @param	String	nome da propriedade.
     * @return	Integer	o valor convertido ou null para valor igual a null.
     * @see 	org.apache.commons.beanutils.PropertyUtils#getSimpleProperty(Object, String)
     */
    public static Integer toInteger(Object bean, String name) {
        try {
            return toInteger(PropertyUtils.getSimpleProperty(bean, name));
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * Converte o valor de uma propriedade de um bean de qualquer tipo para Integer.
     * @param	Object	objeto que possui a propriedade.
     * @param	String	nome da propriedade.
     * @param	Integer	valor retornado quando o valor da propriedade for igual a null ou em caso de fracasso na conversão.
     * @return	Integer	o valor convertido ou defaultValue.
     */
    public static Integer toInteger(Object bean, String name, Integer defaultValue) {
        try {
            Integer value = toInteger(bean, name);
            return (value == null) ? defaultValue : value;
        } catch (Throwable e) {
            return defaultValue;
        }
    }

    /**
     * Converte o objeto de entrada em um objeto do tipo Long.
     * @param	Object	value
     * @return	Long
     */
    public static Long toLong(Object value) {
        return (value == null) ? null : (Long) new LongConverter().convert(Long.class, value);
    }

    /**
     * Converte o valor de uma propriedade de um bean de qualquer tipo para Long.
     * @param	Object	objeto que possui a propriedade.
     * @param	String	nome da propriedade.
     * @return	Long	o valor convertido ou null para valor igual a null.
     * @see		org.apache.commons.beanutils.PropertyUtils#getSimpleProperty(Object, String)
     */
    public static Long toLong(Object bean, String name) {
        try {
            return toLong(PropertyUtils.getSimpleProperty(bean, name));
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * Transforma um array de objetos em uma string com os itens do array entre aspas e separadas por vírgula como no exemplo abaixo.<p>
     * Ex: '1', 'abc', '@', '234', 'bc', '&%*'
     * @param	Object[]	string
     * @return	String
     */
    public static String toQuoteCommaStr(Object[] strings) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            result.append('\'').append(strings[i]).append('\'').append(',');
        }
        if (strings.length > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * Converte um array de ids em um objeto do tipo Set dada uma determinada classe.
     * @param	String[]	ids
     * @param	Class		beanClass
     * @return	Set
     */
    public static Set toSet(Object[] ids, Class beanClass) {
        try {
            if (ids == null) {
                return null;
            }
            Set<Object> result = new HashSet<Object>();
            Object obj = null;
            for (int i = 0; i < ids.length; i++) {
                obj = beanClass.newInstance();
                Util.setProp(obj, "id", ids[i]);
                result.add(obj);
            }
            return result;
        } catch (Throwable e) {
            throw Util.getBaseException(e);
        }
    }
    
    /**
     * Converte um array de ids em um objeto do tipo List dada uma determinada classe.
     * @param	String[]	ids
     * @param	Class		beanClass
     * @return	Set
     */
    public static List toList(Object[] ids, Class beanClass) {
        try {
            if (ids == null) {
                return null;
            }
            List<Object> result = new ArrayList<Object>();
            Object obj = null;
            for (int i = 0; i < ids.length; i++) {
                obj = beanClass.newInstance();
                Util.setProp(obj, "id", ids[i]);
                result.add(obj);
            }
            return result;
        } catch (Throwable e) {
            throw Util.getBaseException(e);
        }
    }
    
    
    /**
     * Cria um objeto do tipo Set de uma determinada classe setando os seus respectivos valores (attribute).
     * @param	String[]	values
     * @param	Class		beanClass
     * @param	String		attribute
     * @return	Set
     */
    public static Set toSet(String[] values, Class beanClass, String attribute) {
        try {
            if (values == null) {
                return null;
            }
            Set result = new HashSet();
            Object obj = null;
            for (int i = 0; i < values.length; i++) {
                obj = beanClass.newInstance();
                Util.setProp(obj, attribute, values[i]);
                result.add(obj);
            }
            return result;
        } catch (Throwable e) {
            throw Util.getBaseException(e);
        }
    }
    
    /**
     * Converte um List de objetos em um Set da determinada classe.
     * @param	List		beanList
     * @param	Class		beanClass
     * @return	Set
     */
    public static Set toSet(List beanList) {
        try {
        	Set result = new HashSet();
        	if( beanList!=null){
        		for(Object object : beanList){
        			result.add(object);
        		}
        	}
            return result;
        } catch (Throwable e) {
            throw Util.getBaseException(e);
        }
    }

    /**
     * Converte, de forma simples, um valor de qualquer tipo para String.
     * @param	Object	valor a ser convertido.
     * @return 	String	o valor convertido ou null para valor igual a null.
     * @see 	org.apache.commons.beanutils.converters.StringConverter
     */
    public static String toString(Object value) {
        return (value == null) ? null : (String) new StringConverter().convert(String.class, value);
    }

    /**
     * Converte o valor de uma propriedade de um bean de qualquer tipo para String.
     * @param	Object	objeto que possui a propriedade.
     * @param	String	nome da propriedade.
     * @return	String	o valor convertido ou null para valor igual a null.
     * @see 	org.apache.commons.beanutils.PropertyUtils#getSimpleProperty(Object, String)
     */
    public static String toString(Object bean, String name) {
        try {
            return toString(PropertyUtils.getSimpleProperty(bean, name));
        } catch (Exception e) {
            throw new SystemException(e);
        }

    }

    /**
     * Converte o valor de uma propriedade de um bean de qualquer tipo para String.
     * @param	objeto que possui a propriedade.
     * @param	nome da propriedade.
     * @param	defaultValue	valor retornado quando o valor da propriedade for igual a null ou em caso de fracasso na conversão.
     * @return	String	o valor convertido ou defaultValue
     */
    public static String toString(Object bean, String name, String defaultValue) {
        try {
            String value = toString(bean, name);
            return ((value == null) || value.trim().equals("")) ? defaultValue : value;
        } catch (Throwable e) {
            return defaultValue;
        }
    }

    /**
     * Converte um objeto em um array de string.
     * @param	Object	value
     * @return	String[]
     */
    public static String[] toStringArray(Object value) {
        return (value == null) ? null : (String[]) new StringArrayConverter().convert(String[].class, value);
    }

    /**
     * Converte um determinado atributo de um objeto em um array de string.
     * @param	Object	bean
     * @param	String	name
     * @return	String[]
     */
    public static String[] toStringArray(Object bean, String name) {
        try {
            return toStringArray(PropertyUtils.getSimpleProperty(bean, name));
        } catch (Throwable e) {
            throw Util.getBaseException(e);
        }
    }
    
    /**
     * Converte um array de string em uma string.
     * @param	String[]	arr
     * @return	String
     */
    public static String arrayStringToString(String[] arr){
    	String temp = "";
    	for(int i=0; i < arr.length; i++){
    		temp+=arr[i]+",";
    	}
    	temp=temp.substring(0,temp.lastIndexOf(','));
    	return temp;
    }

    /**
     * Converte valores de um determinado atributo (name) de um objeto em um array de string. 
     * @param	Set		collection
     * @param	String	name
     * @return	String[]
     */
    public static String[] toStringArray(Set collection, String name) {
        try {
            String[] result = null;
            if (collection != null) {
                result = new String[collection.size()];
                int i = 0;
                for (Iterator it = collection.iterator(); it.hasNext();) {
                    result[i] = Util.toString(it.next(), name);
                    i++;
                }
            }
            return result;
        } catch (Throwable e) {
            throw Util.getBaseException(e);
        }
    }
    
    /**
     * Retorna a posição que está uma determinada string em um array.
     * @param	String[]	array
     * @param	String		value
     * @return	int
     */
    public static int searchArrayIndex(String [] array, String value){
        int index = -1;
        
        for(int i = 0; i < array.length; i++){
            if (array[i].equals(value))
                return i;
        }
        
        return index;
    }

    /**
     * Converte qualquer Collection para uma coleção do tipo List.
     * @param	Collection	collection a ser convertida
     * @return	List		a lista obtida
     */
    public static List toList(Collection collection) {
    	List list = null;
    	if(collection != null){
    		list = IteratorUtils.toList(collection.iterator());
    	}
        return list;
        
    }
    
    /**
     * TODO Testar e documentar este método.
     * @param todos
     * @param atributoPai
     * @return
     */
    private static List ordenaObjetosPorPai(List todos,String atributoPai){
    	List<Object> ordenada = new ArrayList<Object>();
		boolean achei = false;
		Object objeto;
		Object subPai;
		for(int x=0;x<todos.size();x++){
			achei=false;
			objeto = todos.get(x);
			if(retornaValor(objeto,atributoPai)!=null){
				if(ordenada.size()>0){
					for(int z=0;z<ordenada.size();z++){
						subPai = ordenada.get(z);
						if(retornaValor(subPai,atributoPai)!=null && ((Long)(retornaValor(retornaValor(objeto,atributoPai),"id"))).longValue()<=((Long)retornaValor(retornaValor(subPai,atributoPai),"id")).longValue()){
							ordenada.add(z,todos.get(x));
							achei = true;
							break;
						}
						
						for(int t=z;t<ordenada.size();t++){
							subPai = ordenada.get(t);
							if(retornaValor(subPai,atributoPai)!=null && ((Long)(retornaValor(retornaValor(objeto,atributoPai),"id"))).longValue()<=((Long)retornaValor(retornaValor(subPai,atributoPai),"id")).longValue()){
								ordenada.add(t,todos.get(x));
								achei = true;
								break;
							}
						}
						if(!achei){
							ordenada.add(todos.get(x));
						}
						break;
					}
				}else{
					ordenada.add(todos.get(x));
				}
			}else{
				ordenada.add(0,todos.get(x));
			}
		}
		return ordenada;
    }

    /**
     * Retorna o valor de um atributo de um objeto.
     * @param 	Object	objeto
     * @param 	String	campo
     * @return	Object
     */
    public static Object retornaValor(Object objeto,String campo){
	    Object valor = new Object();
	    if (PropertyUtils.isReadable(objeto, campo)) {
            try {
                valor = PropertyUtils.getSimpleProperty(objeto, campo);
            } catch (Exception e) {
                throw new ApplicationException("Método não encontrado");
            }
        }
	    return valor;
	}
    
    /**
     * Configura um valor de uma propriedade de um determinado bean.
     * @param	Object	bean
     * @param	String	name
     * @param	Object	value
     */
    public static void setProp(Object bean, String name, Object value) {
        try {
            if (value != null) {
                Class type = PropertyUtils.getPropertyType(bean, name);
                Converter converter = ConvertUtils.lookup(type);
                if (converter != null) {
                    value = converter.convert(type, value);
                }
            }
            PropertyUtils.setProperty(bean, name, value);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    /**
	 * Converte a data para o tipo Timestamp
	 * 
	 * @param data
	 * @return Date
	 */
	public static Timestamp converterDateParaTimestamp(Date data) {
		if (data == null) {
			return null;
		}
		return new Timestamp(data.getTime());
	}
	
    
	/**
	 * Método que completa uma string com um caractere 
	 * 
	 * @param dado - a string original
	 * @param tamanho - no numero de caracteres que a nova string devera ter
	 * @param lado - o lado em quue o  numero n de caracteres sera incluido
	 * @param caractere - o caractere que sera adicionado a string original
	 * @return 
	 */
	private static final char DIREITA = 'D';
	private static final char ESQUERDA = 'E';
	
	public static String completarString(String dado,int tamanho,char lado,String caractere){
		
		String completarDadoRetorno = "";

		if(dado == null){
			//System.out.println("ERRO Dado Veio vazio !");
			completarDadoRetorno = completarString("",tamanho,lado,caractere);
		}
		else if(dado != null && dado.length() > tamanho){
			completarDadoRetorno = dado.substring(0,tamanho);
		}
		else{
			int caracteresFaltando = tamanho - dado.length();
			if(lado == DIREITA){
			 	completarDadoRetorno = dado;
			}
			for(int i=0;i<caracteresFaltando;i++){
				if(lado == ESQUERDA){
					completarDadoRetorno += caractere;
				}
				else if(lado == DIREITA){
					completarDadoRetorno += caractere;
				}
			}
			if(lado == ESQUERDA){
			 	completarDadoRetorno += dado;
			}
		}
		return completarDadoRetorno;
	}
	
	/**
     * Salva um arquivo em um determinado diretório.
     * @param     String    nomeArquivo
     * @param     byte[]    arquivo
     */
    public static void salvarArquivo(String nomeArquivo, byte[] arquivo){
        try{
            FileOutputStream file = new FileOutputStream(nomeArquivo);
            file.write(arquivo);
            file.flush();
            file.close();
        }catch(Exception exception) {
            exception.printStackTrace(System.out);
        }
    }
    
    /**
     * Remove um caracter de uma String.
     * @param s
     * @param c
     * @return
     */
    public static String removeChar(String s, char c) {

		   String r = "";

		   for (int i = 0; i < s.length(); i ++) {
		      if (s.charAt(i) != c) r += s.charAt(i);
		   }

		   return r;
	}
    
    /**
	 * Calcula a idade
	 * @param dataNascimento
	 * @return quantidade de tempo entre as duas datas em anos
	 * @throws Exception
	 */
	public static int calcularIdade(Date dataNascimento)throws Exception{ 
		
		 int idadeMes = 0;
		 int idadeAno = 0;
		 int idadeDia = 0;
		 
		 Calendar cal = new GregorianCalendar();
		 cal.setTime(new Date());
		 
		 if(dataNascimento != null){// caso tenha uma data de nascimento commeçar a calcular a idade da pessoa em anos
		 	
			Calendar niver = new GregorianCalendar();
			niver.setTime(dataNascimento);
			//calculando os anos da idade se for necessario
			if(cal.get(Calendar.YEAR) - niver.get(Calendar.YEAR) > 0 || cal.get(Calendar.YEAR) - niver.get(Calendar.YEAR) == 0){
				idadeAno = cal.get(Calendar.YEAR) - niver.get(Calendar.YEAR);//calculando a idade em anos
				if(cal.get(Calendar.MONTH) < niver.get(Calendar.MONTH)){
					//System.out.println("entrou caso 1");
					idadeAno--;
					if(cal.get(Calendar.DAY_OF_MONTH) < niver.get(Calendar.DAY_OF_MONTH)){
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 11;//calculando os meses
						//idadeDia = calculaDia(niver.get(Calendar.MONTH)+1,cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),niver.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.YEAR),niver.get(Calendar.YEAR));	
					}
					else if(cal.get(Calendar.DAY_OF_MONTH) > niver.get(Calendar.DAY_OF_MONTH)){
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;//calculando os meses
						//idadeDia = cal.get(Calendar.DAY_OF_MONTH) - niver.get(Calendar.DAY_OF_MONTH);
					}
					else{
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;
					} 
				}
				else if(cal.get(Calendar.MONTH) == niver.get(Calendar.MONTH)){//verificando dias
					//System.out.println("entrou caso 2");
					//idadeMes = 0; 
					if(cal.get(Calendar.DAY_OF_MONTH) < niver.get(Calendar.DAY_OF_MONTH)){//nao niver
						idadeMes = 11;
						idadeAno--;
						//idadeDia = calculaDia(niver.get(Calendar.MONTH)+1,cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),niver.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.YEAR),niver.get(Calendar.YEAR));		
					}
					else if(cal.get(Calendar.DAY_OF_MONTH) > niver.get(Calendar.DAY_OF_MONTH)){//fez niver
						idadeMes = 0;
						//idadeDia = cal.get(Calendar.DAY_OF_MONTH) - niver.get(Calendar.DAY_OF_MONTH);
					} 
					else{
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;
					}    
				}
				else{
					//System.out.println("entrou caso 3");
					idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH);
					if(cal.get(Calendar.DAY_OF_MONTH) < niver.get(Calendar.DAY_OF_MONTH)){//nao niver mes
						idadeMes--;
						//idadeDia = calculaDia(niver.get(Calendar.MONTH)+1,cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),niver.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.YEAR),niver.get(Calendar.YEAR));	
					}
					else if(cal.get(Calendar.DAY_OF_MONTH) > niver.get(Calendar.DAY_OF_MONTH)){
						//idadeDia = cal.get(Calendar.DAY_OF_MONTH) - niver.get(Calendar.DAY_OF_MONTH);
					}
					else{
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;
					}  
				}
			}
			else{//caso a diferença seja negativa (idade colocada errada) anos fica zerado
				idadeAno = 0;
				idadeMes = 0;
			}
		}
		 return idadeAno;
	}
	
    /**
	 * Calcula a idade
	 * @param dataNascimento
	 * @return quantidade de tempo entre as duas datas em anos
	 * @throws Exception
	 */
	public static int calcularIdade(String dataNascimento)throws Exception{ 
		
		 int idadeMes = 0;
		 int idadeAno = 0;
		 int idadeDia = 0;
		 
		 Date data = new Date();
		 SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String hoje = dataFormat.format(data).toString();
		 
		 String ano = hoje.substring(6,10);
		 String mes = hoje.substring(3,5); 
		 String dia = hoje.substring(0,2);
		 Calendar cal = new GregorianCalendar(Integer.parseInt(ano),Integer.parseInt(mes)-1,Integer.parseInt(dia));
		 
		 
		 if(dataNascimento != null){// caso tenha uma data de nascimento commeçar a calcular a idade da pessoa em anos
		 	ano = dataNascimento.substring(6,10);
			mes = dataNascimento.substring(3,5);
			dia = dataNascimento.substring(0,2);
			Calendar niver = new GregorianCalendar(Integer.parseInt(ano),Integer.parseInt(mes)-1,Integer.parseInt(dia));
		    //calculando os anos da idade se for necessario
			if(cal.get(Calendar.YEAR) - niver.get(Calendar.YEAR) > 0 || cal.get(Calendar.YEAR) - niver.get(Calendar.YEAR) == 0){
				idadeAno = cal.get(Calendar.YEAR) - niver.get(Calendar.YEAR);//calculando a idade em anos
				if(cal.get(Calendar.MONTH) < niver.get(Calendar.MONTH)){
					//System.out.println("entrou caso 1");
					idadeAno--;
					if(cal.get(Calendar.DAY_OF_MONTH) < niver.get(Calendar.DAY_OF_MONTH)){
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 11;//calculando os meses
						//idadeDia = calculaDia(niver.get(Calendar.MONTH)+1,cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),niver.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.YEAR),niver.get(Calendar.YEAR));	
					}
					else if(cal.get(Calendar.DAY_OF_MONTH) > niver.get(Calendar.DAY_OF_MONTH)){
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;//calculando os meses
						//idadeDia = cal.get(Calendar.DAY_OF_MONTH) - niver.get(Calendar.DAY_OF_MONTH);
					}
					else{
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;
					} 
				}
				else if(cal.get(Calendar.MONTH) == niver.get(Calendar.MONTH)){//verificando dias
					//System.out.println("entrou caso 2");
					//idadeMes = 0; 
					if(cal.get(Calendar.DAY_OF_MONTH) < niver.get(Calendar.DAY_OF_MONTH)){//nao niver
						idadeMes = 11;
						idadeAno--;
						//idadeDia = calculaDia(niver.get(Calendar.MONTH)+1,cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),niver.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.YEAR),niver.get(Calendar.YEAR));		
					}
					else if(cal.get(Calendar.DAY_OF_MONTH) > niver.get(Calendar.DAY_OF_MONTH)){//fez niver
						idadeMes = 0;
						//idadeDia = cal.get(Calendar.DAY_OF_MONTH) - niver.get(Calendar.DAY_OF_MONTH);
					} 
					else{
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;
					}    
				}
				else{
					//System.out.println("entrou caso 3");
					idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH);
					if(cal.get(Calendar.DAY_OF_MONTH) < niver.get(Calendar.DAY_OF_MONTH)){//nao niver mes
						idadeMes--;
						//idadeDia = calculaDia(niver.get(Calendar.MONTH)+1,cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),niver.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.YEAR),niver.get(Calendar.YEAR));	
					}
					else if(cal.get(Calendar.DAY_OF_MONTH) > niver.get(Calendar.DAY_OF_MONTH)){
						//idadeDia = cal.get(Calendar.DAY_OF_MONTH) - niver.get(Calendar.DAY_OF_MONTH);
					}
					else{
						idadeMes = cal.get(Calendar.MONTH) - niver.get(Calendar.MONTH) + 12;
					}  
				}
			}
			else{//caso a diferença seja negativa (idade colocada errada) anos fica zerado
				idadeAno = 0;
				idadeMes = 0;
			}
		}
		 return idadeAno;
	}
	
	/**
	 * 
	 * @param ndias
	 * @return dataRetorno
	 */
	public static String dataAnteriorPosterior(int ndias){
		String dataRetorno = null;
		
		Date data = new Date();
		SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtual = dat.format(data); 
		
		Calendar cal = new GregorianCalendar(Integer.parseInt(dataAtual.substring(6,10)),
				Integer.parseInt(dataAtual.substring(3,5))-1,
				Integer.parseInt(dataAtual.substring(0,2)));
		
		if((cal.get(Calendar.DAY_OF_YEAR)+(ndias)) > 365){
			cal.roll(Calendar.YEAR, 1); 
		}
		cal.roll(Calendar.DAY_OF_YEAR, ndias);
		
		dataRetorno = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
	
		return dataRetorno;
	}
	
	public static String dataAtual(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy 'às' kk:mm:ss");
		return df.format(new Date());
	}
	
	 /* Ler o arquivo do disco para efetuar sua gravação no banco e deleta.
     * @param	String	nomeArquivo
     * @param	String	diretorio
     * @throws	Exception
     */
    public static byte[] lerArquivo(String nomeArquivo, String diretorio, boolean deletar) throws FileNotFoundException, IOException{
        int tamanhoFile = 0;
        File file = new File(diretorio + nomeArquivo);
        FileInputStream fis = new FileInputStream(file);
        Long tamanhoAux = new Long(file.length());
        tamanhoFile = tamanhoAux.intValue();
        byte[] ret = new byte[tamanhoFile];
        fis.read(ret, 0, tamanhoFile);
        fis.close();
        if(deletar){
        	file.delete();
        }
        return ret;
    }
    
    /**
     * Ler o arquivo do disco para efetuar sua gravação no banco e deleta.
     * @param	String	nomeArquivo
     * @param	String	diretorio
     * @throws	Exception
     */
    public static byte[] lerArquivo(String caminhoArquivo, boolean deletar) throws FileNotFoundException, IOException{
        int tamanhoFile = 0;
        File file = new File(caminhoArquivo);
        FileInputStream fis = new FileInputStream(file);
        Long tamanhoAux = new Long(file.length());
        tamanhoFile = tamanhoAux.intValue();
        byte[] ret = new byte[tamanhoFile];
        fis.read(ret, 0, tamanhoFile);
        fis.close();
        if(deletar){
        	file.delete();
        }
        return ret;
    }
    
    /**
     * Verifica se o arquivo existe no caminho especificado.
     * @param	String	nomeArquivo
     * @throws	Exception
     */
    public static boolean verificaExistenciaArquivo(String caminhoArquivo){
        
    	boolean existe = true;
    	FileInputStream fis = null;
    	File file = null;
    	
    	try{
    		file = new File(caminhoArquivo);
    		fis = new FileInputStream(file);
    	}catch (Exception e) {
    		existe = false;
		}finally{
			file = null;
			fis = null;
		}
		
		return existe;
    }
    
    /**
     * Deleta uma arquivo no disco.
     * @param	String	nomeArquivo
     * @param	String	diretorio
     * @throws	Exception
     */
    public static void deletaArquivo(String caminhoArquivo) throws FileNotFoundException, IOException{
        File file = new File(caminhoArquivo);
        if(file != null && file.exists()){
        	file.delete();
        }
    }
    
    /**
     * Ler o arquivo de uma url.
     * @param	String	nomeArquivo
     * @param	String	diretorio
     * @throws	Exception
     */
    public static String lerArquivoURL(URL url) throws FileNotFoundException, IOException{
    	
    	// cria o objeto url
		// cria o objeto httpurlconnection
		HttpURLConnection connection =
			(HttpURLConnection) url.openConnection();

		// seta o metodo
		connection.setRequestProperty("Request-Method","GET");

		// seta a variavel para ler o resultado
		connection.setDoInput(true);
		connection.setDoOutput(false);

		// conecta com a url destino
		connection.connect();

		// abre a conexão pra input
		BufferedReader br =
			new BufferedReader(new InputStreamReader(connection.getInputStream()));

		// le ate o final
		StringBuffer newData = new StringBuffer(20000);
		String s = "";
		while (null != ((s = br.readLine()))) {
			newData.append(s);
		}
		
		br.close();
		return newData.toString();		
    }
    
    public static byte[] descompactarImagem(byte[] imagem) {   
        Inflater decompressor = new Inflater();   
        decompressor.setInput(imagem);   
           
        ByteArrayOutputStream bos = new ByteArrayOutputStream(imagem.length);   
           
        // Decompress the data   
        byte[] buf = new byte[1024];   
        while (!decompressor.finished()) {   
            try {   
                int count = decompressor.inflate(buf);   
                bos.write(buf, 0, count);   
            } catch (Exception e) {   
            }   
        }   
        try {   
            bos.close();   
            return bos.toByteArray();   
        } catch (Exception e) {   
            return null;   
        }   
	}   
	  
    public  static byte[] compactarImagem(byte[] imagem) {   
        Deflater compressor = new Deflater();   
        compressor.setLevel(Deflater.BEST_COMPRESSION);   
           
        compressor.setInput(imagem);   
        compressor.finish();   
           
        ByteArrayOutputStream bos = new  ByteArrayOutputStream(imagem.length);   
           
        byte[] buf = new byte[1024];   
        while (!compressor.finished()) {   
            int count = compressor.deflate(buf);   
            bos.write(buf, 0, count);   
        }   
        try {   
            bos.close();   
            return bos.toByteArray();             
        } catch (Exception e) {   
            //System.out.println("Erro: "+e.getMessage());   
            return null;   
        }         
    }
    
    /**
     * Retorna InputStream de uma URL.
     * @param	String	nomeArquivo
     * @param	String	diretorio
     * @throws	Exception
     */
    public static InputStream getInputStream(URL url) throws FileNotFoundException, IOException{
    	
    	// cria o objeto url
		// cria o objeto httpurlconnection
		HttpURLConnection connection =
			(HttpURLConnection) url.openConnection();

		// seta o metodo
		connection.setRequestProperty("Request-Method","GET");

		// seta a variavel para ler o resultado
		connection.setDoInput(true);
		connection.setDoOutput(false);

		// conecta com a url destino
		connection.connect();

		return connection.getInputStream();
	
    }
    
	public static String formatarDataParaBusca(String data){
		String data2 = null;
		if (data.length()==10){
			data2 = data.substring(6,10)+"0"+data.substring(3,5)+"0"+data.substring(0,2);
		}
		return data2;
	}
	
	
	public static boolean isNumber(String value){
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(value);
		return  m.matches();
	}
	
	public static String criarNomeRandomico(){
		UUID uuid = UUID.randomUUID();  
		String myRandom = uuid.toString();  
		return myRandom.substring(0,20); 
	}
}
