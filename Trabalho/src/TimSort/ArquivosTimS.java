package TimSort;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ArquivosTimS {
    public static int[] lerArquivo(String url) {
        Path caminho = Paths.get(url); // Vai receber o caminho do arquivo
        String leitura = "";

        try {
            byte[] texto = Files.readAllBytes(caminho); // ira retornar um array de bytes que sera armazenado no array texto
            leitura = new String(texto); // o construtor da classe String aceita um parametro de array do tipo byte

        } catch(Exception e) {
            System.out.println("Não foi possível ler o arquivo!");
            return null;
        }

        ArrayList<Integer> protoVetor = new ArrayList<>();

        String num = "";

        for(int i=0; i<leitura.length(); i++){
            if(leitura.charAt(i) != '[' && leitura.charAt(i) != ']' && leitura.charAt(i) != ',' && leitura.charAt(i) != ' '){
                num += leitura.charAt(i);
            }
            else{
                if(num != ""){
                    int numero = Integer.parseInt(num);
                    protoVetor.add(numero);
                    num = "";
                }
            }
        }

        int[] vetor = new int[protoVetor.size()];

        for(int i=0; i<protoVetor.size(); i++){
            vetor[i] = protoVetor.get(i);
        }

        return vetor;
    }


    public static void gravarArquivo(String url, int[] conteudo){
        Path caminho = Paths.get(url);

        String vetor = Arrays.toString(conteudo);

        byte[] textoEmBytes = vetor.getBytes();

        try{
            Files.write(caminho, textoEmBytes);
        } catch(Exception e){
            System.out.println("Erro!!");
        }
    }

    public static void gravarArquivo(String url, String conteudo, String nomeAlg, long tempoExe, long qtdComp, long qtdMov){
        Path caminho = Paths.get(url);
        String texto = conteudo;
        byte[] textoEmBytes = texto.getBytes();

        try{
            Files.write(caminho, textoEmBytes);
        } catch(Exception e){
            System.out.println("Erro!!");
        }
    }

    public static void gravarArquivo(String url, int[] conteudo, String nomeAlg, long tempoExe, long qtdComp, long qtdMov){
        Path caminho = Paths.get(url);

        double segundos = tempoExe / 1000.0;
        double miliSeg = segundos * 1000;

        int hr = (int) Math.floor(segundos / 3600);
        int min = (int) Math.floor((segundos % 3600) / 60);
        int seg = (int) Math.floor((segundos % 60));

        String tempo = String.format( "%02d:%02d:%02d:%03d", hr, min, seg, (int) miliSeg);

        String cabecalho = "Tempo de execução: "+ tempo + "\nQuantidade de Comparações: " + qtdComp + "\nQuantidade de Movimentos: " + qtdMov + "\n\n";

        String vetor = cabecalho + Arrays.toString(conteudo);


        byte[] textoEmBytes = vetor.getBytes();

        try{
            Files.write(caminho, textoEmBytes);
        } catch(Exception e){
            System.out.println("Erro!!");
        }

    }
}
