package trab.io;
/**
 * @author Cinthia, Lucas Carlos Giacomin
 * 
 * TODO Classe para criar um objeto do tipo arquivo de leitura
 */
import java.io.*;
import java.util.*;
import trab.classes.cliente;
import trab.classes.clienteEspecial;
import trab.classes.compra;

public class LeArquivo {
	private Scanner entrada;
	/**
	 * Construtor
	 * @param nome => Nome do arquivo que sera aberto para leitura
	 * @throws FileNotFoundException => Excecao se nao encontrar o arquivo
	 */
	public LeArquivo (String nome) throws FileNotFoundException{
		try{
			this.entrada = new Scanner (new File (nome));
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException ("ARQUIVO NAO ENCONTRADO");
		}
	}
	 /**
	  * Metodo para ler os dados contidos no arquivo
	  * @return Vetor de Contas que serao lidas nesse metodo
	  * @throws NoSuchElementException
	  */
	public ArrayList<compra> ler ()throws ArrayIndexOutOfBoundsException, NumberFormatException{
		ArrayList<compra> compra = new ArrayList<>();
		String linha;
		// Enquanto tiver informacao na linha do cursor ele roda o while
		while (this.entrada.hasNext()){
			linha = this.entrada.nextLine();// pega a linha toda e joga na variavel linha
			compra.add(separaDados(linha));// separa os dados e adiciona na lista de contas
		}
		return compra;
	}

	/**
	 * Metodo para transformar uma linha do arquivo em um objeto
	 * da classe Conta
	 * @param linha => String contendo a linha do arquivo que sera transformada
	 * @return => A conta criada a partir do linha passada
	 * @throws NoSuchElementException => Excecao causada por elementos insuficientes
	 * 					na String, durante a transformacao
	 * @throws NumberFormatException => Excecao causada por transformar uma String
	 * 					que nao tem apenas digitos em inteiro
	 */
	private compra separaDados (String linha)throws ArrayIndexOutOfBoundsException, NumberFormatException{
		String[] dados=null;
		double vale, valor;
		Calendar data;

		try{
			dados = linha.split(";");
			
			if(dados.length > 4){//caso o cliente seja ou nao especial a posição dos dados vale, data e valor podem mudar
				valor = Double.parseDouble(dados[4]);
				vale = Double.parseDouble(dados[2]);
				data = manipulaData.leDataString(dados[3]);
				clienteEspecial cliente = new clienteEspecial(dados[0], dados[1], vale);
				return (new compra(cliente, data, valor));
			}else{
				valor = Double.parseDouble(dados[3]);
				data = manipulaData.leDataString(dados[2]);
				cliente cliente = new cliente(dados[0], dados[1]);
				return (new compra(cliente, data, valor));
			}
		}
		catch (ArrayIndexOutOfBoundsException erro){
			throw new ArrayIndexOutOfBoundsException ("REGISTRO TEM "+dados.length+" INFORMACOES");
		}catch (NumberFormatException erro){
			throw new NumberFormatException ("NUMERO DA CONTA NAO E INTEIRO");
		}
	}
	/**
	 * Metodo para fechar o arquivo de leitura
	 * @throws IllegalStateException => Excecao causada se nao conseguir fechar o arquivo.
	 */
	public void fecha ()throws IllegalStateException{
		try{
			this.entrada.close();
		}
		catch (IllegalStateException e){
			throw new IllegalStateException ("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
