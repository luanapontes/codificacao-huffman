package br.ufpb.dcx;

import java.util.PriorityQueue;

public class Huffman {

    public static void main(String[] args){

        String texto = "luana";

        int[] freqs = new int[500];
        for (char c: texto.toCharArray()) {
            freqs[c]++;
        }

        Arvore arvore = criarArvore(freqs);

        System.out.println("Tabela");
        System.out.println("Símbolo\tQuantidade\tCódigo Huffman");
        printCodigos(arvore, new StringBuffer());

        String compactacao = compactacao(arvore, texto);
        System.out.println("\n Texto Compactado");
        System.out.println(compactacao);

        System.out.println("\n\n Texto Decodificado");
        System.out.println(decodificacao(arvore, compactacao));
    }

    public static Arvore criarArvore(int[] freqs){

        PriorityQueue<Arvore> arvores = new PriorityQueue<Arvore>();

        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > 0 )
                arvores.offer(new Folha(freqs[i], (char)i));
        }

        while (arvores.size() > 1){
            Arvore a = arvores.poll();
            Arvore b = arvores.poll();

            arvores.offer(new Node(a,b));
        }

        return arvores.poll();
    }

    public static String compactacao(Arvore arvore, String compactacao){
        assert arvore != null;

        String textoComp = "";
        for (char c: compactacao.toCharArray()) {
            textoComp+= (getCodigo(arvore, new StringBuffer(), c));
        }

        return textoComp;
    }

    public static String decodificacao(Arvore arvore, String decodificacao){
        assert arvore != null;

        String textoDec = "";
        Node node = (Node) arvore;
        for (char codigo: decodificacao.toCharArray()) {
            if (codigo == '0') {
                if (node.esquerda instanceof Folha){
                    textoDec += ((Folha) node.esquerda).valor;
                    node = (Node) arvore;
                } else {
                    node = (Node) node.esquerda;
                }
            } else if (codigo == '1') {
                if (node.direita instanceof Folha){
                    textoDec += ((Folha) node.direita).valor;
                    node = (Node) arvore;
                } else {
                    node = (Node) node.direita;
                }
            }
            
        }

        return textoDec;
    }

    public static String getCodigo(Arvore arvore, StringBuffer prefix, char w){
        assert arvore != null;

        if (arvore instanceof Folha){
            Folha folha = (Folha) arvore;

            if (folha.valor == w){
                return prefix.toString();
            }
        } else if (arvore instanceof Node){
            Node node = (Node) arvore;

            prefix.append('0');
            String esquerda = getCodigo(node.esquerda, prefix, w);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            String direita = getCodigo(node.direita, prefix, w);
            prefix.deleteCharAt(prefix.length()-1);

            if (esquerda == null) return direita; else return esquerda;
        }

        return null;
    }

    public static void printCodigos(Arvore arvore, StringBuffer prefix){
        assert arvore != null;

        if (arvore instanceof Folha){
            Folha folha = (Folha) arvore;

            System.out.println(folha.valor + "\t\t" + folha.frequencia + "\t\t\t" + prefix);
        } else if (arvore instanceof Node){
            Node node = (Node) arvore;

            prefix.append('0');
            printCodigos(node.esquerda, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            prefix.append('1');
            printCodigos(node.direita, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
}
