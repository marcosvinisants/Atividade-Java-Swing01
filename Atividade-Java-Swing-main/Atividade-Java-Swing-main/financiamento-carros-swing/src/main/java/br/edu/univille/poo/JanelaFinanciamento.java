package br.edu.univille.poo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class JanelaFinanciamento extends JFrame {

    private JComboBox<String> cbMarca;
    private JTextField txtModelo;
    private JComboBox<String> cbAno;
    private JTextField txtValorVeiculo;

    private JRadioButton rbNovo;
    private JRadioButton rbUsado;
    private ButtonGroup bgTipo;

    private JPanel panelUsado;
    private JTextField txtQuilometragem;
    private JTextField txtProprietarios;

    private JCheckBox chkPossuiEntrada;
    private JLabel lblEntrada;
    private JTextField txtEntrada;
    private JComboBox<Integer> cbParcelas;

    private JButton btnCalcular;
    private JButton btnLimpar;

    private JPanel panelResultado;
    private JLabel lblValorFinanciado;
    private JLabel lblValorParcela;
    private JLabel lblTotalPagar;

    public JanelaFinanciamento() {
        super("Financiamento de Carros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 750);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        mainPanel.add(criarPainelVeiculo());
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(criarPainelTipo());
        mainPanel.add(Box.createVerticalStrut(10));

        panelUsado = criarPainelUsado();
        panelUsado.setVisible(false);
        mainPanel.add(panelUsado);
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(criarPainelFinanciamento());
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(criarPainelBotoes());
        mainPanel.add(Box.createVerticalStrut(15));

        panelResultado = criarPainelResultado();
        panelResultado.setVisible(false);
        mainPanel.add(panelResultado);

        add(new JScrollPane(mainPanel));
    }

    private JPanel criarPainelVeiculo() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dados do Veículo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Marca:"), gbc);

        String[] marcas = {"Ford", "Volkswagen", "Chevrolet", "FIAT", "Toyota", "Honda", "Hyundai"};
        cbMarca = new JComboBox<>(marcas);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(cbMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Modelo:"), gbc);

        txtModelo = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtModelo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Ano:"), gbc);

        DefaultComboBoxModel<String> modelAnos = new DefaultComboBoxModel<>();
        for (int ano = 2026; ano >= 2000; ano--) {
            modelAnos.addElement(String.valueOf(ano));
        }
        cbAno = new JComboBox<>(modelAnos);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(cbAno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Valor (R$):"), gbc);

        txtValorVeiculo = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtValorVeiculo, gbc);

        return panel;
    }

    private JPanel criarPainelTipo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Tipo:"));

        rbNovo = new JRadioButton("NOVO", true);
        rbUsado = new JRadioButton("USADO");

        bgTipo = new ButtonGroup();
        bgTipo.add(rbNovo);
        bgTipo.add(rbUsado);

        panel.add(rbNovo);
        panel.add(rbUsado);

        ActionListener listenerTipo = e -> {
            panelUsado.setVisible(rbUsado.isSelected());
            revalidate();
            repaint();
        };

        rbNovo.addActionListener(listenerTipo);
        rbUsado.addActionListener(listenerTipo);

        return panel;
    }

    private JPanel criarPainelUsado() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createDashedBorder(Color.GRAY),
                "Dados do Veículo Usado",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Quilometragem:"), gbc);

        txtQuilometragem = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtQuilometragem, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Proprietários:"), gbc);

        txtProprietarios = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtProprietarios, gbc);

        return panel;
    }

    private JPanel criarPainelFinanciamento() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Financiamento"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        chkPossuiEntrada = new JCheckBox("Possui entrada?");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(chkPossuiEntrada, gbc);

        lblEntrada = new JLabel("Entrada (R$):");
        lblEntrada.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        panel.add(lblEntrada, gbc);

        txtEntrada = new JTextField();
        txtEntrada.setVisible(false);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtEntrada, gbc);

        chkPossuiEntrada.addActionListener(e -> {
            boolean visivel = chkPossuiEntrada.isSelected();
            lblEntrada.setVisible(visivel);
            txtEntrada.setVisible(visivel);
            if (!visivel) {
                txtEntrada.setText("");
            }
            revalidate();
            repaint();
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Parcelas:"), gbc);

        Integer[] parcelas = {12, 24, 36, 48, 60};
        cbParcelas = new JComboBox<>(parcelas);
        cbParcelas.setSelectedItem(36);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(cbParcelas, gbc);

        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        btnCalcular = new JButton("CALCULAR");
        btnLimpar = new JButton("LIMPAR");

        btnCalcular.addActionListener(e -> calcularFinanciamento());
        btnLimpar.addActionListener(e -> limparFormulario());

        panel.add(btnCalcular);
        panel.add(btnLimpar);

        return panel;
    }

    private JPanel criarPainelResultado() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Resultado"));

        lblValorFinanciado = new JLabel("Valor financiado: R$ 0,00");
        lblValorParcela = new JLabel("Valor da parcela: R$ 0,00");
        lblTotalPagar = new JLabel("Total a pagar: R$ 0,00");

        Font fontBold = lblValorFinanciado.getFont().deriveFont(Font.BOLD, 13f);
        lblValorFinanciado.setFont(fontBold);
        lblValorParcela.setFont(fontBold);
        lblTotalPagar.setFont(fontBold);

        panel.add(lblValorFinanciado);
        panel.add(lblValorParcela);
        panel.add(lblTotalPagar);

        return panel;
    }

    private void calcularFinanciamento() {
        try {
            if (txtModelo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o modelo do veículo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (txtValorVeiculo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o valor do veículo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double valorVeiculo = Double.parseDouble(txtValorVeiculo.getText().trim().replace(",", "."));
            double entrada = 0.0;

            if (chkPossuiEntrada.isSelected()) {
                if (txtEntrada.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o valor da entrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                entrada = Double.parseDouble(txtEntrada.getText().trim().replace(",", "."));
                if (entrada >= valorVeiculo) {
                    JOptionPane.showMessageDialog(this, "A entrada deve ser menor que o valor do veículo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (rbUsado.isSelected()) {
                if (txtQuilometragem.getText().trim().isEmpty() || txtProprietarios.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha os dados do veículo usado (Quilometragem e Proprietários).", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            int numeroParcelas = (Integer) cbParcelas.getSelectedItem();
            double taxaJuros = 0.02;

            CalculadoraFinanciamento calc = new CalculadoraFinanciamento(valorVeiculo, entrada, numeroParcelas, taxaJuros);

            DecimalFormat df = new DecimalFormat("R$ #,##0.00");
            lblValorFinanciado.setText("Valor financiado: " + df.format(calc.getValorFinanciado()));
            lblValorParcela.setText("Valor da parcela: " + df.format(calc.getValorParcela()));
            lblTotalPagar.setText("Total a pagar: " + df.format(calc.getTotalAPagar()));

            panelResultado.setVisible(true);
            revalidate();
            repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos nos campos de valor/entrada.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparFormulario() {
        cbMarca.setSelectedIndex(0);
        txtModelo.setText("");
        cbAno.setSelectedIndex(0);
        txtValorVeiculo.setText("");

        rbNovo.setSelected(true);
        panelUsado.setVisible(false);
        txtQuilometragem.setText("");
        txtProprietarios.setText("");

        chkPossuiEntrada.setSelected(false);
        lblEntrada.setVisible(false);
        txtEntrada.setVisible(false);
        txtEntrada.setText("");
        cbParcelas.setSelectedItem(36);

        panelResultado.setVisible(false);

        revalidate();
        repaint();
    }
}