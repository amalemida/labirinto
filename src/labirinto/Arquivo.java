package labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

public class Arquivo {
	private BufferedReader opcaoDoUsuario;
	private String caminhoDeEntrada;
	private String nomeDoArquivo;
	private String path;
	private int[] opcoes = { 1, 2, 3, 4, 5, 6 };

	public Arquivo(String nomeDoArquivo, int opcao) throws Exception {
		this.nomeDoArquivo = nomeDoArquivo;
		try {
			for (int i = 0; i < opcoes.length; i++) {
				if (opcao == opcoes[i]) {
					path = "src/labirintos_corretos/" + nomeDoArquivo;
					break;

				} else {
					path = "src/labirintos_errados/" + nomeDoArquivo;
				}
			}
			this.caminhoDeEntrada = FileSystems.getDefault().getPath(path).toAbsolutePath().toString();
			this.opcaoDoUsuario = new BufferedReader(new FileReader(this.caminhoDeEntrada));

		} catch (Exception erro) {
			erro.getMessage();
		}
	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public String pegaProximaLinha() {
		String ret = null;

		try {
			ret = opcaoDoUsuario.readLine();
		} catch (IOException ignored) {
		}

		return ret;
	}

	// esse método é uma cópia de Teclado.class para ler do arquivo.txt
	public int pegarUmItem() throws Exception {
		int ret = 0;

		try {
			ret = Integer.parseInt(opcaoDoUsuario.readLine());
		} catch (IOException ignored) {
		} catch (NumberFormatException ignored) {
			throw new Exception("Int invalido!");
		}

		return ret;
	}

	public void fecharArquivo() throws IOException {
		opcaoDoUsuario.close();
	}
}
