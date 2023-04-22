package labiMain;

import labirinto.*;

public class ExecutaLabirinto {
	public static void main(String[] args) {
		Labirinto labirinto;

		try {
			PerguntasFactory perguntasFactory = new PerguntasFactory();
			perguntasFactory.fazerPergunta();

			LeArquivo arquivo = new LeArquivo(perguntasFactory.getNomeDoArquivo(), perguntasFactory.getOpcao());

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
