package flowbank.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private Integer id;
    private Integer usuarioId;
    private String tipo;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String descricao;

    public Transacao() {}

    public Transacao(Integer usuarioId, String tipo, BigDecimal valor, String descricao) {
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
