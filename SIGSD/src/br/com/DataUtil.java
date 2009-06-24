package br.com;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DataUtil {
	
	/**
	 * metodo que retorna o numero de dias do mes de uma data
	 * @param data Date
	 * @return maior dia do mes
	 */
	public static int maiorNDiasMesData(Date data){
	
		SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dat.format(data);
		
		Calendar cal = new GregorianCalendar(Integer.parseInt(dataFormatada.substring(6,10)),
				Integer.parseInt(dataFormatada.substring(3,5))-1,
				Integer.parseInt(dataFormatada.substring(0,2)));

		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
		 
	/**
	 * metodo que retorna o numero de dias do mes de uma data
	 * @param data String dd/mm/yyyy
	 * @return maior dia do mes
	 */
	public static int maiorNDiasMesData(String data){
	
		String dataFormatada = data;
		
		Calendar cal = new GregorianCalendar(Integer.parseInt(dataFormatada.substring(6,10)),
				Integer.parseInt(dataFormatada.substring(3,5))-1,
				Integer.parseInt(dataFormatada.substring(0,2)));

		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Método que transforma uma data em string para uma do tipo date
	 * @param data
	 * @return data tranformada
	 */
	public static Date converteParaDate(String data){
		Date dataRetorno = null;
		if(data != null){
			try{
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
				dataRetorno = dataFormat.parse(data);
			}
			catch(Exception e){ 
				System.out.println("Erro: Não foi possivel converter a data recebida ("+data+") !");
			}
		}
		return dataRetorno;
	}
	
	/**
	 * Método que transforma uma dataHora em string para uma do tipo date (só que com apenas mês e ano)
	 * e se é data início ou fim 
	 * @param data
	 * @return data transformada do tipo Date
	 */
	public static Date converteParaDateMesAno(String data){
		Date dataRetorno = null;
		if(data != null){
			SimpleDateFormat dataFormat1 = new SimpleDateFormat("MM/yyyy");			
			try {			
				dataRetorno = dataFormat1.parse(data);												 
			} catch (Exception e) {
				System.out.println("Erro: Não foi possivel converter a data recebida ("+data+") !");
				}
			}
		return dataRetorno;
	}
	
	/**
	 * Método que transforma uma dataHora em string para uma do tipo date
	 * @param data
	 * @return data tranformada
	 */
	public static Date converteDataHoraParaDate(String data){
		Date dataRetorno = null;
		if(data != null){
			try{
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				dataRetorno = dataFormat.parse(data);
			}
			catch(Exception e){ 
				System.out.println("Erro: Não foi possivel converter a data recebida !");
			}
		}
		return dataRetorno;
	}	
	
	/**
	 * Método que transforma uma dataHora em date para uma do tipo String
	 * @param data
	 * @return data tranformada
	 */
	public static String converteDataHoraParaString(Date data){
		String dataRetorno = null;
		if(data != null){
			try{
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				dataRetorno = dataFormat.format(data);
			}
			catch(Exception e){ 
				System.out.println("Erro: Não foi possivel converter a data recebida !");
			}
		}
		return dataRetorno;
	}
	
	
	/**
	 * Método que compara se uma data encontrase contida no intervalo de outras duas datas
	 * @param dataComparacao
	 * @param dataInicio
	 * @param dataFinal
	 * @return
	 */
	public static boolean encontraEntreDatas(Date dataComparacao,Date dataInicio,Date dataFinal){
		boolean estaNoIntervalo = false;
		if(dataComparacao != null && dataInicio != null && dataFinal != null){
			if((dataComparacao.after(dataInicio) || dataComparacao.equals(dataInicio)) && (dataComparacao.before(dataFinal) || dataComparacao.equals(dataFinal))){
				estaNoIntervalo = true;
			}
		}
		return estaNoIntervalo;
	}
	
	/**
	 * Metodo que formata uma data string dd/mm/yyyy para yyy-mm-dd para salvar no banco
	 * @param data
	 * @return
	 */
	public static String converteParaStringBanco(String data){
		String dataRetorno = null;
		if(data != null && data.length() == 10){
			try{
				dataRetorno = data.substring(6,10);
				dataRetorno += "-";
				dataRetorno += data.substring(3,5);
				dataRetorno += "-";
				dataRetorno += data.substring(0,2);
			}
			catch(Exception e){ 
				System.out.println("Erro: Não foi possivel converter a data recebida !");
			}
		}
		return dataRetorno;
	}
	
	
	/**
	 * método que pega a data atual do sistema
	 * @return
	 */
	public static String pegarDataAtual(){
		String dataRetorno = null;
		try{
			Date data = new Date();
			SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
			dataRetorno = dataFormat.format(data);
		}
		catch(Exception e){ 
			System.out.println("Erro: Não foi possivel converter a data recebida !");
		}
		return dataRetorno;
	}
	
	/**
	 * método que pega a data atual completa do sistema
	 * @return
	 */
	public static String pegarDataAtualCompleta(){
		String dataRetorno = null;
		try{
			Date data = new Date();
			SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
			dataRetorno = dataFormat.format(data);
		}
		catch(Exception e){ 
			System.out.println("Erro: Não foi possivel converter a data recebida !");
		}
		return dataRetorno;
	}
	
	/**
	 * Retorna a diferenca entre duas datas.
	 * @param 	Date 	(Data "DD/MM/AAAA HH:MM")
	 * @param 	Date 	(Data "DD/MM/AAAA HH:MM")
	 * @param 	int 	<br> Calendar.DAY_OF_MONTH - retorna a diferença em dias;
	 * 					<br> Calendar.WEEK_OF_MONTH - retorna a diferença em semanas; 
	 * 					<br> Calendar.MONTH - retorna a diferença em meses;
	 * 					<br> Calendar.DAY_OF_YEAR - retorna a diferença em anos.
	 * @param 	TimeZone 	("GMT-03:00")
	 * @return 	int
	 */
	public static int diferencaDatas(Date sdate1, Date sdate2, int tipoRetorno, TimeZone tz){
        Date date1 = sdate1;
        Date date2 = sdate2;
        
        Calendar cal1 = null; 
        Calendar cal2 = null;
        
        if (tz == null)
        {
          cal1=Calendar.getInstance(); 
          cal2=Calendar.getInstance(); 
        }
        else
        {
          cal1=Calendar.getInstance(tz); 
          cal2=Calendar.getInstance(tz); 
        }
        
        // data diferente pode ter offset diferente
        cal1.setTime(date1);          
        long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
        
        cal2.setTime(date2);
        long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
        
        // Usa calculos com inteiros, trunca os decimais
        int hr1   = (int)(ldate1/3600000); //60*60*1000
        int hr2   = (int)(ldate2/3600000);

        int days1 = hr1/24;
        int days2 = hr2/24;
        
        int dateDiff  = days2 - days1;
        int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK))<0 ? 1 : 0;
        int weekDiff  = dateDiff/7 + weekOffset; 
        int yearDiff  = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); 
        int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

        if(tipoRetorno == Calendar.DAY_OF_MONTH || tipoRetorno == Calendar.DAY_OF_WEEK || tipoRetorno == Calendar.DAY_OF_WEEK_IN_MONTH || tipoRetorno == Calendar.DAY_OF_YEAR){
            return dateDiff;
        }else if(tipoRetorno == Calendar.WEEK_OF_MONTH || tipoRetorno == Calendar.WEEK_OF_YEAR){
            return weekDiff;
        }else if(tipoRetorno == Calendar.MONTH){
            return monthDiff;
        }else if(tipoRetorno == Calendar.YEAR){
            return yearDiff;
        }else{
            return dateDiff;
        }
    }
	    
    /**
	 * 
	 * @author Leonardo
	 * @return df.format(new Date())
	 */
	public static String dataAtual(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		return df.format(new Date());
	}
	
	 /**
	  * Metodo que calcula a quantidade de anos entre duas datas (Apropriado para idade) 
	 * @param dataNasc - dd/mm/yyyy
	 * @return idade
	 */
	public static int calculaIdade(String dataNasc,String dataReferencia){
        DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Date dataNascConvertida = null;
        try {
        	dataNascConvertida = sdf.parse(dataNasc);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        Calendar dataAniversario = new GregorianCalendar();
        dataAniversario.setTime(dataNascConvertida);
        
        
        sdf = new SimpleDateFormat("dd/mm/yyyy");
        Date dataCom = null;
        try {
        	dataCom= sdf.parse(dataReferencia);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        Calendar dataComparacao = new GregorianCalendar();
        dataComparacao.setTime(dataCom);
        
        
       // Obtém a idade baseado no ano
        int idade = dataComparacao.get(Calendar.YEAR) - dataAniversario.get(Calendar.YEAR);
        dataAniversario.add(Calendar.YEAR, idade);
        if (dataComparacao.before(dataAniversario)) {
            idade--;
        }
        return idade;   
    }
	
	/**
	 * Método para pegar(montar) um java.sql.date da uniao data (dd/mm/yyy) + hora (hh:mi)
	 * @param dataUtil
	 * @return java.sql.Date
	 */
	public static Timestamp transformaraDataHoraDate(String data,String hora) {
	  	if(data == null || hora == null){
	  		return null;
	  	}
	    return Timestamp.valueOf(converteParaStringBanco(data)+" "+hora+":00");
	}
	
	/**
	 * Método para pegar(montar) um java.sql.date de uma java.util.date, conforme o seu format
	 * @param utilDate
	 * @param format
	 * @return
	 */
	public static java.sql.Date getSqlDate(java.util.Date utilDate) {
	  	if(utilDate == null){
	  		return null;
	  	}
	    return java.sql.Date.valueOf(utilDateToStr(utilDate, "yyyy-MM-dd"));
	}
	public static String utilDateToStr(java.util.Date utilDate, String format) {
	    String retorno = null;
	
	    if ((null != utilDate) && (null != format)) {
	
	      SimpleDateFormat formater = new SimpleDateFormat(format);
	      retorno = formater.format(utilDate);
	    }
	
	    return retorno;
	}
	
	/**
	 * Método para pegar um java.util.date de um java.sql.date
	 * @param dateSql
	 * @return java.util.Date
	 */
	public static java.util.Date getUtilDateHour(java.sql.Date sqlDate) throws java.text.ParseException{
		if(sqlDate == null){
	  		return null;
	  	}
		
		java.util.Date utilDate =  null;
		
		try {
			
			String dateSqlStr = sqlDateToStr(sqlDate, "dd-MM-yyyy hh:mm:ss");
			DateFormat f = new SimpleDateFormat(dateSqlStr);
			utilDate = (java.util.Date)f.parse(f.format(dateSqlStr));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return utilDate;
		
	}
	/**
	 * Método para pegar um java.util.date de um java.sql.date no formato 
	 * @param sqlDate
	 * @return
	 * @throws java.text.ParseException
	 */
	public static java.util.Date getUtilDate(java.sql.Date sqlDate) throws java.text.ParseException{
		if(sqlDate == null){
	  		return null;
	  	}
		
		java.util.Date utilDate =  null;
		
		try {
			
			String dateSqlStr = sqlDateToStr(sqlDate, "dd-MM-yyyy");
			DateFormat f = new SimpleDateFormat(dateSqlStr);
			utilDate = (java.util.Date)f.parse(f.format(dateSqlStr));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return utilDate;
		
	}
	public static String sqlDateToStr(java.sql.Date sqlDate, String format) {
	    String retorno = null;
	
	    if ((null != sqlDate) && (null != format)) {
	
	      SimpleDateFormat formater = new SimpleDateFormat(format);
	      retorno = formater.format(sqlDate);
	    }
	
	    return retorno;
	}
	

	/**
	 * metodo que navega n dias para mais (+) o menos (-) a partir de uma data fornecida em string dd/mm/yyyy
	 * 
	 * @param data
	 * @param quantidadesDias
	 * @return
	 */
	public static String navegarData(String data,int quantidadesDias){
		
		String dataNavegada = data;
		
		Calendar cal = new GregorianCalendar(Integer.parseInt(dataNavegada.substring(6,10)),
				Integer.parseInt(dataNavegada.substring(3,5))-1,
				Integer.parseInt(dataNavegada.substring(0,2)));

		if(cal.get(Calendar.DAY_OF_YEAR)+ quantidadesDias > 365){
			cal.roll(Calendar.YEAR, 1);
		}
		
		cal.roll(Calendar.DAY_OF_YEAR, quantidadesDias);
		
		dataNavegada = 
			cal.get(Calendar.YEAR)+"/"+
		Util.completarString(Integer.toString(cal.get(Calendar.MONTH)+1),2,'E',"0")+"/"+
		Util.completarString(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)),2,'E',"0");
		
		return dataNavegada;
	}
	
	
	/**
	 * metodo que retorna o dia da semana 
	 * dom=1,seg=2,ter=3,qua=4,qui=5,sex=6,sab=7 
	 * 
	 * @param data no formato dd/MM/yyyy
	 * @return
	 */
	public static int pegarDiaSemanaData(String data) throws ParseException{
		
		int diaSemana = 0;
		try{
				
			if(data != null && !"".equals(data.trim())){
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date = df.parse(data);
				Calendar cal = new GregorianCalendar();
				cal.setTime(date);
				diaSemana = cal.get(Calendar.DAY_OF_WEEK);
			}
		}catch (ParseException e) {
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
		}
		return diaSemana;
	}
	
	
	/**
	 * Método que checa se a data passada corresponde a data atual
	 * 
	 * @param data
	 * @return
	 */
	public static boolean dataHoje(String data){
		
		boolean eHoje = false;
		if(pegarDataAtual().equals(data)){
			eHoje = true;
		}
		return eHoje;
	}
	
	
	/**
	 * NOVO 
	 * 
	 * Método para pegar(montar) um java.sql.date da uniao data (dd/mm/yyy) + hora (hh:mi)
	 * @param dataUtil
	 * @return java.sql.Date
	 */
	public static Timestamp transformarDataHoraDate(String data,String hora) {
	  	if(data == null || hora == null){
	  		return null;
	  	}
	    return Timestamp.valueOf(converteParaStringBanco(data)+" "+hora+":00");
	}
	
	
	
	public static Collection<String> montarHorariosConsulta(int tempoMedioConsulta,String horaInicio,String horaFinal){
		
		Collection<String> horarios = null;
		String horarioTemp = null;
		
		try {
			String dataAtual = pegarDataAtual();
			
			Calendar cal = new GregorianCalendar(Integer.parseInt(dataAtual.substring(6,10)),
					Integer.parseInt(dataAtual.substring(3,5))-1,
					Integer.parseInt(dataAtual.substring(0,2)));
			
			/* Calculando a quantidade total de minutos disponiveis */
			int horas = Integer.parseInt(horaFinal.substring(0,2)) - Integer.parseInt(horaInicio.substring(0,2));
			int minutos = Integer.parseInt(horaFinal.substring(3,5)) - Integer.parseInt(horaInicio.substring(3,5));

			if(minutos < 0){
				horas--;
				minutos = 60 - minutos;
			}
			int totalMinutos = (horas * 60) + minutos;
			
			/* Calculando a quantidade de horarios da relacao tempo total ,tempo medio consulta */
			int quantidadeHorarios = (int) totalMinutos / tempoMedioConsulta;
			
			horarioTemp = horaInicio;
			if(quantidadeHorarios > 0){
				horarios = new ArrayList<String>();
			}
			for(int i=0;i<quantidadeHorarios;i++){ /* montando horarios */
				/*if(i==0){
					horarios.add(horarioTemp+"_"+somarMinutos(horarioTemp,tempoMedioConsulta));
				}
				else{
					horarios.add(somarMinutos(horarioTemp,1)+"_"+somarMinutos(horarioTemp,tempoMedioConsulta));
				}
				*/
				horarios.add(horarioTemp+"_"+somarMinutos(somarMinutos(horarioTemp,tempoMedioConsulta),-1));
				horarioTemp = somarMinutos(horarioTemp,tempoMedioConsulta);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horarios;
	}
	
	/**
	 * Retorna os horarios de inicio, conforme o tempo medio a partir de um período de horas(horaInicio e horaFinal)
	 * @param tempoMedio HH:mm
	 * @param horaInicio HH:mm
	 * @param horaFinal  HH:mm
	 * @return
	 */
	public static List<String> montaHorarios(String tempoMedio, String horaInicio, String horaFinal) throws Exception{
		
		List<String> horarios = null;
		String horarioTemp = null;
		int tempoMedioConsulta = DataUtil.converteParaMinutos(tempoMedio);
		
		try {
			
			/* Calculando a quantidade total de minutos disponiveis */
			int horas = Integer.parseInt(horaFinal.substring(0,2)) - Integer.parseInt(horaInicio.substring(0,2));
			int minutos = Integer.parseInt(horaFinal.substring(3,5)) - Integer.parseInt(horaInicio.substring(3,5));

			if(minutos < 0){
				horas--;
				minutos = 60 - minutos;
			}
			int totalMinutos = (horas * 60) + minutos;
			
			/* Calculando a quantidade de horarios da relacao tempo total ,tempo medio consulta */
			int quantidadeHorarios = (int) totalMinutos / tempoMedioConsulta;
			
			horarioTemp = horaInicio;
			if(quantidadeHorarios > 0){
				horarios = new ArrayList<String>();
			}
			for(int i=0;i<quantidadeHorarios;i++){ /* montando horarios */
				//if(i==0){
					horarios.add(horarioTemp+"_"+somarMinutos(somarMinutos(horarioTemp,tempoMedioConsulta),-1));
				//}else{
					//horarios.add(horarioTemp+"_"+somarMinutos(somarMinutos(horarioTemp,tempoMedioConsulta),-1));
				//}
				horarioTemp = somarMinutos(horarioTemp,tempoMedioConsulta);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return horarios;
	}
	
	public static String somarMinutos(String horaInicio,int minutosAdicionar){
		
		String horaFinal = null;
		
		try {
			
			int horas = Integer.parseInt(horaInicio.substring(0,2));
			int minutos = Integer.parseInt(horaInicio.substring(3,5));
			
			while(minutos + minutosAdicionar >= 60 || minutos + minutosAdicionar < 0){
				if(minutos + minutosAdicionar >= 60){
					horas++;
					minutosAdicionar -= 60;
				}
				else if(minutos + minutosAdicionar < 0){
					horas--;
					minutosAdicionar += 60;
				}
			}
			minutos = minutos + minutosAdicionar;
			horaFinal = Util.completarString(Integer.toString(horas),2,'E',"0")+":"+Util.completarString(Integer.toString(minutos),2,'E',"0");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return horaFinal;
	}
	
	/**
	 * Formato da hora 00:30 que aceita
	 * @param hora
	 * @return
	 */
	public static int converteParaMinutos(String hora){
		
		int minutosTotal = 0;
		
		try {
			int horas = Integer.parseInt(hora.substring(0,2));
			int minutos = Integer.parseInt(hora.substring(3,5));
			
			minutosTotal = minutos + (horas * 60);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return minutosTotal;
	}
	
	//----------------------------------------------------------------
	
//	/**
//	 * Método para pegar um java.util.Timer de um java.sql.Time
//	 * @param sqlTime
//	 * @return java.util.Timer
//	 */
//	public static java.util.Timer getUtilTimer(java.sql.Time sqlTime,String format){
//		java.util.Timer time = null;
//		
//		String sqlTimeStr = sqlTimeToStr(sqlTime, format);
//		
//		time = new java.util.Timer(sqlTimeStr);
//		
//		return time;
//	}
//	
//	public static String sqlTimeToStr(java.sql.Time sqlTime, String format){
//		String retorno = null;
//		
//		SimpleDateFormat formater = new SimpleDateFormat(format);
//		retorno = formater.format(sqlTime);
//		
//		return retorno;
//	}
//	/**
//	 * Método para pegar um java.sql.Time de um java.util.Timer
//	 * @param utilTimer
//	 * @return um java.sql.Time
//	 */
//	public static java.sql.Time getSqlTime(java.util.Timer utilTimer,String format) throws java.text.ParseException{
//		java.sql.Time time = null;
//		
//		try {
//			String utilTimerStr = utilTimerToStr(utilTimer, format);
//			
//			DateFormat dateFormat = new SimpleDateFormat(format);
//			time = new java.sql.Time(dateFormat.parse(utilTimerStr).getTime());
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return time;
//	}
//	
//	public static String utilTimerToStr(java.util.Timer utilTimer, String format){
//		String retorno = null;
//		
//		DateFormat formater = new SimpleDateFormat(format);
//		retorno = formater.format(utilTimer);
//		
//		return retorno;
//	}
//	//-----------------------------------------------------------------------------
//	
	
	/**
	 * Adiciona e remove dias de uma data.
	 * Leva em conta dias, mês e ano.
	 * @param data
	 * @param dias
	 *  +dias (Positivo) = adiciona dias na data
	 *  -dias (negativo) = remove  dias da data
	 * @return
	 */
	public static Date adicionaRemoveDias(Date data, int dias) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.DATE, dias);
		Date novaData = date.getTime();
		return novaData;
	}
	public static Date adicionaRemoveMes(Date data, int meses) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.MONTH, meses);
		Date novaData = date.getTime();
		return novaData;
	}
	public static Date adicionaRemoveAnos(Date data, int anos) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.YEAR, anos);
		Date novaData = date.getTime();
		return novaData;
	}
	public static Date adicionaRemoveHoras(Date data, int hora) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.HOUR, hora);
		Date novaData = date.getTime();
		return novaData;
	}
	public static Date adicionaRemoveMinutos(Date data, int mim) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.MINUTE, mim);
		Date novaData = date.getTime();
		return novaData;
	}
	public static Date adicionaRemoveSeguntos(Date data, int seg) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.SECOND, seg);
		Date novaData = date.getTime();
		return novaData;
	}
	public static Date adicionaRemoveMilissegundos(Date data, int mili) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.add(Calendar.MILLISECOND, mili);
		Date novaData = date.getTime();
		return novaData;
	}
	
}
