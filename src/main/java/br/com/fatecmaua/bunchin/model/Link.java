package br.com.fatecmaua.bunchin.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String codigo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", referencedColumnName = "n_registro", nullable = false)
    private Funcionario funcionario;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    public Link() {}

    public Link(Integer id, String email, String codigo, Funcionario funcionario, Instant dataCriacao) {
        this.id = id;
        this.email = email;
        this.codigo = codigo;
        this.funcionario = funcionario;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
