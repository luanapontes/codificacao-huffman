package br.ufpb.dcx;

abstract class Arvore implements Comparable<Arvore> {

    public final int frequencia;

    public Arvore(int freq){
        frequencia = freq;
    }

    public int compareTo(Arvore arvore){
        return frequencia - arvore.frequencia;
    }
}
