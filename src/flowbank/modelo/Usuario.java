package flowbank.modelo;

import java.math.BigDecimal;

public class Usuario {
    private Integer id;
    private String nome;
    private String celular;
    private String email;
    private String dataNascimento;
    private String senha;
    private BigDecimal saldo;

    public Usuario() {
        this.saldo = BigDecimal.ZERO;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
}
