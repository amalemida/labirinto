package labiMain;

import labirinto.*;

public class ExecutaLabirinto {
	public static void main(String[] args) {

		System.out.println("Digite o nome do arquivo de texto");
		String nomeDoArquivo = Teclado.getUmString();
		System.out.println();

		Labirinto labirinto;
		try {
			if (nomeDoArquivo.equals(""))
				System.err.println("Digite um nome de arquivo");

			LeArquivo arquivo = new LeArquivo(nomeDoArquivo);
			try {
				// Achar a solução para o labirinto, caso exista.
				labirinto = new Labirinto(arquivo);
				labirinto.encontrarSaida();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
