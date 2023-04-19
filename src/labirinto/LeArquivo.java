package labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

public class LeArquivo {
	private BufferedReader in;
	private final String caminhoDeEntrada;

	String caminho;

	public LeArquivo(String nomeDoArquivo) throws Exception {
		if (nomeDoArquivo.isEmpty() || nomeDoArquivo.isBlank())
			throw new Exception("Nome do arquivo nulo");

		try {
			this.caminhoDeEntrada = FileSystems.getDefault().getPath(nomeDoArquivo).toAbsolutePath().toString();
			this.in = new BufferedReader(new FileReader(this.caminhoDeEntrada));
		} catch (IOException erro) {
			throw new Exception("Arquivo nao encontrado");
		}
	}

	public String pegaProximaLinha() {
		String ret = null;

		try {
			ret = in.readLine();
		} catch (IOException ignored) {
		}

		return ret;
	}

	public int pegaUmInt() throws Exception {
		int ret = 0;

		try {
			ret = Integer.parseInt(in.readLine());
		} catch (IOException ignored) {
		} 
		catch (NumberFormatException ignored) {
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