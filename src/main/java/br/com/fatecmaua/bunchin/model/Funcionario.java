package br.com.fatecmaua.bunchin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_registro;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String funcao;
    private String cargo;
    private String departamento;
    private String status;

    public Funcionario() {
    }

    public Funcionario(Integer n_registro, String nome, String email, String senha, String cpf, String funcao, String cargo, String departamento, String status) {
        this.n_registro = n_registro;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.funcao = funcao;
        this.cargo = cargo;
        this.departamento = departamento;
        this.status = status;
    }

    public Integer getN_registro() {
        return n_registro;
    }

    public void setN_registro(Integer n_registro) {
        this.n_registro = n_registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
