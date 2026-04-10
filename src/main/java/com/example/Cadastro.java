package com.example;

import javax.swing.*;
import java.awt.*;

public class Cadastro extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Cadastro.class.getName());

    // Components
    private JTextField txtNome;
    private JTextField txtIdade;
    private JCheckBox chkPromo;
    private JCheckBox chkTermos;
    private JLabel lblResultado;
    private JButton btnCadastrar;

    public Cadastro() {
        initComponents();
    }

    private void initComponents() {
        // Initialize components
        txtNome = new JTextField();
        txtIdade = new JTextField();
        chkPromo = new JCheckBox("Desejo receber mensagens promocionais");
        chkTermos = new JCheckBox("Concordo com os termos de uso");
        lblResultado = new JLabel();
        btnCadastrar = new JButton("Cadastrar-se");

        JLabel title = new JLabel("Cadastro da Loja");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 36));

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblIdade = new JLabel("Idade:");

        // Styles
        btnCadastrar.setBackground(new Color(5, 94, 203));
        btnCadastrar.setForeground(Color.WHITE);

        // Events
        btnCadastrar.addActionListener(e -> handleCadastro());

        // Layout
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(lblNome);
        panel.add(txtNome);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(lblIdade);
        panel.add(txtIdade);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(chkPromo);
        panel.add(chkTermos);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(btnCadastrar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(lblResultado);

        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void handleCadastro() {
        String nome = txtNome.getText().trim();
        String idade = txtIdade.getText().trim();

        if (!validateFields(nome, idade)) {
            return;
        }

        String promoStatus = chkPromo.isSelected() ? "Ativadas" : "Desativadas";

        showSuccess(nome, idade, promoStatus);
    }

    private boolean validateFields(String nome, String idade) {
        if (nome.isEmpty() || idade.isEmpty()) {
            showError("Por favor, preencha todos os campos.");
            return false;
        }

        if (!chkTermos.isSelected()) {
            showError("Você precisa aceitar os termos.");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        lblResultado.setText(message);
        lblResultado.setForeground(Color.RED);
    }

    private void showSuccess(String nome, String idade, String promoStatus) {
        lblResultado.setText(
                "Cadastro realizado para " + nome +
                        " (idade: " + idade + "). Promoções: " + promoStatus + "."
        );
        lblResultado.setForeground(new Color(2, 11, 0));
    }

    public static void main(String[] args) {
        setLookAndFeel();

        EventQueue.invokeLater(() -> new Cadastro().setVisible(true));
    }

    private static void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
    }
}