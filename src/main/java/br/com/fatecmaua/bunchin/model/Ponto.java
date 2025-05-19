package br.com.fatecmaua.bunchin.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_ponto")
public class Ponto {
    @Id
    @Column(name = "id_ponto", length = 36)
    private String idPonto;

    @ManyToOne
    @JoinColumn(name = "funcionario_fk", referencedColumnName = "n_registro", nullable = false)
    private Funcionario funcionario;

    @Column(name = "nome_tipo", length = 20, nullable = false)
    private String nomeTipo;

    @Column(name = "data_hora", nullable = false)
    private Instant dataHora;

    public Ponto() {}

    public Ponto(String idPonto, Funcionario funcionario, String nomeTipo, Instant dataHora) {
        this.idPonto = idPonto;
        this.funcionario = funcionario;
        this.nomeTipo = nomeTipo;
        this.dataHora = dataHora;
    }

    public String getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(String idPonto) {
        this.idPonto = idPonto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public void setDataHora(Instant dataHora) {
        this.dataHora = dataHora;
    }
}
