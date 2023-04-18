import java.io.*;

public class MostraArquivoTexto {
    public static void main(String[] args) {
        System.out.println("Digite o arquivo com o labirinto escolhido: ");
        String arquivo = Teclado.getUmString();

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("labirintos_corretos/" + arquivo));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
        }
    }
}