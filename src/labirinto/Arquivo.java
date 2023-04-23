package labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arquivo {
	private BufferedReader in;
	private String caminhoDeEntrada;
	private String path;
	private int[] opcoes = { 1, 2, 3, 4, 5, 6,};
	private String nomeDoArquivo;

	public Arquivo(String nomeDoArquivo, int opcao) throws Exception {
		this.nomeDoArquivo = nomeDoArquivo;
		if (nomeDoArquivo.isEmpty() || nomeDoArquivo.isBlank())
			throw new Exception("Nome do arquivo inválido!");

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
			this.in = new BufferedReader(new FileReader(this.caminhoDeEntrada));

		} catch (Exception erro) {
			erro.getMessage();
		//	System.err.println("Arquivo não encontrado");
		}
	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public String pegaProximaLinha() {
		String ret = null;

		try {
			ret = in.readLine();
		} catch (IOException ignored) {
		}

		return ret;
	}
	//pega do arquivo
	public int pegarUmItem() throws Exception {
		int ret = 0;

		try {
			ret = Integer.parseInt(in.readLine());
		} catch (IOException ignored) {
		} catch (NumberFormatException ignored) {
			throw new Exception("Int invalido!");
		}

		return ret;
	}

	public void lerNovamente() throws Exception {
		try {
			this.in.close();
			this.in = new BufferedReader(new FileReader(this.caminhoDeEntrada));
		} catch (IOException erro) {
			throw new Exception(erro);
		}
	}

	public void fecharArquivo() throws IOException {
		in.close();
	}
}
