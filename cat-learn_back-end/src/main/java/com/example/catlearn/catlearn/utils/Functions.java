package com.example.catlearn.catlearn.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;	
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;



public abstract class Functions {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Functions.class);
	
	
	
	
	public Functions() {
		// TODO Auto-generated constructor stub
	}

	public static String ColocaZeroInicio(String Texto, int Tamanho) {
		return ColocaZero(Texto, Tamanho, "Inicio");
	}

	public static String ColocaZeroFim(String Texto, int Tamanho) {
		return ColocaZero(Texto, Tamanho, "Fim");
	}


	protected static String ColocaZero(String Texto, int Tamanho, String Posicao) {

		if(Texto == null){
			return null;
		}
		String TextoOriginal = Texto;
		if (Texto.length() > Tamanho) {
			return Texto.substring(0, Tamanho);
		}
		for (int x = 0; x < Tamanho - TextoOriginal.length(); x++) {
			if (Posicao.equals("Inicio")) {
				Texto = "0" + Texto;
			} else {
				Texto = Texto + "0";
			}

		}
		return Texto;
	}

	static public String dataParaTexto(Date Data) {
		return dataParaTexto(Data, "dd/MM/yyyy");
	}

	static public String dataParaTexto(Date Data, String Mascara ) {
		return dataParaTexto(Data, Mascara, false);
	}
	static public String dataParaTexto(Date Data, String Mascara , boolean retornaVazio) {
		if (Data == null || (Data.equals(new Timestamp(0)) && retornaVazio)) {
			return "";
		} else {
			return new SimpleDateFormat(Mascara).format(Data);
		}
	}

	static public void criarDiretorio(String diretorio){
		File f = new File(diretorio);
		if(!f.isDirectory()){
			if(f.mkdirs()){
				logger.info("Pasta  criada :" + diretorio);
			}else{
				logger.warn("Pasta  n�o foi criada :" + diretorio);
			}
		}
	}
	static public boolean existePasta(String pasta){
		File f = new File(pasta);
		return f.isDirectory();

	}

	static public boolean existeArquivo(String arquivo){
		File f = new File(arquivo);
		return f.isFile();

	}

	static public void limparPasta(String pasta){


		File f = new File(pasta);

		if(f.isDirectory()){
			File[] list = f.listFiles();
			for (File x : list) {
				x.delete();
			}
		}
	}
	static public Timestamp textoParaData(String Data, boolean ShowErro, boolean Hora) {
		if (Hora) {
			Data = Data + " 23:59:59";
			return textoParaData(Data, ShowErro, "dd/MM/yyyy HH:mm:ss");
		} else {
			return textoParaData(Data, ShowErro, "dd/MM/yyyy");
		}
	}
	
	static public Timestamp ultimodiaAno(String ano) {

		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, Integer.parseInt(ano));
		c.add(Calendar.MONTH, 11);
		c.add(Calendar.DAY_OF_MONTH, 28);

	return new Timestamp(c.getTimeInMillis());
		
	}	
	static public Timestamp textoParaData(String Data, boolean ShowErro, String formato) {

		DateFormat df = new SimpleDateFormat(formato);
		Timestamp tsData = null;
		if (Data == null) {
			return tsData;
		}
		if ((Data.equals("")) || (Data.equals("__/__/____")) || (Data.equals("__/__/____ __:__"))) {
			return tsData;
		}
		df.setLenient(false); // gera exception se a data for invalida
		try {
			tsData = new Timestamp(df.parse(Data).getTime());

		} catch (Exception ex) {
			// Devido ao horario de verão no existe a ata 10/10/2010 00:00:00 enta passa para 10/10/2010 01:00:00
			// Se não consigo converter assim mesmo é porque a data a converter não é a data citada acima então gera exceção.
			if (formato.indexOf("HH") > 0) {
				Data = Data.substring(0, formato.indexOf("HH")) + "01" + Data.substring(formato.indexOf("HH") + 2);
			}

			try {
				tsData = new Timestamp(df.parse(Data).getTime());
			} catch (Exception ex1) {
				if (ShowErro) {
					JOptionPane.showMessageDialog(null, new String("Data inválida"), "i-Global Informação", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
		return tsData;
	}
	public static String lerArquivoTxt(String filename) {
		StringBuffer dadosTxt = new StringBuffer("");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename)); // where test.txt is your test file
			String line;
			while ((line = reader.readLine()) != null) {
				dadosTxt.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dadosTxt.toString();
	}
	public static String lerArquivoSistema(String caminhoArquivo){
		Functions funct = new Functions() {
		};  
		return funct.lendoArquivoSistema(caminhoArquivo);

	}
	public String lendoArquivoSistema(String caminhoArquivo){
		List<String> linhas = new ArrayList<String>();
		BufferedReader br = null;		
		try{
			URL url = this.getClass().getResource(caminhoArquivo);

			br = new BufferedReader(new FileReader( url.getFile() ));
			String linha = br.readLine();
			while( linha != null ){
				linhas.add(linha);
				linha = br.readLine();
			}


			String arquivo = "";
			for(String s : linhas){
				arquivo += s;
			}


			return arquivo;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return "";
		}
	}

	public static String getMsgPago(){

		String html = "<div class=\"uolMsg uolMsg-small uolMsg-success\" style=\"\"><h3>Pago<h3></div>";

		return html;

	}
	public static String gerarNumeroAleatorio(int qtd){
		String codigo = "";
		for (int i = 0; i < qtd; i++) {
			codigo += gerarNumero();
		}
		return codigo;
	}
	public static String gerarNumero(){
		Random random = new Random();
		int x = random.nextInt(10);	
		return String.valueOf(x);
	}
	static public boolean valida_CPF(String strCpf) {

		try{

			if (strCpf.trim().equals("")) {
				return false;
			}

			strCpf = strCpf.replace(".", "").replace("-", "");

			int d1, d2;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;

			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;

			for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
				digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

				// multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
				d1 = d1 + (11 - nCount) * digitoCPF;

				// para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
				d2 = d2 + (12 - nCount) * digitoCPF;
			}
			;

			// Primeiro resto da divisão por 11.
			resto = (d1 % 11);

			// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
			if (resto < 2) {
				digito1 = 0;
			} else {
				digito1 = 11 - resto;
			}

			d2 += 2 * digito1;

			// Segundo resto da divisão por 11.
			resto = (d2 % 11);

			// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
			if (resto < 2) {
				digito2 = 0;
			} else {
				digito2 = 11 - resto;
			}

			// Digito verificador do CPF que está sendo validado.
			String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

			// Concatenando o primeiro resto com o segundo.
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

			// comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
			return nDigVerific.equals(nDigResult);


		}catch(Exception e){
			return false;
		}
	}


	public static String gerarSenha() { 


		String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 

		String senha=""; 


		for (int x=0; x<6; x++){ 
			int j = (int) (Math.random()*carct.length); 
			senha += carct[j]; 

		} 

		return senha;
	}
	public static String colocarMascaraMicrochip(String micro){
		try{
			String ret = "";
			if(micro.length()== 15){
				ret = micro.substring(0,3)+".";
				ret += micro.substring(3,6)+".";
				ret += micro.substring(6,9)+".";
				ret += micro.substring(9,12)+"." + micro.substring(12);
			}

			return ret;
		}catch(Exception e){
			return micro;
		}
	}
		public static String getEnderecoServidor(){
		return "catsys.com.br";
	}


	//	public static void main(String[] args) {
	//
	//		String sFileOrigem = "D:\\xml\\abc2\\alterado.xml";
	//		String sFileDestino = "D:\\xml\\abc1\\alterado.xml"; 
	//		copiarArquivo(sFileOrigem, sFileDestino);
	//	}

	public  static String copiarArquivo(String sFileOrigem, String sFileDestino){

		String msgErro = "";

		try{

			FileChannel oriChannel  = new FileInputStream(sFileOrigem).getChannel();
			FileChannel destChannel = new FileOutputStream(sFileDestino).getChannel();
			destChannel.transferFrom(oriChannel, 0, oriChannel.size());
			destChannel.close();
			oriChannel.close();

		} catch (Exception e) {

			msgErro = e.toString();
			e.printStackTrace();

		}

		return msgErro;
	}
		
}
