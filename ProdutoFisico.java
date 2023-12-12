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
public class ProdutoFisico extends Produto {

    private int quantidade;

    public ProdutoFisico(String nome, float valor, int quantidade) {
        super(nome, valor);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int novaQuantidade) {
        this.quantidade = novaQuantidade;
    }

    public void vender(int quantidadeAVender) throws QuantidadeInsuficienteException {
        
        if (quantidadeAVender > quantidade) {
            throw new QuantidadeInsuficienteException("Quantidade insuficiente em estoque");
        }

        // Subtraindo a quantidade vendida do estoque do produto
        setQuantidade(quantidade - quantidadeAVender);

        // Atualizando o ganho com base na venda
    }
    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Valor: " + getValor() + ", Quantidade: " + getQuantidade();
    }
}










