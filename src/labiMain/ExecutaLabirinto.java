package labiMain;

import labirinto.*;

public class ExecutaLabirinto {
	public static void main(String[] args) {

		System.out.println("Digite o nome do arquivo de texto");
		String nomeDoArquivo = Teclado.getUmString();
		System.out.println();

		Labirinto labirinto;
		try {
			LeArquivo arquivo = new LeArquivo(nomeDoArquivo);
			{

				// Achar a solução para o labirinto, caso exista.
				labirinto = new Labirinto(arquivo);
				labirinto.encontrarSaida();

			}
		} catch (Exception erro) {
			erro.getMessage();
			System.err.println("Arquivo não encontrado");
		}
	}

}
