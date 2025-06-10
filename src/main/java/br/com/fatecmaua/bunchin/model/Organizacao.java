package br.com.fatecmaua.bunchin.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_organizacao")
public class Organizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organizacao")
    private Integer idOrganizacao;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 14, unique = true)
    private String cnpj;

    @Column(length = 200)
    private String endereco;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column(nullable = false, length = 1)
    private String status = "1";

    @Column(name = "data_criacao")
    private Instant dataCriacao;

    public Organizacao() {}

    public Organizacao(Integer idOrganizacao, String nome, String cnpj, String endereco, String telefone, String email, String status, Instant dataCriacao) {
        this.idOrganizacao = idOrganizacao;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    @PrePersist
    protected void onCreate() {
        dataCriacao = Instant.now();
    }

    public Integer getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(Integer idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
