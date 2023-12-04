package TimSort;

public class TimSort {
    private long mov;
    private long comp;

    public TimSort(int[] vetor) {
        this.mov = 0;
        this.comp = 0;

        long tempoIni = System.currentTimeMillis();

        timSort(vetor);

        long tempoFim = System.currentTimeMillis();

        long tempoTotal = tempoFim - tempoIni;

        ArquivosTimS.gravarArquivo("L:\\Trabalho\\Trabalho\\src\\TimSort\\Resultados" + ".txt", vetor, "Teste",
                tempoTotal, this.comp, this.mov);
    }

    private void timSort(int[] vetor) {

        int run = 32;
        for (int i = 0; i < vetor.length; i += run) {

            insertionSort(vetor, i, Math.min((i + 31), (vetor.length - 1)));

        }

        for (int size = run; size < vetor.length; size *= 2) {

            for (int esquerda = 0; esquerda < vetor.length; esquerda += 2 * size) {

                int meio = esquerda + size - 1;
                int direita = Math.min((esquerda + 2 * size - 1), (vetor.length - 1));

                if (meio < direita)
                    merge(vetor, esquerda, meio, direita);
            }
        }
    }

    private void insertionSort(int[] vetor, int comeco, int fim) {
        for (int i = comeco + 1; i <= fim; i++) {
            int temp = vetor[i];
            int j = i - 1;

            this.comp++;
            while (vetor[j] > temp && j >= comeco) {
                this.comp++;

                this.mov++;
                vetor[j + 1] = vetor[j];
                j--;
                if (j < 0)
                    break;
            }

            this.mov++;
            vetor[j + 1] = temp;
        }
    }

    private void merge(int[] vetor, int comeco, int meio, int fim) {
        int alcance1 = meio - comeco + 1;
        int alcance2 = fim - meio;

        int[] esq = new int[alcance1];
        int[] dir = new int[alcance2];

        for (int i = 0; i < alcance1; i++) {
            this.mov++;
            esq[i] = vetor[comeco + i];
        }

        for (int i = 0; i < alcance2; i++) {
            this.mov++;
            dir[i] = vetor[meio + 1 + i];
        }

        int i = 0, j = 0, k = comeco;

        while (i < alcance1 && j < alcance2) {
            this.comp++;
            if (esq[i] <= dir[j]) {
                this.mov++;
                vetor[k] = esq[i];
                i++;
            } else {
                this.mov++;
                vetor[k] = dir[j];
                j++;
            }
            k++;
        }

        while (i < alcance1) {
            this.mov++;
            vetor[k] = esq[i];
            k++;
            i++;
        }

        while (j < alcance2) {
            this.mov++;
            vetor[k] = dir[j];
            k++;
            j++;
        }
    }

}
