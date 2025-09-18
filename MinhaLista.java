package AED1.Exercicios.TrabalhoFractal2;

public class MinhaLista<T> {
    private Celula head;
    private Celula tail;
    private long tamanho;

    class Celula {
        T item;
        Celula prox;
    }

    public MinhaLista() {
        head = new Celula();
        tail = head;
        tamanho = 0;
    }

    public boolean isVazia() {

        return head == tail;
    }

    public void insereNoFinal(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item com valor nulo!!");
        } else {
            tail.prox = new Celula();
            tail = tail.prox;
            tail.item = item;

            tamanho++;
        }
    }

    public long getTamanho() {
        return tamanho;
    }

    public T get(long indice) {
        Celula aux = head.prox;
        if (isVazia() || indice >= tamanho || indice < 0) {
            throw new IndexOutOfBoundsException("Tamanho do vetor inválido!!");
        } else {
            for (int i = 0; i < indice; i++) {
                aux = aux.prox;
            }
            return aux.item;
        }
    }
}

