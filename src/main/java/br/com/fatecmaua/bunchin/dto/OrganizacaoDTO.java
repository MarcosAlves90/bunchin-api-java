package br.com.fatecmaua.bunchin.dto;

import java.time.Instant;

public class OrganizacaoDTO {
    private Integer idOrganizacao;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;
    private String status;
    private Instant dataCriacao;

    public OrganizacaoDTO() {}

    public OrganizacaoDTO(Integer idOrganizacao, String nome, String cnpj, String endereco, String telefone, String email, String status, Instant dataCriacao) {
        this.idOrganizacao = idOrganizacao;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
        this.dataCriacao = dataCriacao;
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
