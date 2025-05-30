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

    @ManyToOne(optional = true)
    @JoinColumn(name = "funcionario_fk", referencedColumnName = "cpf", nullable = true)
    private Funcionario funcionario_fk;

    @Column(name = "nome_tipo", length = 20, nullable = false)
    private String nome_tipo;

    @Column(name = "data_hora", nullable = false)
    @Convert(converter = InstantStringAttributeConverter.class)
    private Instant data_hora;

    public Ponto() {}

    public Ponto(UUID id_ponto, Funcionario funcionario_fk, String nome_tipo, Instant data_hora) {
        this.id_ponto = id_ponto;
        this.funcionario_fk = funcionario_fk;
        this.nome_tipo = nome_tipo;
        this.data_hora = data_hora;
    }

    public UUID getId_ponto() {
        return id_ponto;
    }

    public void setId_ponto(UUID id_ponto) {
        this.id_ponto = id_ponto;
    }

    public Funcionario getFuncionario_fk() {
        return funcionario_fk;
    }

    public void setFuncionario_fk(Funcionario funcionario_fk) {
        this.funcionario_fk = funcionario_fk;
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