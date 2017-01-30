package be4service2.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("contratanteProfissional")
public class ContratanteProfissional extends Pessoa{
	
	private Double avaliacaoCordialidade=1.0;
	private Double avaliacaoCompromisso=1.0;
	private Integer numeroAvaliacoesContratante=0;
	
	private String profissao;
	@Column(columnDefinition = "text")
	private String resumoProfissional;
	private String competencias;
	private Double avaliacaoQualidade=1.0;
	private Double avaliacaoPreco=1.0;
	private Double avaliacaoPontualidade=1.0;
	private Integer numeroAvaliacoesProfissional=0;
	
	public ContratanteProfissional(){
		
	}
	
	public ContratanteProfissional(Integer id, String nome, String cpf, String dataNascimento, String email,
			String senha, String telefone, String celular, String cep, String lougradouro, String numero, String cidade,
			String bairro, String complemento, String foto, String chave) {
		super(id, nome, cpf, dataNascimento, email, senha, telefone, celular, cep, lougradouro, numero, cidade, bairro,
				complemento, foto, chave);
	}
	public ContratanteProfissional(Integer id, String nome, String cpf, String dataNascimento, String email,
			String senha, String telefone, String celular, String cep, String lougradouro, String numero, String cidade,
			String bairro, String complemento, String foto, String chave, Double avaliacaoCordialidade,
			Double avaliacaoCompromisso, Integer numeroAvaliacoesContratante, String profissao,
			String resumoProfissional, String competencias, Double avaliacaoQualidade, Double avaliacaoPreco,
			Double avaliacaoPontualidade, Integer numeroAvaliacoesProfissional) {
		super(id, nome, cpf, dataNascimento, email, senha, telefone, celular, cep, lougradouro, numero, cidade, bairro,
				complemento, foto, chave);
		
		
		this.avaliacaoCordialidade = avaliacaoCordialidade;
		this.avaliacaoCompromisso = avaliacaoCompromisso;
		this.numeroAvaliacoesContratante = numeroAvaliacoesContratante;
		this.profissao = profissao;
		this.resumoProfissional = resumoProfissional;
		this.competencias = competencias;
		this.avaliacaoQualidade = avaliacaoQualidade;
		this.avaliacaoPreco = avaliacaoPreco;
		this.avaliacaoPontualidade = avaliacaoPontualidade;
		this.numeroAvaliacoesProfissional = numeroAvaliacoesProfissional;
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
	public Integer getNumeroAvaliacoesContratante() {
		return numeroAvaliacoesContratante;
	}
	public void setNumeroAvaliacoesContratante(Integer numeroAvaliacoesContratante) {
		this.numeroAvaliacoesContratante = numeroAvaliacoesContratante;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getResumoProfissional() {
		return resumoProfissional;
	}
	public void setResumoProfissional(String resumoProfissional) {
		this.resumoProfissional = resumoProfissional;
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
	public Integer getNumeroAvaliacoesProfissional() {
		return numeroAvaliacoesProfissional;
	}
	public void setNumeroAvaliacoesProfissional(Integer numeroAvaliacoesProfissional) {
		this.numeroAvaliacoesProfissional = numeroAvaliacoesProfissional;
	}
	@Override
	public String toString() {
		return "ContratanteProfissional [avaliacaoCordialidade=" + avaliacaoCordialidade + ", avaliacaoCompromisso="
				+ avaliacaoCompromisso + ", numeroAvaliacoesContratante=" + numeroAvaliacoesContratante + ", profissao="
				+ profissao + ", resumoProfissional=" + resumoProfissional + ", competencias=" + competencias
				+ ", avaliacaoQualidade=" + avaliacaoQualidade + ", avaliacaoPreco=" + avaliacaoPreco
				+ ", avaliacaoPontualidade=" + avaliacaoPontualidade + ", numeroAvaliacoesProfissional="
				+ numeroAvaliacoesProfissional + "]";
	}
	//media geral das avaliacoes contratante
	public void mediaAvaliacaoContratante(Double avCompromisso,Double avCordialidade){
		//faz calculo para achar o valor completo de todas avaliacoes
		Double valorAntigoCompromisso=this.getAvaliacaoCordialidade()*numeroAvaliacoesContratante;
		Double valorAntigoCordialidade=this.getAvaliacaoCompromisso()*numeroAvaliacoesContratante;
		//aumenta o numero de pessoas que avaliaram o contratante
		this.numeroAvaliacoesContratante++;
		//faz a media do valor total e seta o valor na avaliacao do contratante
		Double mediaCompromisso=(valorAntigoCompromisso+avCompromisso)/this.numeroAvaliacoesContratante;
		this.setAvaliacaoCompromisso(mediaCompromisso);
		Double mediaCordialidade=(valorAntigoCordialidade+avCordialidade)/this.numeroAvaliacoesContratante;
		this.setAvaliacaoCordialidade(mediaCordialidade);
	}
	
	//media geral das avaliacoes profissional
	public void mediaAvaliacaoProfissional(Double avQualidade,Double avPreco,Double avPontualidade){
		//faz calculo para achar o valor completo de todas avaliacoes
		Double valorAntigoQualidade=this.getAvaliacaoQualidade()*this.getNumeroAvaliacoesProfissional();
		Double valorAntigoPreco=this.getAvaliacaoPreco()*this.getNumeroAvaliacoesProfissional();
		Double valorAntigoPontualidade=this.avaliacaoPontualidade*this.getNumeroAvaliacoesProfissional();
	
		//aumenta o numero de pessoas que avaliaram o contratante
		this.numeroAvaliacoesProfissional++;

		//faz a media do valor total e seta o valor na avaliacao do contratante
		Double mediaQualidade=(valorAntigoQualidade+avQualidade)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoQualidade(mediaQualidade);
		Double mediaPreco=(valorAntigoPreco+avPreco)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoPreco(mediaPreco);
		Double mediaPontualidade=(valorAntigoPontualidade+avPontualidade)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoPontualidade(mediaPontualidade);
	
		
	}
	
}

