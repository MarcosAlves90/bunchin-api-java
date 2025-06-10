package br.com.fatecmaua.bunchin.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

import br.com.fatecmaua.bunchin.util.InstantStringAttributeConverter;

@Entity
@Table(name = "tb_ponto")
public class Ponto {
    @Id
    @Column(name = "id_ponto", columnDefinition = "uuid")
    private UUID id_ponto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", referencedColumnName = "n_registro", nullable = false)
    private Funcionario funcionario;

    @Column(name = "nome_tipo", length = 20, nullable = false)
    private String nome_tipo;

    @Column(name = "data_hora", nullable = false)
    @Convert(converter = InstantStringAttributeConverter.class)
    private Instant data_hora;

    @ManyToOne(optional = true)
    @JoinColumn(name = "projeto_id", referencedColumnName = "id_projeto", nullable = true)
    private Projeto projeto;

    public Ponto() {}

    public Ponto(UUID id_ponto, Funcionario funcionario, String nome_tipo, Instant data_hora, Projeto projeto) {
        this.id_ponto = id_ponto;
        this.funcionario = funcionario;
        this.nome_tipo = nome_tipo;
        this.data_hora = data_hora;
        this.projeto = projeto;
    }

    public UUID getId_ponto() {
        return id_ponto;
    }

    public void setId_ponto(UUID id_ponto) {
        this.id_ponto = id_ponto;
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

    public String getNome_tipo() {
        return nome_tipo;
    }

    public void setNome_tipo(String nome_tipo) {
        this.nome_tipo = nome_tipo;
    }

    public Instant getData_hora() {
        return data_hora;
    }

    public void setData_hora(Instant data_hora) {
        this.data_hora = data_hora;
    }
}