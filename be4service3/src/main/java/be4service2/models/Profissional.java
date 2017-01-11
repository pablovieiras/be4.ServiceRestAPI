package be4service2.models;

import java.util.ArrayList;
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
	private Double avaliacaoProfissional=0.0;
	private Double avaliacaoQualidade=0.0;
	private Double avaliacaoPreco=0.0;
	private Double avaliacaoPontualidade=0.0;

	@OneToMany(mappedBy="profissional")
	private List<Proposta> listaProposta=new ArrayList<>();

	@OneToMany(mappedBy="profissional")
	private List<Servico> servicosPrestados=new ArrayList<>();
	
	private Integer numeroAvaliacoesProfissional=0;

	
	
	public Profissional() {
	
	}
	


	
	
	//get set
	public Double getAvaliacaoProfissional() {
		return avaliacaoProfissional;
	}

	public void setAvaliacaoProfissional(Double avaliacaoProfissional) {
		this.avaliacaoProfissional = avaliacaoProfissional;
	}
	
	public List<Servico> getServicosPrestados() {
		return servicosPrestados;
	}
	public void setServicosPrestados(List<Servico> servicosPrestados) {
		this.servicosPrestados = servicosPrestados;
	}
	@JsonIgnore
	public List<Proposta> getListaProposta() {
		return listaProposta;
	}
	
	public void setListaProposta(List<Proposta> listaProposta) {
		this.listaProposta = listaProposta;
	}
	
	
	public void mediaAvaliacao(Double nota){
		Double valorAntigo=this.getAvaliacaoProfissional()*numeroAvaliacoesProfissional;
		this.numeroAvaliacoesProfissional++;
		Double media=(valorAntigo+nota)/this.numeroAvaliacoesProfissional;
		this.setAvaliacaoProfissional(media);
	}


  
   

}
