package br.com.fatecmaua.bunchin.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_funcionario_projeto")
public class FuncionarioProjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", referencedColumnName = "n_registro", nullable = false)
    private Funcionario funcionario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "projeto_id", referencedColumnName = "id_projeto", nullable = false)
    private Projeto projeto;

    @Column(name = "data_atribuicao")
    private Instant dataAtribuicao;

    @Column(name = "data_remocao")
    private Instant dataRemocao;

    @Column(nullable = false, length = 1)
    private String status = "1";

    public FuncionarioProjeto() {}

    public FuncionarioProjeto(Integer id, Funcionario funcionario, Projeto projeto, Instant dataAtribuicao, Instant dataRemocao, String status) {
        this.id = id;
        this.funcionario = funcionario;
        this.projeto = projeto;
        this.dataAtribuicao = dataAtribuicao;
        this.dataRemocao = dataRemocao;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        dataAtribuicao = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Instant getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(Instant dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }

    public Instant getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(Instant dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
