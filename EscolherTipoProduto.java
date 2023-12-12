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
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EscolherTipoProduto extends JFrame {
    private DefaultListModel<Produto> modeloProdutos;
    private JPanel infoPanel; // Painel para exibir informações ou ações
    private boolean modoVenda;

    public EscolherTipoProduto(DefaultListModel<Produto> modeloProdutos, JPanel infoPanel, boolean modoVenda) {
        this.modeloProdutos = modeloProdutos;
        this.infoPanel = infoPanel;
        this.modoVenda = modoVenda;

        setTitle(modoVenda ? "Registrar Venda" : "Adicionar Produto");
        setSize(300, 100);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnProdutoFisico = new JButton("Produto Físico");
        btnProdutoFisico.addActionListener(e -> {
            if (modoVenda) {
                registrarVendaProdutoFisico();
            } else {
                adicionarProduto("Fisico");
            }
        });
        add(btnProdutoFisico);

        JButton btnProdutoServico = new JButton("Produto Serviço");
        btnProdutoServico.addActionListener(e -> {
            if (modoVenda) {
                registrarVendaProdutoServico();
            } else {
                adicionarProduto("Servico");
            }
        });
        add(btnProdutoServico);

        setVisible(true);
    }

    private void adicionarProduto(String tipoProduto) {
        Adicionar adicionarWindow = new Adicionar(modeloProdutos, tipoProduto);
        adicionarWindow.setVisible(true);
    }

    private void registrarVendaProdutoFisico() {
        JFrame vendaFrame = new JFrame("Registrar Venda - Produto Físico");
        vendaFrame.setLayout(new BorderLayout());
        vendaFrame.setSize(500, 300);

        JPanel painelProdutos = new JPanel();
        painelProdutos.setLayout(new BoxLayout(painelProdutos, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(painelProdutos);
        vendaFrame.add(scrollPane, BorderLayout.CENTER);

        HashMap<ProdutoFisico, JTextField> produtoQuantidadeMap = new HashMap<>();

        for (int i = 0; i < modeloProdutos.size(); i++) {
            Produto produto = modeloProdutos.get(i);
            if (produto instanceof ProdutoFisico) {
                JPanel produtoPainel = new JPanel(new FlowLayout());
                JCheckBox checkBox = new JCheckBox(produto.toString());
                JTextField txtQuantidade = new JTextField(5);
                produtoPainel.add(checkBox);
                produtoPainel.add(new JLabel("Quantidade:"));
                produtoPainel.add(txtQuantidade);
                painelProdutos.add(produtoPainel);
                produtoQuantidadeMap.put((ProdutoFisico) produto, txtQuantidade);
            }
        }

        if (produtoQuantidadeMap.isEmpty()) {
            JOptionPane.showMessageDialog(vendaFrame, "Não há produtos físicos cadastrados.");
            return;
        }

        JButton btnVender = new JButton("Vender");
        btnVender.addActionListener(e -> processarVenda(produtoQuantidadeMap, vendaFrame));
        vendaFrame.add(btnVender, BorderLayout.SOUTH);

        vendaFrame.setVisible(true);
    }
    private void processarVenda(HashMap<ProdutoFisico, JTextField> produtoQuantidadeMap, JFrame vendaFrame) {
        try {
            for (Map.Entry<ProdutoFisico, JTextField> entry : produtoQuantidadeMap.entrySet()) {
                ProdutoFisico produto = entry.getKey();
                JTextField txtQuantidade = entry.getValue();
    
                if (!txtQuantidade.getText().trim().isEmpty()) {
                    int quantidadeVenda = Integer.parseInt(txtQuantidade.getText());
                    
                    if (quantidadeVenda <= 0) {
                        JOptionPane.showMessageDialog(vendaFrame, "Quantidade inválida para o produto: " + produto.getNome());
                        return;
                    }
    
                    if (quantidadeVenda > produto.getQuantidade()) {
                        JOptionPane.showMessageDialog(vendaFrame, "Quantidade de venda excede o estoque para o produto: " + produto.getNome());
                        return;
                    }
    
                    // Subtraindo a quantidade vendida do estoque do produto
                    produto.setQuantidade(produto.getQuantidade() - quantidadeVenda);
                }
            }
    
            // Após processar todas as vendas
            JOptionPane.showMessageDialog(vendaFrame, "Venda(s) registrada(s) com sucesso.");
            vendaFrame.dispose(); // Fechar a janela de venda após completar o processo
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vendaFrame, "Por favor, insira uma quantidade válida.");
        }
    }
    private void registrarVendaProdutoServico() {
        JFrame agendarFrame = new JFrame("Agendar Serviço");
        agendarFrame.setLayout(new BorderLayout());
        agendarFrame.setSize(400, 300);

        JPanel painelServicos = new JPanel();
        painelServicos.setLayout(new BoxLayout(painelServicos, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(painelServicos);
        agendarFrame.add(scrollPane, BorderLayout.CENTER);

        HashMap<ProdutoServico, JCheckBox> servicoCheckBoxMap = new HashMap<>();

        for (int i = 0; i < modeloProdutos.size(); i++) {
            Produto produto = modeloProdutos.get(i);
            if (produto instanceof ProdutoServico) {
                JCheckBox checkBox = new JCheckBox(produto.toString());
                painelServicos.add(checkBox);
                servicoCheckBoxMap.put((ProdutoServico) produto, checkBox);
            }
        }

        if (servicoCheckBoxMap.isEmpty()) {
            JOptionPane.showMessageDialog(agendarFrame, "Não há serviços cadastrados.");
            return;
        }

        // Painel para os campos de entrada de data e hora
        JPanel painelDataHora = new JPanel();
        painelDataHora.setLayout(new FlowLayout());

        JTextField campoDia = new JTextField(2); // Campo para o dia
        JTextField campoMes = new JTextField(2); // Campo para o mês
        JTextField campoHora = new JTextField(2); // Campo para a hora

        painelDataHora.add(new JLabel("Dia:"));
        painelDataHora.add(campoDia);
        painelDataHora.add(new JLabel("Mês:"));
        painelDataHora.add(campoMes);
        painelDataHora.add(new JLabel("Hora:"));
        painelDataHora.add(campoHora);

        agendarFrame.add(painelDataHora, BorderLayout.NORTH);

        JButton btnConcluir = new JButton("Concluir");
        btnConcluir.addActionListener(e -> {
            try {
                int dia = Integer.parseInt(campoDia.getText());
                int mes = Integer.parseInt(campoMes.getText());
                int hora = Integer.parseInt(campoHora.getText());

                List<String> servicosSelecionados = new ArrayList<>();
                for (Map.Entry<ProdutoServico, JCheckBox> entry : servicoCheckBoxMap.entrySet()) {
                    if (entry.getValue().isSelected()) {
                        servicosSelecionados.add(entry.getKey().getNome());
                    }
                }

                if (servicosSelecionados.isEmpty()) {
                    JOptionPane.showMessageDialog(agendarFrame, "Nenhum serviço selecionado.");
                    return;
                }

                String agrupamentoServicos = String.join(", ", servicosSelecionados);
                Agendamento agendamento = new Agendamento(agrupamentoServicos, dia, mes, hora);
                Agenda.agendamentos.add(agendamento); // Adicionando ao array global
                JOptionPane.showMessageDialog(agendarFrame, "Agendamento salvo com sucesso.");

                // Aqui você pode salvar o agendamento como desejar

                JOptionPane.showMessageDialog(agendarFrame, "Agendamento salvo com sucesso.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(agendarFrame, "Por favor, insira valores válidos para dia, mês e hora.");
            }
        });

        agendarFrame.add(btnConcluir, BorderLayout.SOUTH);
        agendarFrame.setVisible(true);
    }
}