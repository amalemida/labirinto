package labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeArquivo {
	private BufferedReader in;
	private String caminhoDeEntrada;
	public static String path;
	List<String> lista = new ArrayList<String>(Arrays.asList(
			new String[] { "Teste1.txt", "Teste2.txt","Teste3.txt","Teste4.txt","Teste5.txt","Teste6.txt" }));

	public LeArquivo(String nomeDoArquivo) throws Exception {
		if (nomeDoArquivo.isEmpty() || nomeDoArquivo.isBlank())
			throw new Exception("Nome do arquivo inválido");

		try {

			for (int i = 0; i < lista.size(); i++) {
				if (nomeDoArquivo.contains(lista.get(i))) {
					path = "src/Labirintos_corretos/";
					break;
				} else
					path = "src/Labirintos_errados/";
			}

			this.caminhoDeEntrada = FileSystems.getDefault().getPath(path + nomeDoArquivo).toAbsolutePath().toString();
			this.in = new BufferedReader(new FileReader(this.caminhoDeEntrada));

		} catch (IOException erro) {
			System.err.println(erro.getMessage());
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
