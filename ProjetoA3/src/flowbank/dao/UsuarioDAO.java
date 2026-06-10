package flowbank.dao;

import flowbank.modelo.Usuario;
import flowbank.utilitario.SecurityUtils;
import java.math.BigDecimal;
import java.sql.*;

public class UsuarioDAO {

    public void inserir(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, celular, data_nascimento, senha, saldo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getCelular());
            stmt.setString(4, u.getDataNascimento());
            stmt.setString(5, SecurityUtils.criptografar(u.getSenha()));
            stmt.setBigDecimal(6, u.getSaldo());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
            }
        }
    }

    public void atualizar(Usuario u, boolean atualizarSenha) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE usuarios SET nome = ?, email = ?, celular = ?, data_nascimento = ?");
        if (atualizarSenha) {
            sql.append(", senha = ?");
        }
        sql.append(" WHERE id = ?");
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getCelular());
            stmt.setString(4, u.getDataNascimento());
            if (atualizarSenha) {
                stmt.setString(5, SecurityUtils.criptografar(u.getSenha()));
                stmt.setInt(6, u.getId());
            } else {
                stmt.setInt(5, u.getId());
            }
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Usuario login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaCriptografada = rs.getString("senha");
                    if (SecurityUtils.checkPassword(senha, senhaCriptografada)) {
                        return mapearUsuario(rs);
                    }
                }
            }
        }
        return null;
    }

    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }

    public void atualizarSaldo(Integer id, BigDecimal novoSaldo) throws SQLException {
        String sql = "UPDATE usuarios SET saldo = ? WHERE id = ?";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, novoSaldo);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setCelular(rs.getString("celular"));
        u.setDataNascimento(rs.getString("data_nascimento"));
        u.setSenha(SecurityUtils.descriptografar(rs.getString("senha")));
        u.setSaldo(rs.getBigDecimal("saldo"));
        return u;
    }
}
