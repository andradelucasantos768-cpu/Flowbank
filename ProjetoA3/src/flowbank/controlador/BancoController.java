package flowbank.controlador;

import flowbank.dao.TransacaoDAO;
import flowbank.dao.UsuarioDAO;
import flowbank.modelo.Transacao;
import flowbank.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BancoController {

    private final UsuarioDAO usuarioDAO;
    private final TransacaoDAO transacaoDAO;
    private Usuario usuarioLogado;

    public BancoController() {
        this.usuarioDAO = new UsuarioDAO();
        this.transacaoDAO = new TransacaoDAO();
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean login(String email, String senha) throws SQLException {
        usuarioLogado = usuarioDAO.login(email, senha);
        return usuarioLogado != null;
    }

    public void logout() {
        usuarioLogado = null;
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        if (usuarioDAO.emailExiste(usuario.getEmail())) {
            throw new SQLException("Este e-mail já está cadastrado.");
        }
        usuarioDAO.inserir(usuario);
    }

    public void atualizarUsuario(Usuario u, boolean atualizarSenha) throws SQLException {
        usuarioDAO.atualizar(u, atualizarSenha);
        this.usuarioLogado = u;
    }

    public void excluirUsuario() throws SQLException {
        if (usuarioLogado != null) {
            usuarioDAO.excluir(usuarioLogado.getId());
            logout();
        }
    }

    public void depositar(BigDecimal valor) throws SQLException {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new SQLException("O valor do depósito deve ser positivo.");
        }
        BigDecimal novoSaldo = usuarioLogado.getSaldo().add(valor);
        usuarioDAO.atualizarSaldo(usuarioLogado.getId(), novoSaldo);
        usuarioLogado.setSaldo(novoSaldo);
        Transacao t = new Transacao(usuarioLogado.getId(), "DEPOSITO", valor, "Depósito em conta");
        transacaoDAO.inserir(t);
    }

    public void sacar(BigDecimal valor) throws SQLException {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new SQLException("O valor do saque deve ser positivo.");
        }
        if (usuarioLogado.getSaldo().compareTo(valor) < 0) {
            throw new SQLException("Saldo insuficiente para realizar o saque.");
        }
        BigDecimal novoSaldo = usuarioLogado.getSaldo().subtract(valor);
        usuarioDAO.atualizarSaldo(usuarioLogado.getId(), novoSaldo);
        usuarioLogado.setSaldo(novoSaldo);
        Transacao t = new Transacao(usuarioLogado.getId(), "SAQUE", valor, "Saque em conta");
        transacaoDAO.inserir(t);
    }

    public void transferir(String emailDestino, BigDecimal valor) throws SQLException {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new SQLException("O valor da transferência deve ser positivo.");
        }
        if (usuarioLogado.getEmail().equalsIgnoreCase(emailDestino)) {
            throw new SQLException("Não é possível transferir para si mesmo.");
        }
        if (usuarioLogado.getSaldo().compareTo(valor) < 0) {
            throw new SQLException("Saldo insuficiente para realizar a transferência.");
        }
        Usuario destino = usuarioDAO.buscarPorEmail(emailDestino);
        if (destino == null) {
            throw new SQLException("Usuário destinatário não encontrado.");
        }
        BigDecimal novoSaldoRemetente = usuarioLogado.getSaldo().subtract(valor);
        usuarioDAO.atualizarSaldo(usuarioLogado.getId(), novoSaldoRemetente);
        usuarioLogado.setSaldo(novoSaldoRemetente);
        BigDecimal novoSaldoDestinatario = destino.getSaldo().add(valor);
        usuarioDAO.atualizarSaldo(destino.getId(), novoSaldoDestinatario);
        Transacao tSaindo = new Transacao(usuarioLogado.getId(), "TRANSFERENCIA",
                valor, "Transferência para " + destino.getNome());
        transacaoDAO.inserir(tSaindo);
        Transacao tEntrando = new Transacao(destino.getId(), "TRANSFERENCIA",
                valor, "Transferência de " + usuarioLogado.getNome());
        transacaoDAO.inserir(tEntrando);
    }

    public List<Transacao> obterHistorico() throws SQLException {
        return transacaoDAO.buscarPorUsuario(usuarioLogado.getId());
    }
}
