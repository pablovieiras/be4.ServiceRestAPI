package be4service2.controllers;

public class ProfissionalDTO {

	private Integer idProfissional;
	private String nome;
	private String email;
	private String resumoProfissional;
	private String profissao;
	private String telefone;
	private String celular;
	private String competencias;
	private Double avaliacaoQualidade;
	private Double avaliacaoPreco;
	private Double avaliacaoPontualidade;
	private Double avaliacaoCordialidade;
	private Double avaliacaoCompromisso;
	private String cep;
	private String logradouro;
	private byte[] foto;

	public ProfissionalDTO() {
		super();
	}
	
	
	public ProfissionalDTO(Integer idProfissional, String nome, String email, String resumoProfissional,
			String profissao, String telefone, String celular, String competencias, Double avaliacaoQualidade,
			Double avaliacaoPreco, Double avaliacaoPontualidade, String cep, String logradouro, byte[] foto) {
		super();
		this.idProfissional = idProfissional;
		this.nome = nome;
		this.email = email;
		this.resumoProfissional = resumoProfissional;
		this.profissao = profissao;
		this.telefone = telefone;
		this.celular = celular;
		this.competencias = competencias;
		this.avaliacaoQualidade = avaliacaoQualidade;
		this.avaliacaoPreco = avaliacaoPreco;
		this.avaliacaoPontualidade = avaliacaoPontualidade;
		this.cep = cep;
		this.logradouro = logradouro;
		this.foto = foto;
	}




	public ProfissionalDTO(Integer idProfissional, String nome, String email, String resumoProfissional,
			String profissao, String telefone, String celular, String competencias, Double avaliacaoQualidade,
			Double avaliacaoPreco, Double avaliacaoPontualidade, Double avaliacaoCordialidade,
			Double avaliacaoCompromisso, String cep, String logradouro, byte[] foto) {
		super();
		this.idProfissional = idProfissional;
		this.nome = nome;
		this.email = email;
		this.resumoProfissional = resumoProfissional;
		this.profissao = profissao;
		this.telefone = telefone;
		this.celular = celular;
		this.competencias = competencias;
		this.avaliacaoQualidade = avaliacaoQualidade;
		this.avaliacaoPreco = avaliacaoPreco;
		this.avaliacaoPontualidade = avaliacaoPontualidade;
		this.avaliacaoCordialidade = avaliacaoCordialidade;
		this.avaliacaoCompromisso = avaliacaoCompromisso;
		this.cep = cep;
		this.logradouro = logradouro;
		this.foto = foto;
	}

	public String getCompetencias() {
		return competencias;
	}

	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	public Double getAvaliacaoQualidade() {
		return avaliacaoQualidade;
	}

	public void setAvaliacaoQualidade(Double avaliacaoQualidade) {
		this.avaliacaoQualidade = avaliacaoQualidade;
	}

	public Double getAvaliacaoPreco() {
		return avaliacaoPreco;
	}

	public void setAvaliacaoPreco(Double avaliacaoPreco) {
		this.avaliacaoPreco = avaliacaoPreco;
	}

	public Double getAvaliacaoPontualidade() {
		return avaliacaoPontualidade;
	}

	public void setAvaliacaoPontualidade(Double avaliacaoPontualidade) {
		this.avaliacaoPontualidade = avaliacaoPontualidade;
	}

	public Double getAvaliacaoCordialidade() {
		return avaliacaoCordialidade;
	}

	public void setAvaliacaoCordialidade(Double avaliacaoCordialidade) {
		this.avaliacaoCordialidade = avaliacaoCordialidade;
	}

	public Double getAvaliacaoCompromisso() {
		return avaliacaoCompromisso;
	}

	public void setAvaliacaoCompromisso(Double avaliacaoCompromisso) {
		this.avaliacaoCompromisso = avaliacaoCompromisso;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return logradouro;
	}

	public void setRua(String logradouro) {
		this.logradouro = logradouro;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Integer getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(Integer idProfissional) {
		this.idProfissional = idProfissional;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}



	
}
