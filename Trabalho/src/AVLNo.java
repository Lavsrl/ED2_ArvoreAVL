import java.util.ArrayList;

public class AVLNo {

    int info;
    int alt;
    AVLNo noDireita;
    AVLNo noEsquerda;

    public AVLNo(int info) {
        this.info = info;
        this.alt = 0;
        this.noDireita = null;
        this.noEsquerda = null;
    }

    public AVLNo(int[] vetor, String nome) {
        for (int i = 0; i < vetor.length; i++) {
            inserir(this, vetor[i]);
        }
}
    static void imp_Ordem(AVLNo no) {
        if (no != null) {
            imp_Ordem(no.noEsquerda);
            System.out.println(no.info);
            imp_Ordem(no.noDireita);
        }
    }

    int getAltura(AVLNo no) {
        if (no == null) {
            return -1;
        } else {
            return no.alt;
        }
    }

    int getMax(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    int getFatorBalanceamento(AVLNo no) {
        if (no == null) {
            return 0;
        } else {
            return Math.abs(getAltura(no.noEsquerda) - getAltura(no.noDireita)); //Retorna o valor absoluto da diferença entre as alturas
        }
    }

    //rotações simples (LL e RR) atualizam as novas alturas das subárvores
    //Quando o nó desbalanceado e seu filho estão no mesmo sentido de inclinação

    //RotaçãoLL: simples à direita
    // o nó é inserido na subárvore esquerda da subárvore esquerda
    AVLNo rotacaoLL(AVLNo raiz) {
        AVLNo no = raiz.noEsquerda;//No auxiliar, que será o filho da esquerda da raiz
        raiz.noEsquerda = no.noDireita; //Nó esquerda da raiz passa a ser o filho da direita do nó
        no.noDireita = raiz; //Filho a direita recebe a raiz
        raiz.alt = getMax(getAltura(raiz.noEsquerda), getAltura(raiz.noDireita)) + 1;
        //Altura da raiz passa a ser a maior altura do nó da esquerda e do nó da direita +1
        no.alt = getMax(getAltura(no.noEsquerda), raiz.alt) + 1; //Altura do nó passa a ser a maior altura do nó da esquerda e da raiz +1
        return no; //Raiz passa a ser quem estiver no nó

    }

    //RotaçãoRR: simples à esquerda
    // o nó é inserido na subárvore direita da subárvore direita
    AVLNo rotacaoRR(AVLNo raiz) {
        AVLNo no = raiz.noDireita; //No auxiliar, que será o filho da direita da raiz
        raiz.noDireita = no.noEsquerda; //Nó direita da raiz passa a ser o filho da esquerda do né
        no.noEsquerda = raiz; //Filho a esquerda recebe a raiz
        raiz.alt = getMax(getAltura(raiz.noEsquerda), getAltura(raiz.noDireita)) + 1; //Maior das alturas +1
        no.alt = getMax(getAltura(no.noDireita), raiz.alt) + 1; //Maior valor entre a altura do filho da direta e a altura da raiz +1
        return no; //Raiz passa a ser quem estiver no nó, nó no lugar da raiz
    }

    //Rotações duplas (LR e RL) podem ser implementadas com 2 rotações simples
    AVLNo rotacaoLR(AVLNo raiz) {
        raiz.noEsquerda = rotacaoRR(raiz.noEsquerda);
        raiz = rotacaoLL(raiz);
        return raiz;
    }

    AVLNo rotacaoRL(AVLNo raiz) {
        raiz.noDireita = rotacaoLL(raiz.noDireita);
        raiz = rotacaoRR(raiz);
        return raiz;
    }

    AVLNo inserir(AVLNo atual, int valor) {
        if (atual == null) {//arvore vazia ou nó folha
            atual = new AVLNo(valor); //Cria um novo nó
            return atual;
        } else { //Atual recebe o conteudo da raiz
            if (valor < atual.info) { //Valor que o campo de informação do nó atual
                atual.noEsquerda = inserir(atual.noEsquerda, valor); //inserção na asa da esquerda
                if (getFatorBalanceamento(atual) >= 2) { //Se a inserção for maior ou igual a 2, é necessário balancear
                    if (valor < atual.noEsquerda.info) {//Se o valor é menor do que o conteudo do nó da esquerda atual
                        atual = rotacaoLL(atual);
                    } else {
                        atual = rotacaoLR(atual);
                    }
                }
            } else if (valor > atual.info) {
                atual.noDireita = inserir(atual.noDireita, valor);//inserção na asa da direita
                if (getFatorBalanceamento(atual) >= 2) {//Se a inserção for maior ou igual a 2, é necessário balancear
                    if (valor > atual.noDireita.info) {//Se o valor é maior do que o conteudo do nó da direita atual
                        atual = rotacaoRR(atual);
                    } else {
                        atual = rotacaoRL(atual);
                    }
                }
            } else {//Valores iguais
                System.out.println("Valor duplicado!");
            }
        }
        atual.alt = getMax(getAltura(atual.noEsquerda), getAltura(atual.noDireita)) + 1; //Recalcula a altura atual
        return atual;
    }

    AVLNo procuraMenor(AVLNo atual) { //Dado um dos nós da árvore
        AVLNo no1 = atual;
        AVLNo no2 = atual.noEsquerda;
        while (no2 != null) {
            no1 = no2;
            no2 = no2.noEsquerda; //Andando cada vez mais para a esquerda
        }
        return no1; //Retorna o nó mais a esquerda
    }

    AVLNo remove(AVLNo atual, int valor) {
        if (atual == null) {//Valor não existe
            return null;
        } else {
            if (valor < atual.info) { //Se o valor é menor do que a informação
                atual.noEsquerda = remove(atual.noEsquerda, valor);// tenta remover o nó da esquerda
                if (getFatorBalanceamento(atual) >= 2) {//Se a inserção for maior ou igual a 2, é necessário balancear
                    if (getAltura(atual.noDireita.noEsquerda) <= getAltura(atual.noDireita.noDireita)) { //Comparando as alturas
                        atual = rotacaoRR(atual);
                    } else {
                        atual = rotacaoRL(atual);
                    }
                }
            } else if (valor > atual.info) {
                atual.noDireita = remove(atual.noDireita, valor);// Tenta remover o nó da direita
                if (getFatorBalanceamento(atual) >= 2) {//Se a inserção for maior ou igual a 2, é necessário balancear
                    if (getAltura(atual.noEsquerda.noDireita) <= getAltura(atual.noEsquerda.noEsquerda)) {
                        atual = rotacaoLL(atual);
                    } else {
                        atual = rotacaoLR(atual);
                    }
                }
            } else { //Se valor == atual.info
                if (atual.noDireita == null || atual.noEsquerda == null) {
                    if (atual.noEsquerda != null) { //Se o nó da esquerda não for nulo
                        atual = atual.noEsquerda; //Raiz recebe o filho da esquerda
                    } else {
                        atual = atual.noDireita; //Raiz recebe o filho da direita
                    }
                } else {//Nó tem dois filhos
                    AVLNo temp = procuraMenor(atual.noDireita);//procurar o menor valor na subarvore da direita
                    atual.info = temp.info; //Compila as informações para o nó raiz
                    atual = remove(atual.noDireita, atual.info);//Remove o nó que foi compilado

                    if (getFatorBalanceamento(atual) >= 2) {
                        if (getAltura(atual.noDireita.noEsquerda) <= getAltura(atual.noDireita.noDireita)) {
                            atual = rotacaoRR(atual);
                        } else {
                            atual = rotacaoRL(atual);
                        }
                    }
                }
            }
        }
        if (atual != null) {
            atual.alt = getMax(getAltura(atual.noEsquerda), getAltura(atual.noDireita)) + 1; //Recalcula a altura atual
        }
        return atual;
    }


}
