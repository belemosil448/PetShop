/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshop;

import javax.swing.JOptionPane;

/**
 *
 * @author lemos
 */
public class ProdutoServico extends Produto {
    private int tempo; // Tempo necessário para o serviço

    public ProdutoServico(String nome, float valor, int tempo) {
        super(nome, valor);
        this.tempo = tempo;
    }

    public int getTempo() {
        return tempo;
    }


    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Valor: " + getValor() + ", Tempo: " + getTempo();
    }
}
