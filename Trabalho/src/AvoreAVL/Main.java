package AvoreAVL;

public class Main {
    public static void main(String[] args) {

        int[] vetor = Arquivos.lerArquivo("L:\\Trabalho\\Trabalho\\src\\ArquivoDados\\dados5.txt");
        int[] vetor1 = Arquivos.lerArquivo("L:\\Trabalho\\Trabalho\\src\\ArquivoDados\\dados100.txt");
        int[] vetor2 = Arquivos.lerArquivo("L:\\Trabalho\\Trabalho\\src\\ArquivoDados\\dados100_mil.txt");
        int[] vetor3 = Arquivos.lerArquivo("L:\\Trabalho\\Trabalho\\src\\ArquivoDados\\dados500_mil.txt");

        AVLNo arvore = new AVLNo(vetor.clone());
        AVLNo arvore1 = new AVLNo(vetor1.clone());
        AVLNo arvore2 = new AVLNo(vetor2.clone());
        AVLNo arvore3 = new AVLNo(vetor3.clone());

        double tempoInicial = System.currentTimeMillis();
        for (int value : vetor) {//Inserindo os valores do vetor na árvore
            arvore2.inserir(arvore2, value);
        }
        System.out.println("Impressão em ordem dos 100 mil dados: ");
        AVLNo.imp_Ordem(arvore2);

        double tempoFinal = System.currentTimeMillis();
        double tempoTotal = tempoFinal - tempoInicial;


        //Tempo marcado
        double segundos = tempoTotal / 1000.0;
        double miliSeg = segundos * 1000;

        int hr = (int) Math.floor(segundos / 3600);
        int min = (int) Math.floor((segundos % 3600) / 60);
        int seg = (int) Math.floor((segundos % 60));

        System.out.println("Tempo de execução: ");
        System.out.printf("%02d:%02d:%02d:%03d", hr, min, seg, (int) miliSeg);

    }
}
