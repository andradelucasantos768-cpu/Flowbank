package flowbank.interfacegrafica;

import flowbank.controlador.BancoController;
import flowbank.modelo.Transacao;
import flowbank.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal extends javax.swing.JFrame {

    private BancoController controller;
    private DefaultTableModel tableModel;
    private boolean saldoVisivel = true;

    public TelaPrincipal() {
        this.controller = new BancoController();
        initComponents();
        configurarTabela();
        setLocationRelativeTo(null);
    }

    public TelaPrincipal(BancoController controller) {
        this.controller = controller;
        initComponents();
        configurarTabela();
        atualizarDados();
        setLocationRelativeTo(null);
    }

    private void configurarTabela() {
        String[] colunas = {"Data", "Tipo", "Descrição", "Valor"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblExtrato.setModel(tableModel);
    }

    public void atualizarDados() {
        Usuario user = controller.getUsuarioLogado();
        if (user != null) {
            lblBoasVindas.setText("Olá, " + user.getNome());
            if (saldoVisivel) {
                lblSaldo.setText(String.format("R$ %,.2f", user.getSaldo()));
                btnOcultarSaldo.setText("Ocultar");
            } else {
                lblSaldo.setText("R$ *****");
                btnOcultarSaldo.setText("Mostrar");
            }
            try {
                List<Transacao> historico = controller.obterHistorico();
                tableModel.setRowCount(0);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                for (Transacao t : historico) {
                    tableModel.addRow(new Object[]{
                        t.getDataHora().format(dtf),
                        t.getTipo(),
                        t.getDescricao(),
                        String.format("R$ %,.2f", t.getValor())
                    });
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar extrato.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblBoasVindas = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        pnlCorpo = new javax.swing.JPanel();
        lblTituloSaldo = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        btnOcultarSaldo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExtrato = new javax.swing.JTable();
        pnlAcoes = new javax.swing.JPanel();
        btnDeposito = new javax.swing.JButton();
        btnSaque = new javax.swing.JButton();
        btnTransferencia = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnEditarPerfil = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FlowBank - Dashboard");

        lblBoasVindas.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblBoasVindas.setText("Olá, Usuário");

        btnLogout.setText("Sair");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblBoasVindas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(20, 20, 20))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBoasVindas)
                    .addComponent(btnLogout))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        lblTituloSaldo.setText("Saldo Disponível:");

        lblSaldo.setFont(new java.awt.Font("Segoe UI", 1, 36));
        lblSaldo.setText("R$ 0,00");

        btnOcultarSaldo.setText("Ocultar");
        btnOcultarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarSaldoActionPerformed(evt);
            }
        });

        tblExtrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Data", "Tipo", "Descrição", "Valor"}
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jScrollPane1.setViewportView(tblExtrato);

        pnlAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações"));

        btnDeposito.setText("Depósito");
        btnDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositoActionPerformed(evt);
            }
        });

        btnSaque.setText("Saque");
        btnSaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaqueActionPerformed(evt);
            }
        });

        btnTransferencia.setText("Transferência");
        btnTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferenciaActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar Extrato");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnEditarPerfil.setText("Atualizar dados cadastrados");
        btnEditarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPerfilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAcoesLayout = new javax.swing.GroupLayout(pnlAcoes);
        pnlAcoes.setLayout(pnlAcoesLayout);
        pnlAcoesLayout.setHorizontalGroup(
            pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeposito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAcoesLayout.setVerticalGroup(
            pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCorpoLayout = new javax.swing.GroupLayout(pnlCorpo);
        pnlCorpo.setLayout(pnlCorpoLayout);
        pnlCorpoLayout.setHorizontalGroup(
            pnlCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCorpoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(pnlCorpoLayout.createSequentialGroup()
                        .addGroup(pnlCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloSaldo)
                            .addGroup(pnlCorpoLayout.createSequentialGroup()
                                .addComponent(lblSaldo)
                                .addGap(18, 18, 18)
                                .addComponent(btnOcultarSaldo)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(pnlAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlCorpoLayout.setVerticalGroup(
            pnlCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCorpoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCorpoLayout.createSequentialGroup()
                        .addComponent(lblTituloSaldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSaldo)
                            .addComponent(btnOcultarSaldo))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCorpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlCorpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        controller.logout();
        new TelaLogin(controller).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnDepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositoActionPerformed
        String valorStr = JOptionPane.showInputDialog(this, "Valor do depósito:");
        if (valorStr != null && !valorStr.isEmpty()) {
            try {
                BigDecimal valor = new BigDecimal(valorStr.replace(",", "."));
                controller.depositar(valor);
                JOptionPane.showMessageDialog(this, "Depósito realizado!");
                atualizarDados();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Valor inválido!");
            }
        }
    }//GEN-LAST:event_btnDepositoActionPerformed

    private void btnSaqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaqueActionPerformed
        String valorStr = JOptionPane.showInputDialog(this, "Valor do saque:");
        if (valorStr != null && !valorStr.isEmpty()) {
            try {
                BigDecimal valor = new BigDecimal(valorStr.replace(",", "."));
                controller.sacar(valor);
                JOptionPane.showMessageDialog(this, "Saque realizado!");
                atualizarDados();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Valor inválido!");
            }
        }
    }//GEN-LAST:event_btnSaqueActionPerformed

    private void btnTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferenciaActionPerformed
        javax.swing.JTextField fieldEmail = new javax.swing.JTextField();
        javax.swing.JTextField fieldValor = new javax.swing.JTextField();
        Object[] message = {
            "E-mail do destinatário:", fieldEmail,
            "Valor:", fieldValor
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Transferência", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                BigDecimal valor = new BigDecimal(fieldValor.getText().replace(",", "."));
                controller.transferir(fieldEmail.getText(), valor);
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
                atualizarDados();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Dados inválidos!");
            }
        }
    }//GEN-LAST:event_btnTransferenciaActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        atualizarDados();
        JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPerfilActionPerformed
        new TelaCadastro(controller, this, true).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnEditarPerfilActionPerformed

    private void btnOcultarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarSaldoActionPerformed
        saldoVisivel = !saldoVisivel;
        if (saldoVisivel) {
            lblSaldo.setText(String.format("R$ %,.2f", controller.getUsuarioLogado().getSaldo()));
            btnOcultarSaldo.setText("Ocultar");
        } else {
            lblSaldo.setText("R$ *****");
            btnOcultarSaldo.setText("Mostrar");
        }
    }//GEN-LAST:event_btnOcultarSaldoActionPerformed

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Falha ao inicializar LookAndFeel");
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDeposito;
    private javax.swing.JButton btnEditarPerfil;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOcultarSaldo;
    private javax.swing.JButton btnSaque;
    private javax.swing.JButton btnTransferencia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblTituloSaldo;
    private javax.swing.JPanel pnlAcoes;
    private javax.swing.JPanel pnlCorpo;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTable tblExtrato;
    // End of variables declaration//GEN-END:variables
}
