package trab;

import java.io.FileNotFoundException;
import java.io.IOException;

import trab.EntradaSaida.GravaArquivo;
import trab.EntradaSaida.LeArquivo;
import trab.classes.cadCompra;

public class appCompra {
	public static void main(String[] args) {
		escolher();
	}

	public static void escolher() {
		String nomearq = "", nomearqResultado = "";
		for (int i = 0; i <= 14; i++) {//esses for me deixam triste, mas eu não vou tirar eles
			for (int j = 0; j <= 3; j++) {
				switch (i) {
				case 0:
					nomearq = "trab/testes/compra500alea.txt";
					nomearqResultado = "compra500alea.txt";
					break;
				case 1:
					nomearq = "trab/testes/compra500inv.txt";
					nomearqResultado = "compra500inv.txt";
					break;
				case 2:
					nomearq = "trab/testes/compra500ord.txt";
					nomearqResultado = "compra500ord.txt";
					break;
				case 3:
					nomearq = "trab/testes/compra1000alea.txt";
					nomearqResultado = "compra1000alea.txt";
					break;
				case 4:
					nomearq = "trab/testes/compra1000inv.txt";
					nomearqResultado = "compra1000inv.txt";
					break;
				case 5:
					nomearq = "trab/testes/compra1000ord.txt";
					nomearqResultado = "compra1000ord.txt";
					break;
				case 6:
					nomearq = "trab/testes/compra5000alea.txt";
					nomearqResultado = "compra5000alea.txt";
					break;
				case 7:
					nomearq = "trab/testes/compra5000inv.txt";
					nomearqResultado = "compra5000inv.txt";
					break;
				case 8:
					nomearq = "trab/testes/compra5000ord.txt";
					nomearqResultado = "compra5000ord.txt";
					break;
				case 9:
					nomearq = "trab/testes/compra10000alea.txt";
					nomearqResultado = "compra10000alea.txt";
					break;
				case 10:
					nomearq = "trab/testes/compra10000inv.txt";
					nomearqResultado = "compra10000inv.txt";
					break;
				case 11:
					nomearq = "trab/testes/compra10000ord.txt";
					nomearqResultado = "compra10000ord.txt";
					break;
				case 12:
					nomearq = "trab/testes/compra50000alea.txt";
					nomearqResultado = "compra50000alea.txt";
					break;
				case 13:
					nomearq = "trab/testes/compra50000inv.txt";
					nomearqResultado = "compra50000inv.txt";
					break;
				case 14:
					nomearq = "trab/testes/compra50000ord.txt";
					nomearqResultado = "compra50000ord.txt";
					break;
				}
				rodar(nomearq, nomearqResultado, j);
			}
		}
	}

	public static void rodar(String nomearq, String nomeResultado, int escolheOrdena) {
		cadCompra compra = new cadCompra();
		double[] tempoMedio = new double[5];
		double media = 0;
		String nomeOrdena = "";

		for (int i = 0; i < tempoMedio.length; i++) {
			long tempoInicial, tempoFinal;
			tempoInicial = System.nanoTime();

			leArquivo(compra, nomearq);
			nomeOrdena = ordena(compra, escolheOrdena);
			grava(nomeOrdena, compra, nomeResultado);

			tempoFinal = System.nanoTime();
			tempoMedio[i] = ((tempoFinal - tempoInicial) / 1e+9);
			System.out.println("Tempo medio de " + nomearq + " com algoritimo " + nomeOrdena + " : " + tempoMedio[i]);
		}

		for (int i = 0; i < tempoMedio.length; i++) {
			media += tempoMedio[i];
		}
		media = media / tempoMedio.length;
		gravarResultado("Tempo medio de " + nomearq + " do algoritimo " + nomeOrdena + ": ", media);
	}

	public static void leArquivo(cadCompra compra, String nomearq) {
		LeArquivo arquivo = null;
		try {
			arquivo = new LeArquivo(nomearq);
			compra.setVetCompra(arquivo.ler());
			arquivo.fecha();
		} catch (FileNotFoundException erro) {
			System.out.println(erro.getMessage());
			System.exit(1);
		} catch (NumberFormatException erro) {
			System.out.println(erro.getMessage());
			arquivo.fecha();
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException erro) {
			System.out.println(erro.getMessage());
			arquivo.fecha();
			System.exit(1);
		}
	}

	public static String ordena(cadCompra compra, int escolheOrdena) {
		String nomeOrdena = "";
		switch (escolheOrdena) {
		case 0:
			compra.insercaoDireta();
			nomeOrdena = "Inserção direta";
			break;
		case 1:
			compra.shellSort();
			nomeOrdena = "ShellSort";
			break;
		case 2:
			compra.quickSort();
			nomeOrdena = "QuickSort";
			break;
		case 3:
			compra.quickComInsercao();
			nomeOrdena = "Quick Com Inserção";
			break;
		}
		return nomeOrdena;
	}

	public static void grava(String nomeOrdena, cadCompra compra, String nomearq) {
		try {
			String grava = nomeOrdena + nomearq;
			GravaArquivo saida = new GravaArquivo(grava, false);
			for (int i = 0; i < compra.getVetCompra().size(); i++) {
				saida.grava(compra.get(i).toString());
			}
			saida.fecha();
		} catch (IOException excecao) {
			System.out.println(excecao);
		}
	}

	public static void gravarResultado(String str, double mediaFinal) {
		String mediaFinalString = str + Double.toString(mediaFinal);
		try {
			GravaArquivo saida = new GravaArquivo("resultado.txt", true);
			saida.grava(mediaFinalString + "\n");
			saida.fecha();
		} catch (IOException excecao) {
			System.out.println(excecao);
		}
	}
}
