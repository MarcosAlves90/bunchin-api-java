package br.com.fatecmaua.bunchin.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_projetos")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Integer idProjeto;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organizacao_id", referencedColumnName = "id_organizacao", nullable = false)
    private Organizacao organizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "responsavel_id", referencedColumnName = "n_registro", nullable = false)
    private Funcionario responsavel;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim_prevista")
    private LocalDate dataFimPrevista;

    @Column(name = "data_fim_real")
    private LocalDate dataFimReal;

    @Column(nullable = false, length = 20)
    private String status = "planejamento";

    @Column(name = "data_criacao")
    private Instant dataCriacao;

    public Projeto() {}

    public Projeto(Integer idProjeto, String nome, String descricao, Organizacao organizacao, Funcionario responsavel, LocalDate dataInicio, LocalDate dataFimPrevista, LocalDate dataFimReal, String status, Instant dataCriacao) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.organizacao = organizacao;
        this.responsavel = responsavel;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.dataFimReal = dataFimReal;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    @PrePersist
    protected void onCreate() {
        dataCriacao = Instant.now();
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(LocalDate dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public LocalDate getDataFimReal() {
        return dataFimReal;
    }

    public void setDataFimReal(LocalDate dataFimReal) {
        this.dataFimReal = dataFimReal;
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
