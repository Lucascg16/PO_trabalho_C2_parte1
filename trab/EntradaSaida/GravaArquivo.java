package trab.EntradaSaida;
import java.io.*;

public class GravaArquivo {
	private FileWriter writer;
	// objeto que representara o “gravador” de caracteres. 
	private PrintWriter saida;
	// objeto que possibilita escrever Strings no arquivo 
	// utilizando os métodos print() e println().

	public GravaArquivo (String nome, boolean opc) throws IOException{
		try{
			writer = new FileWriter(new File(nome), opc);

			saida = new PrintWriter (writer);
		}
		catch (IOException e){
			throw new IOException ("ARQUIVO NAO PODE SER ABERTO PARA GRAVACAO");
		}
	}

	public void grava (String str)	{
			this.saida.print(str);
	}

	public void fecha ()throws IOException{
		try{
			this.saida.close();
			this.writer.close();
		}
		catch (IOException e){
			throw new IOException ("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
