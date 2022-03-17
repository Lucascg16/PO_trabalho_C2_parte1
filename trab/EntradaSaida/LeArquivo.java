package trab.EntradaSaida;

import java.io.*;
import java.util.*;
import trab.classes.cliente;
import trab.classes.clienteEspecial;
import trab.classes.compra;

public class LeArquivo {
	private Scanner entrada;
	
	public LeArquivo (String nome) throws FileNotFoundException{
		try{
			this.entrada = new Scanner (new File (nome));
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException ("ARQUIVO NAO ENCONTRADO");
		}
	}

	public ArrayList<compra> ler ()throws ArrayIndexOutOfBoundsException, NumberFormatException{
		ArrayList<compra> compra = new ArrayList<>();
		String linha;
		while (this.entrada.hasNext()){// Enquanto tiver informacao na linha do cursor ele roda o while
			linha = this.entrada.nextLine();// pega a linha toda e joga na variavel linha
			compra.add(separaDados(linha));// separa os dados e adiciona na lista de contas
		}
		return compra;
	}

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
	
	public void fecha ()throws IllegalStateException{
		try{
			this.entrada.close();
		}
		catch (IllegalStateException e){
			throw new IllegalStateException ("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
