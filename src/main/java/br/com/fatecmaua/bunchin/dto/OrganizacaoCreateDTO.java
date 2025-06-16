package br.com.fatecmaua.bunchin.dto;

public class OrganizacaoCreateDTO {
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;
    
    private String adminNome;
    private String adminEmail;
    private String adminSenha;
    private String adminCpf;
    private String adminFuncao;
    private String adminCargo;
    private String adminDepartamento;

    public OrganizacaoCreateDTO() {}

    // Getters e Setters da Organização
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

    // Getters e Setters do Administrador
    public String getAdminNome() {
        return adminNome;
    }

    public void setAdminNome(String adminNome) {
        this.adminNome = adminNome;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminSenha() {
        return adminSenha;
    }

    public void setAdminSenha(String adminSenha) {
        this.adminSenha = adminSenha;
    }

    public String getAdminCpf() {
        return adminCpf;
    }

    public void setAdminCpf(String adminCpf) {
        this.adminCpf = adminCpf;
    }

    public String getAdminFuncao() {
        return adminFuncao;
    }

    public void setAdminFuncao(String adminFuncao) {
        this.adminFuncao = adminFuncao;
    }

    public String getAdminCargo() {
        return adminCargo;
    }

    public void setAdminCargo(String adminCargo) {
        this.adminCargo = adminCargo;
    }

    public String getAdminDepartamento() {
        return adminDepartamento;
    }

    public void setAdminDepartamento(String adminDepartamento) {
        this.adminDepartamento = adminDepartamento;
    }
}
