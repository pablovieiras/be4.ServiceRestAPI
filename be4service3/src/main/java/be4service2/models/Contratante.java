package be4service2.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@DiscriminatorValue(value="contratante")
public class Contratante extends Pessoa
{

	private Double avaliacaoContratante=0.0;
	

	@OneToMany(mappedBy="contratante")
	private List<Servico> servicosContratados=new ArrayList<>();

	private Integer numeroAvaliacoesContratante=0;

	public Contratante(String nome,Double avaliacaoContratante) {
		super(nome);
		this.avaliacaoContratante = avaliacaoContratante;
	//	servicosContratados = new ArrayList();
	}

	public Contratante(Integer id, String nome, Double avaliacaoContratante) {
	super(id, nome);
	this.avaliacaoContratante = avaliacaoContratante;
}

	public Contratante() {
		super();
	}



	public Double getAvaliacaoContratante() {
		return avaliacaoContratante;
	}

	

	public void setAvaliacaoContratante(Double avaliacaoContratante) {
		this.avaliacaoContratante = avaliacaoContratante;
	}

	public List<Servico> getServicosContratados() {
		return servicosContratados;
	}

	public void setServicosContratados(List<Servico> servicosContratados) {
		this.servicosContratados = servicosContratados;
	}

	@Override
	public String toString() {
		return "Contratante [avaliacaoContratante=" + avaliacaoContratante + "]";
	}

	public Integer getNumeroAvaliacoesContratante() {
		return numeroAvaliacoesContratante;
	}

	public void setNumeroAvaliacoesContratante(Integer numeroAvaliacoesContratante) {
		this.numeroAvaliacoesContratante = numeroAvaliacoesContratante;
	}
	
	public void mediaAvaliacao(Double nota){
		Double valorAntigo=this.getAvaliacaoContratante()*numeroAvaliacoesContratante;
		this.numeroAvaliacoesContratante++;
		Double media=(valorAntigo+nota)/this.numeroAvaliacoesContratante;
		this.setAvaliacaoContratante(media);
	}
}
