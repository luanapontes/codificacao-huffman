package br.ufpb.dcx;

public class Node extends Arvore{

    public final Arvore esquerda, direita;

    public Node(Arvore esq, Arvore dir){
        super(esq.frequencia + dir.frequencia);
        esquerda = esq;
        direita = dir;
    }
}
