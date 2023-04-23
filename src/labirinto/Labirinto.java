package labirinto;

public class Labirinto {
	private char[][] labirinto;
	private int qtdLinhas;
	private int qtdColunas;
	private int numLinha;
	private int numeroEntradas;
	private int numeroSaidas;
	private String terceiraLinha;
	private String linha;
	private Coordenada atual;
	private boolean encontrouSaida = false;

	public Labirinto(Arquivo arquivo) throws Exception {
		// Pega o numero de linhas
		this.qtdLinhas = arquivo.pegarUmItem();
		// Pega o numero de colunas
		this.qtdColunas = arquivo.pegarUmItem();
		this.terceiraLinha = arquivo.pegaProximaLinha();

		if (this.qtdLinhas == 0 || this.qtdColunas == 0)
			throw new Exception("Labirinto inválido!");

		// Cria o labirinto
		this.labirinto = new char[qtdLinhas][qtdColunas];

		inserirLinha(terceiraLinha, 0);

		numLinha = 1;
		while ((linha = arquivo.pegaProximaLinha()) != null) {
			inserirLinha(linha, numLinha);
			numLinha++;
		}

		arquivo.fecharArquivo();

		if (numLinha != this.qtdLinhas)
			throw new Exception("a quantidade de linhas é diferente da especificada!");

		// Verifica se o labirinto possui uma unica entrada e saida
		verificaEntradasESaidas();
		verificarParedes();

		System.out.println("Labirinto do arquivo " + arquivo.getNomeDoArquivo() + "\n");
		imprimeLabirinto();
	}

	private void inserirLinha(String linha, int numeroLinha) throws Exception {
		verificarLinha(linha, numeroLinha);

		this.labirinto[numeroLinha] = linha.toCharArray();
	}

	private void verificarLinha(String linha, int numeroDaLinha) throws Exception {
		int tamanhoLinha = linha.length();

		// Verifica o tamanho das linhas
		if (tamanhoLinha != qtdColunas)
			throw new Exception("o labirinto possui linhas com tamanhos diferentes!");

		// Verifica o numero de linhas
		if (numeroDaLinha >= qtdLinhas)
			throw new Exception("o labirinto possui mais linhas do que o especificado!");

		String caracteresAceitos = "# ES";

		for (int numeroColuna = 0; numeroColuna < tamanhoLinha; numeroColuna++) {
			char caractere = Character.toUpperCase(linha.charAt(numeroColuna));

			// Verifica os caracteres permitidos
			if (caracteresAceitos.indexOf(caractere) == -1)
				throw new Exception("o caractere " + caractere + " é inválido!");

			switch (caractere) {
			case 'E':

				numeroEntradas++;
				atual = new Coordenada(numeroDaLinha, numeroColuna);
				Coordenada posicaoEntrada = new Coordenada(numeroDaLinha, numeroColuna);
				break;

			case 'S':
				numeroSaidas++;
				break;
			default: {
			} // não faz nada
			}
		}
	}

	public void encontrarSaida() throws Exception {
		Pilha<Coordenada> caminho = new Pilha<>(qtdColunas * qtdLinhas);
		Pilha<Fila<Coordenada>> possibilidades = new Pilha<>(qtdColunas * qtdLinhas);

		Fila<Coordenada> filaDeAdjacentes = new Fila<>(3);

		posicaoAdjacentes(filaDeAdjacentes);
		atual = filaDeAdjacentes.recupereUmItem();
		filaDeAdjacentes.removaUmItem();

		labirinto[atual.getLinha()][atual.getColuna()] = '*';

		caminho.guardaPosicao(atual);
		possibilidades.guardaPosicao(filaDeAdjacentes);

		while (!encontrouSaida) {
			percorrerPosicoesAdjacentes(new Fila<Coordenada>(3), possibilidades, caminho);
		}

		System.out.println("Oba, saída encontrada!\n");
		imprimeLabirinto();

		System.out.println("Posições do caminho até a saída\n");
		retrocederCaminho(caminho);
	}

