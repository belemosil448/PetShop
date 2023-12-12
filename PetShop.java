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
import java.util.function.BiConsumer;

public class PetShop extends JFrame {
    private JPanel infoPanel;
    private DefaultListModel<Produto> modeloProdutos;
    private verProduto verProdutoWindow;

    public PetShop() {
        setTitle("PetShop");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloProdutos = new DefaultListModel<>();
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        add(buttonPanel, BorderLayout.WEST);

        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        btnAdicionarProduto.addActionListener(e -> {
        EscolherTipoProduto escolherTipoProdutoWindow = new EscolherTipoProduto(modeloProdutos, infoPanel, false); 
        escolherTipoProdutoWindow.setVisible(true);
        });
        buttonPanel.add(btnAdicionarProduto);

        JButton btnVerProdutos = new JButton("Ver Produtos");
        btnVerProdutos.addActionListener(e -> {
        verProduto verProdutoWindow = new verProduto(modeloProdutos, infoPanel, false);
        verProdutoWindow.setVisible(true);
        });
        buttonPanel.add(btnVerProdutos);

        JButton btnRegistrarVenda = new JButton("Registrar Venda");
        btnRegistrarVenda.addActionListener(e -> {
        EscolherTipoProduto escolherTipoVendaWindow = new EscolherTipoProduto(modeloProdutos, infoPanel, true); // true para modo de venda
        escolherTipoVendaWindow.setVisible(true);
        });
        buttonPanel.add(btnRegistrarVenda);

        JButton btnAtualizarProduto = new JButton("Listar Agendamentos");
        btnAtualizarProduto.addActionListener(e -> {
        infoPanel.removeAll(); // Limpar o painel
        Agenda.agendamentos.forEach(agendamento -> {
        infoPanel.add(new JLabel(agendamento.toString()));
        });
        infoPanel.revalidate();
        infoPanel.repaint();
        });
        buttonPanel.add(btnAtualizarProduto);

    }
    
    private void atualizarPainelInfo(JPanel novoPainel) {
        infoPanel.removeAll();
        infoPanel.add(novoPainel);
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PetShop().setVisible(true));
    }
} 