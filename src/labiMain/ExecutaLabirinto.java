package labiMain;

import labirinto.*;

public class ExecutaLabirinto {
	public static void main(String[] args) {
		try {
			Pergunta pergunta = new Pergunta();
			pergunta.fazerPergunta();

			Arquivo arquivo = new Arquivo(pergunta.getNomeDoArquivo(), pergunta.getOpcao());

			// Encontra a saída para o labirinto, caso exista.
			Labirinto labirinto = new Labirinto(arquivo);
			labirinto.encontrarSaida();

		} catch (Exception erro) {
			System.err.println("Que pena, " + erro.getMessage());
		}
	}

}
