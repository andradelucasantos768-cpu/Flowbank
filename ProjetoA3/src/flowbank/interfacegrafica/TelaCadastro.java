package flowbank.interfacegrafica;

import flowbank.controlador.BancoController;
import flowbank.modelo.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

public class TelaCadastro extends javax.swing.JFrame {

    private final BancoController controller;
    private final javax.swing.JFrame telaAnterior;
    private final boolean modoAtualizacao;
    private boolean senhaModificada = false;

    public TelaCadastro(BancoController controller, javax.swing.JFrame telaAnterior, boolean modoAtualizacao) {
        this.controller = controller;
        this.telaAnterior = telaAnterior;
        this.modoAtualizacao = modoAtualizacao;
        initComponents();
        setLocationRelativeTo(null);
        configurarModo();
    }

    private void configurarModo() {
        txtId.setEditable(false);
        if (modoAtualizacao) {
            lblTitulo.setText("Atualizar Cadastro");
            btnSalvar.setText("Atualizar");
            btnExcluir.setVisible(true);

            Usuario u = controller.getUsuarioLogado();
            txtId.setText(String.valueOf(u.getId()));
            txtNome.setText(u.getNome());
            txtEmail.setText(u.getEmail());
            txtCelular.setText(u.getCelular());
            txtDataNascimento.setText(u.getDataNascimento());

            String senhaReal = controller.getUsuarioLogado().getSenha();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < senhaReal.length(); i++) {
                sb.append("*");
            }
            String placeholder = sb.toString();
            txtSenha.setText(placeholder);
            txtConfirmaSenha.setText(placeholder);

            DocumentListener listener = new DocumentListener() {
                public void insertUpdate(DocumentEvent e) {
                    senhaModificada = true;
                }

                public void removeUpdate(DocumentEvent e) {
                    senhaModificada = true;
                }

                public void changedUpdate(DocumentEvent e) {
                    senhaModificada = true;
                }
            };

            txtSenha.getDocument().addDocumentListener(listener);
            txtConfirmaSenha.getDocument().addDocumentListener(listener);

        } else {
            lblTitulo.setText("Cadastro");
            btnSalvar.setText("Cadastrar");
            btnExcluir.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblCelular = new javax.swing.JLabel();
        try {
            txtCelular = new javax.swing.JFormattedTextField(new MaskFormatter("(##) #####-####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lblDataNascimento = new javax.swing.JLabel();
        try {
            txtDataNascimento = new javax.swing.JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        lblConfirmaSenha = new javax.swing.JLabel();
        txtConfirmaSenha = new javax.swing.JPasswordField();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlowBank - Cadastro");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblTitulo.setText("Cadastro");

        lblId.setText("ID:");
        lblNome.setText("Nome:");
        lblEmail.setText("E-mail:");
        lblCelular.setText("Celular:");
        lblDataNascimento.setText("Data Nascimento:");
        lblSenha.setText("Senha:");
        lblConfirmaSenha.setText("Confirmar Senha:");

        btnSalvar.setText("Cadastrar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblId)
                            .addComponent(lblNome)
                            .addComponent(lblEmail)
                            .addComponent(lblCelular)
                            .addComponent(lblDataNascimento)
                            .addComponent(lblSenha)
                            .addComponent(lblConfirmaSenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCelular)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataNascimento)
                    .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmaSenha)
                    .addComponent(txtConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String celular = txtCelular.getText().replaceAll("[^0-9]", "");
        String senha = new String(txtSenha.getPassword());
        String confirma = new String(txtConfirmaSenha.getPassword());

        boolean atualizarSenha = !modoAtualizacao || senhaModificada;

        if (nome.isEmpty() || email.isEmpty() || celular.isEmpty()
                || (atualizarSenha && (senha.isEmpty() || confirma.isEmpty()))) {
            JOptionPane.showMessageDialog(this,
                    "Os campos Nome, E-mail, Celular, Senha e Confirmação de Senha são obrigatórios!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (atualizarSenha && !senha.equals(confirma)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não coincidem!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            Usuario u = modoAtualizacao ? controller.getUsuarioLogado() : new Usuario();
            u.setNome(nome);
            u.setEmail(email);
            u.setCelular(celular); // já sem máscara: apenas dígitos
            u.setDataNascimento(txtDataNascimento.getText());
            if (atualizarSenha) {
                u.setSenha(senha);
            }

            if (modoAtualizacao) {
                controller.atualizarUsuario(u, atualizarSenha);
                JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
            } else {
                controller.cadastrarUsuario(u);
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            }
            btnCancelarActionPerformed(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir seu cadastro?\nEsta ação não pode ser desfeita.",
                "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.excluirUsuario();
                JOptionPane.showMessageDialog(this, "Cadastro excluído com sucesso.");
                new TelaLogin(controller).setVisible(true);
                this.dispose();
                if (telaAnterior != null) {
                    telaAnterior.dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (modoAtualizacao) {
            if (telaAnterior != null) {
                telaAnterior.setVisible(true);
            }
        } else {
            new TelaLogin(controller).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Falha ao inicializar LookAndFeel");
        }
        SwingUtilities.invokeLater(() -> {
            BancoController controller = new BancoController();
            new TelaCadastro(controller, null, false).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConfirmaSenha;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtConfirmaSenha;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
