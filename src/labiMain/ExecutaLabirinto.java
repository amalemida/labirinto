package labiMain;

import labirinto.*;

public class ExecutaLabirinto {
	public static void main(String[] args) {
		Labirinto labirinto;

		try {
			Pergunta perguntasFactory = new Pergunta();
			perguntasFactory.fazerPergunta();

			Arquivo arquivo = new Arquivo(perguntasFactory.getNomeDoArquivo(), perguntasFactory.getOpcao());

			{

				// Encontra a saída para o labirinto, caso exista.
				labirinto = new Labirinto(arquivo);
				labirinto.encontrarSaida();

			}
		} catch (Exception erro) {
			System.err.println("Que pena, "+erro.getMessage());
		}
	}

}
