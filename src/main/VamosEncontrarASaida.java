package main;

import labirinto.*;

public class VamosEncontrarASaida {
	public static void main(String[] args) {
		String opcao = "";
		while (!opcao.equals("3")) {
			
			System.out
					.println("Digite: " + "\n1 - Labirinto com solução" + "\n2 - Labirinto sem solução" + "\n3 - Sair");
			opcao = Teclado.getUmString();
			if (!opcao.equals("3")) {
				System.out.println("Digite o nome do arquivo de texto");
				String nomeDoArquivo = Teclado.getUmString();
				System.out.println();

				switch (opcao) {
				case "1":
					nomeDoArquivo = "src/Labirintos_corretos/" + nomeDoArquivo;
					break;
				case "2":
					nomeDoArquivo = "src/Labirintos_errados/" + nomeDoArquivo;
					break;
				default: {}
				}

				Labirinto labirinto;
				try {
					if (nomeDoArquivo.equals(""))
						throw new Exception("Especifique um nome de arquivo");
					if (nomeDoArquivo.equalsIgnoreCase("s")) {
						System.out.println("Fim do jogo!!!");
						break;
					}

					LeArquivo arqLabirinto = new LeArquivo(nomeDoArquivo);
					try {
						// Achar a solução para o labirinto, caso exista.
						labirinto = new Labirinto(arqLabirinto);
						labirinto.encontrarSolucao();
					} catch (Exception e) {
						System.err.println("Sem Solução: " + e.getMessage());
					}
				} catch (Exception e) {
					System.err.println("Erro: " + e.getMessage());
				}
			} else {
				System.out.println("Encerrado!!");
			}
		}
	}
}
