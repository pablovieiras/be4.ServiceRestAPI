package be4service2.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value="profissional")
public class Profissional extends Pessoa
{
	private String profissao;
	@Column(columnDefinition = "text")
	private String resumoProfissional;
	private String competencias;
	private Double avaliacaoQualidade=1.0;
	private Double avaliacaoPreco=1.0;
	private Double avaliacaoPontualidade=1.0;
	private Integer numeroAvaliacoesProfissional=0;

	public Profissional() {
	
	}
	
	public Profissional(Integer id, String nome, String cpf, String dataNascimento, String email, String senha,
			String telefone, String celular, String cep, String lougradouro, String numero, String cidade,
			String bairro, String complemento, String foto, String chave) {
		super(id, nome, cpf, dataNascimento, email, senha, telefone, celular, cep, lougradouro, numero, cidade, bairro,
				complemento, foto, chave);
	}

	//get set

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
	
	//media geral das avaliacoes profissional
	public void mediaAvaliacao(Double avQualidade,Double avPreco,Double avPontualidade){
		//faz calculo para achar o valor completo de todas avaliacoes
		Double valorAntigoQualidade=this.getAvaliacaoQualidade()*this.getNumeroAvaliacoesProfissional();
		Double valorAntigoPreco=this.getAvaliacaoPreco()*this.getNumeroAvaliacoesProfissional();
		System.out.println("avProntualidade"+avPontualidade );
		System.out.println("conta: "+this.avaliacaoPontualidade+" "+this.getNumeroAvaliacoesProfissional());
		Double valorAntigoPontualidade=this.avaliacaoPontualidade*this.getNumeroAvaliacoesProfissional();
		System.out.println("valorAntigoPontualidade"+valorAntigoPontualidade );
	
		//aumenta o numero de pessoas que avaliaram o contratante
		this.numeroAvaliacoesProfissional++;
		System.out.println("numeroAvaliacoesProfissional"+numeroAvaliacoesProfissional );
		//faz a media do valor total e seta o valor na avaliacao do contratante
		Double mediaQualidade=(valorAntigoQualidade+avQualidade)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoQualidade(mediaQualidade);
		Double mediaPreco=(valorAntigoPreco+avPreco)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoPreco(mediaPreco);
		Double mediaPontualidade=(valorAntigoPontualidade+avPontualidade)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoPontualidade(mediaPontualidade);
	
		
	}

  
   

}
