package labirinto;

public class Pergunta {
	private int opcao;
	private String nomeDoArquivo;
		
	public void fazerPergunta() throws Exception {
		while (opcao < 1 || opcao > 19) {
			try {
				System.out.println("\nQual arquivo deseja utilizar?\n"
									+ "\n 1 - Teste1.txt" 
									+ "\n 2 - Teste2.txt"
									+ "\n 3 - Teste3.txt"
									+ "\n 4 - Teste4.txt"
									+ "\n 5 - Teste5.txt"
									+ "\n 6 - Teste6.txt"
									+ "\n 7 - 2entradas.txt"
									+ "\n 8 - 2saidas.txt"
									+ "\n 9 - CaracteresEstranhos.txt"
									+ "\n10 - EntradaForaDeLugar.txt" 
									+ "\n11 - QuantidadeLinhaErrada.txt"
									+ "\n12 - SaidaForaDeLugar.txt" 
									+ "\n13 - SemCaminhoDeEparaS.txt" 
									+ "\n14 - SemEntrada.txt"
									+ "\n15 - SemParede1.txt"
									+ "\n16 - SemParede2.txt"
									+ "\n17 - SemQtdDeLinsEOuCols.txt"
									+ "\n18 - SemSaida.txt"
									+ "\n19 - TamanhosDeLinhaErrados.txt\n"
									);

				opcao = Teclado.getUmInt();

				System.out.println();

				switch (opcao) {
				case 1:
					nomeDoArquivo = "Teste1.txt";
					break;
				case 2:
					nomeDoArquivo = "Teste2.txt";
					break;
				case 3:
					nomeDoArquivo = "Teste3.txt";
					break;
				case 4:
					nomeDoArquivo = "Teste4.txt";
					break;
				case 5:
					nomeDoArquivo = "Teste5.txt";
					break;
				case 6:
					nomeDoArquivo = "Teste6.txt";
					break;
				case 7:
					nomeDoArquivo = "2entradas.txt";
					break;
				case 8:
					nomeDoArquivo = "2saidas.txt";
					break;
				case 9:
					nomeDoArquivo = "CaracteresEstranhos.txt";
					break;
				case 10:
					nomeDoArquivo = "EntradaForaDeLugar.txt";
					break;
				case 11:
					nomeDoArquivo = "QuantidadeLinhasErrada.txt";
					break;
				case 12:
					nomeDoArquivo = "SaidaForaDeLugar.txt";
					break;
				case 13:
					nomeDoArquivo = "SemCaminhoDeEparaS.txt";
					break;
				case 14:
					nomeDoArquivo = "SemEntrada.txt";
					break;
				case 15:
					nomeDoArquivo = "SemParede1.txt";
					break;
				case 16:
					nomeDoArquivo = "SemParede2.txt";
					break;
				case 17:
					nomeDoArquivo = "SemQtdDeLinsEOuCols.txt";
					break;
				case 18:
					nomeDoArquivo = "SemSaida.txt";
					break;
				case 19:
					nomeDoArquivo = "TamanhosDeLinhaErrados.txt";
					break;
				default:
					System.err.println("Opção inválida");

				}
			} catch (Exception erro) {
				if (erro.getMessage().equals("Int invalido!")) {
					System.err.println("\nOpção inválida!");
				} else
					System.out.println(erro.getMessage());

			}
		
		}

	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public int getOpcao() {
		return opcao;
	}

}
