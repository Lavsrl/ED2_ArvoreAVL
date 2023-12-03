public class Main {
    public static void main(String[] args) {

        int[] vetor = Arquivos.lerArquivo("L:\\Trabalho\\Trabalho\\src\\ArquivoDados\\dados5.txt");
        //int[] vetor1 = Arquivos.lerArquivo("L:\\Trabalho\\Trabalho\\src\\ArquivoDados\\dados100.txt");

        AVLNo arvore = new AVLNo(vetor.clone(), "Ordenação_AVL_dados5");
        //AVLNo arvore1 = new AVLNo(vetor1.clone(), "Ordenação_AVL_dados100_mil");

        AVLNo.imp_Ordem(arvore);
        //System.out.println("Dados 100:");
        //AVLNo.imp_Ordem(arvore1);


    }
}
