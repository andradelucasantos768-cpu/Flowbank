package flowbank.dao;

import flowbank.modelo.Transacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    public void inserir(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes (usuario_id, tipo, valor, data_hora, descricao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, transacao.getUsuarioId());
            ps.setString(2, transacao.getTipo());
            ps.setBigDecimal(3, transacao.getValor());
            ps.setTimestamp(4, Timestamp.valueOf(transacao.getDataHora()));
            ps.setString(5, transacao.getDescricao());
            ps.executeUpdate();
        }
    }

    public List<Transacao> buscarPorUsuario(Integer usuarioId) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE usuario_id = ? ORDER BY data_hora DESC";
        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transacao t = new Transacao();
                    t.setId(rs.getInt("id"));
                    t.setUsuarioId(rs.getInt("usuario_id"));
                    t.setTipo(rs.getString("tipo"));
                    t.setValor(rs.getBigDecimal("valor"));
                    t.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                    t.setDescricao(rs.getString("descricao"));
                    transacoes.add(t);
                }
            }
        }
        return transacoes;
    }
}
