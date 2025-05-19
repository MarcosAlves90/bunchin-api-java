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
    private UUID uuid;

    @ManyToOne(optional = true)
    @JoinColumn(name = "funcionario_fk", referencedColumnName = "cpf", nullable = true)
    private Funcionario funcionario;

    @Column(name = "nome_tipo", length = 20, nullable = false)
    private String nomeTipo;

    @Column(name = "data_hora", nullable = false)
    @Convert(converter = InstantStringAttributeConverter.class)
    private Instant dataHora;

    public Ponto() {}

    public Ponto(UUID uuid, Funcionario funcionario, String nomeTipo, Instant dataHora) {
        this.uuid = uuid;
        this.funcionario = funcionario;
        this.nomeTipo = nomeTipo;
        this.dataHora = dataHora;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
