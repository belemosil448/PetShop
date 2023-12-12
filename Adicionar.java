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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Adicionar extends JFrame {
    private JTextField txtNome;
    private JTextField txtValor;
    private JTextField txtQuantidade; // Campo específico para Produto Físico
    private JTextField txtTempo;      // Campo específico para Produto Serviço
    private JButton btnSalvar;
    private DefaultListModel<Produto> modeloProdutos;
    private String tipoProduto;

    public Adicionar(DefaultListModel<Produto> modeloProdutos, String tipoProduto) {
        super(); 
        this.modeloProdutos = modeloProdutos;
        this.tipoProduto = tipoProduto;
        

        // Configurações básicas da janela
        setTitle("Adicionar " + tipoProduto);
        setSize(400, 200);
        setLayout(new GridLayout(0, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Adicionando campos comuns
        txtNome = new JTextField(10);
        add(new JLabel("Nome:"));
        add(txtNome);

        txtValor = new JTextField(10);
        add(new JLabel("Valor:"));
        add(txtValor);

        // Adicionando campos específicos para cada tipo de produto
        if ("Fisico".equals(tipoProduto)) {
            txtQuantidade = new JTextField(10);
            add(new JLabel("Quantidade:"));
            add(txtQuantidade);
        } else if ("Servico".equals(tipoProduto)) {
            txtTempo = new JTextField(10);
            add(new JLabel("Tempo (min):"));
            add(txtTempo);
        }

        // Botão para salvar o novo produto
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });
        add(btnSalvar);
        
        // Tornando a janela visível
        setVisible(true);
    }

    private void salvarProduto() {
    try {
        String nome = txtNome.getText();
        float valor = Float.parseFloat(txtValor.getText());
        Produto novoProduto;

        if ("Fisico".equals(tipoProduto)) {
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            novoProduto = new ProdutoFisico(nome, valor, quantidade); 
        } else if ("Servico".equals(tipoProduto)) {
            int tempo = Integer.parseInt(txtTempo.getText());
            novoProduto = new ProdutoServico(nome, valor, tempo); 
        } else {
            throw new IllegalStateException("Tipo de produto desconhecido.");
        }

        modeloProdutos.addElement(novoProduto);
        JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");

        // Limpar campos após adicionar
        txtNome.setText("");
        txtValor.setText("");
        if (txtQuantidade != null) txtQuantidade.setText("");
        if (txtTempo != null) txtTempo.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.");
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
