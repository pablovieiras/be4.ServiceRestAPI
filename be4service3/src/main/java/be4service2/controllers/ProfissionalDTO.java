package be4service2.controllers;

public class ProfissionalDTO {

	private String nome;
	private String email;
	private String resumoProfissional;
	private String profissao;
	
		
	public ProfissionalDTO() {
		super();
	}

	public ProfissionalDTO(String nome, String email, String resumoProfissional, String profissao) {
		super();
		this.nome = nome;
		this.email = email;
		this.resumoProfissional = resumoProfissional;
		this.profissao = profissao;
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
}
