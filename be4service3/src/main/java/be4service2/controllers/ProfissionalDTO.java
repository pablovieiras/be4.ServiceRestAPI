package be4service2.controllers;

public class ProfissionalDTO {

	private String nome;
	private String email;
	private String resumoProfissional;
	private String profissao;
	private Integer id;
	private String telefone;
	private String celular;

	public ProfissionalDTO() {
		super();
	}

	public ProfissionalDTO(String nome, String email, String resumoProfissional, String profissao, Integer id,
			String telefone, String celular) {
		super();
		this.nome = nome;
		this.email = email;
		this.resumoProfissional = resumoProfissional;
		this.profissao = profissao;
		this.id = id;
		this.telefone = telefone;
		this.celular = celular;
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

	public String getResumoProfissional() {
		return resumoProfissional;
	}

	public void setResumoProfissional(String resumoProfissional) {
		this.resumoProfissional = resumoProfissional;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
