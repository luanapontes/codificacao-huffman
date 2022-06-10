package br.ufpb.dcx;

public class Folha extends Arvore {

    public final char valor;

    public Folha(int freq, char val){
        super(freq);
        valor = val;
    }

}
