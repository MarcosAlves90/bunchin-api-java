package br.com.fatecmaua.bunchin.dto;

public class FuncionarioDTO {
	private Integer n_registro;
	private String nome;
	private String email;
	private String funcao;
	private String cargo;
	private String departamento;
	private String cpf;
	private String status;
	private Integer organizacao_id;
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getOrganizacao_id() {
		return organizacao_id;
	}
	public void setOrganizacao_id(Integer organizacao_id) {
		this.organizacao_id = organizacao_id;
	}
}
