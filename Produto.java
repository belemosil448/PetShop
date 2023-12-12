/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshop;

/**
 *
 * @author lemos
 */
public class Produto {
    private String nome;
    private float valor;

    public Produto(String nome, float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    // Getters e Setters para nome, quantidade, valor e ganho
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}