	private void percorrerPosicoesAdjacentes(Fila<Coordenada> filaDeAdjacentes, Pilha<Fila<Coordenada>> possibilidades,
			Pilha<Coordenada> caminho) throws Exception {
		posicaoAdjacentes(filaDeAdjacentes);

		// Modo regressivo
		if (filaDeAdjacentes.isVazia()) {
			do {
				atual = caminho.recupereUmItem();
				caminho.removaUmItem();
				if (caminho.isVazia())
					throw new Exception("saída não encontrada!");

				inserirCaractere(' ', atual);

				filaDeAdjacentes = possibilidades.recupereUmItem();
				possibilidades.removaUmItem();
				if (possibilidades.isVazia())
					throw new Exception("não existe caminho que leva da entrada até a saída!");
			} while (filaDeAdjacentes.isVazia());
		}

		// Modo progressivo
		atual = filaDeAdjacentes.recupereUmItem();
		if (Character.toUpperCase(labirinto[atual.getLinha()][atual.getColuna()]) == 'S') {
			encontrouSaida = true;
			return;
		}
		filaDeAdjacentes.removaUmItem();
		inserirCaractere('*', atual);
		caminho.guardaPosicao(atual);
		possibilidades.guardaPosicao(filaDeAdjacentes);
	}

	private void retrocederCaminho(Pilha<Coordenada> caminho) throws Exception {
		Pilha<Coordenada> inverso = new Pilha<>();
		while (!caminho.isVazia()) {
			inverso.guardaPosicao(caminho.recupereUmItem());
			caminho.removaUmItem();
		}

		// Indice utilizado apenas para printar de 5 em 5 coordenadas
		int indice = 0;
		while (!inverso.isVazia()) {
			System.out.print(inverso.recupereUmItem());
			inverso.removaUmItem();
			indice++;
			if (indice % 5 == 0)
				System.out.print('\n');
		}
		System.out.println('\n');
	}

	private void inserirCaractere(char caracter, Coordenada coordenada) {
		labirinto[coordenada.getLinha()][coordenada.getColuna()] = caracter;
	}

	private void posicaoAdjacentes(Fila<Coordenada> filaAdjacentes) throws Exception {
		int linha = atual.getLinha();
		int coluna = atual.getColuna();

		// Posição à direita
		posicaoAdjacente(filaAdjacentes, linha, coluna + 1);
		// Posição à cima
		posicaoAdjacente(filaAdjacentes, linha - 1, coluna);
		// Posição à baixo
		posicaoAdjacente(filaAdjacentes, linha + 1, coluna);
		// Posição à esquerda
		posicaoAdjacente(filaAdjacentes, linha, coluna - 1);
	}

	private void posicaoAdjacente(Fila<Coordenada> filaAdjacente, int linha, int coluna) throws Exception {
		if (linha < 0 || linha > qtdLinhas)
			return;
		if (coluna < 0 || coluna > qtdColunas)
			return;

		char caracter = labirinto[linha][coluna];

		if ("#*E".indexOf(caracter) == -1) // se o caractere não for encontrado executa o métodp
			filaAdjacente.guardeUmItem(new Coordenada(linha, coluna));
	}

	private void verificaEntradasESaidas() throws Exception {
		if (numeroEntradas == 0)
			throw new Exception("o labirinto não possui entrada!");
		if (numeroEntradas > 1)
			throw new Exception("o labirinto possui mais de uma entrada!");

		if (numeroSaidas == 0)
			throw new Exception("o labirinto não possui saída!");
		if (numeroSaidas > 1)
			throw new Exception("o labirinto possui mais de uma saida!");
	}

	private void verificarParedes() throws Exception {
		for (int j = 0; j < qtdColunas; j++) {
			// Parede de Cima
			if (labirinto[0][j] == ' ')
				throw new Exception("o labirinto não possui parede na parte de cima!");
			// Parede de Baixo
			if (labirinto[qtdLinhas - 1][j] == ' ')
				throw new Exception("o labirinto não possui parede na parte de baixo!");
		}

		for (int i = 0; i < qtdLinhas; i++) {
			// Parede Esquerda
			if (labirinto[i][0] == ' ')
				throw new Exception("o labirinto não possui parede à esquerda!");
			// Parede Direita
			if (labirinto[i][qtdColunas - 1] == ' ')
				throw new Exception("o labirinto não possui parede à direita!");
		}
	}

	private void imprimeLabirinto() {
		System.out.print(this + "\n");
	}

	@Override
	public String toString() {
		String ret = "";
		for (int i = 0; i < qtdLinhas; i++) {
			for (int j = 0; j < qtdColunas; j++) {
				ret += this.labirinto[i][j];
			}
			ret += "\n";
		}
		return ret;
	}
}
