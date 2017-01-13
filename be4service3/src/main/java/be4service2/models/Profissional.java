package be4service2.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@DiscriminatorValue(value="profissional")
public class Profissional extends Pessoa
{
	private String profissao;
	private String resumoProfissional;
	private String competencias;
	private Double avaliacaoQualidade=0.0;
	private Double avaliacaoPreco=0.0;
	private Double avaliacaoPontualidade=0.0;

	@OneToMany(mappedBy="profissional")
	private List<Proposta> listaProposta=new ArrayList<>();

	private Integer numeroAvaliacoesProfissional=0;

	public Profissional() {
	
	}

	public Profissional(String nome, String cpf, Date dataNascimento, String email, String senha, String celular,
			String cep, String lougradouro, String numero, String cidade, String bairro, String complemento,
			String foto) {
		super(nome, cpf, dataNascimento, email, senha, celular, cep, lougradouro, numero, cidade, bairro, complemento, foto);
	}
	
	//get set

	@JsonIgnore
	public List<Proposta> getListaProposta() {
		return listaProposta;
	}
	
	public void setListaProposta(List<Proposta> listaProposta) {
		this.listaProposta = listaProposta;
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
	
	//media geral das avaliacoes profissional
	public void mediaAvaliacao(Double avQualidade,Double avPreco,Double avPontualidade){
		//faz calculo para achar o valor completo de todas avaliacoes
		Double valorAntigoQualidade=this.getAvaliacaoQualidade()*this.getNumeroAvaliacoesProfissional();
		Double valorAntigoPreco=this.getAvaliacaoPreco()*this.getNumeroAvaliacoesProfissional();
		Double valorAntigoPontualidade=this.getAvaliacaoPontualidade()*this.getNumeroAvaliacoesProfissional();
		//aumenta o numero de pessoas que avaliaram o contratante
		this.numeroAvaliacoesProfissional++;
		//faz a media do valor total e seta o valor na avaliacao do contratante
		Double mediaQualidade=(valorAntigoQualidade+avQualidade)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoQualidade(mediaQualidade);
		Double mediaPreco=(valorAntigoPreco+avPreco)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoPreco(mediaPreco);
		Double mediaPontualidade=(valorAntigoPontualidade+avPontualidade)/this.getNumeroAvaliacoesProfissional();
		this.setAvaliacaoQualidade(mediaPontualidade);
		
	}

  
   

}
