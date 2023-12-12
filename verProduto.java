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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.BiConsumer;

public class verProduto extends JFrame {
    private DefaultListModel<Produto> modeloProdutos;
    private JPanel infoPanel;
    private boolean modoRemocao;
    private JList<Produto> listaProdutos;

    public verProduto(DefaultListModel<Produto> modeloProdutos, JPanel infoPanel, boolean modoRemocao) {
        this.modeloProdutos = modeloProdutos;
        this.infoPanel = infoPanel;
        this.modoRemocao = modoRemocao;

        setTitle("Tipo de Produto");
        setSize(300, 100);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnVerProdutosFisicos = new JButton("Físicos");
        btnVerProdutosFisicos.addActionListener(e -> mostrarProdutos("Fisico", modeloProdutos));
        add(btnVerProdutosFisicos);

        JButton btnVerProdutosServicos = new JButton("Serviços");
        btnVerProdutosServicos.addActionListener(e -> mostrarProdutos("Servico", modeloProdutos));
        add(btnVerProdutosServicos);

        
    }

    private void mostrarProdutos(String tipo, DefaultListModel<Produto> produtos) {
        infoPanel.removeAll();
        if (modoRemocao) {
            JButton btnRemover = new JButton("Remover");
            btnRemover.addActionListener(e -> {
                int selectedIndex = listaProdutos.getSelectedIndex();
        if (selectedIndex != -1) {
            modeloProdutos.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um produto para remover.");
        }
            });
            add(btnRemover);
        }
       if(modoRemocao == false) {for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if ((tipo.equals("Fisico") && produto instanceof ProdutoFisico) ||
                (tipo.equals("Servico") && produto instanceof ProdutoServico)) {
                infoPanel.add(new JLabel(produto.toString()));
            }
        }}

        infoPanel.revalidate();
        infoPanel.repaint();
    }
}