package be4service2.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("contratanteProfissional")
public class ContratanteProfissional extends Pessoa{
/*
	Profissional profissional;
	Contratante contratante;*/

	private Double avaliacaoProfissional;

	private Double avaliacaoContratante;
	
	@OneToMany(mappedBy="profissional")
	private List<Servico> servicosPrestados;
	

	@OneToMany(mappedBy="contratante")
	private List<Servico> servicosContratados;

	
	
	
	
	
	
	
	public ContratanteProfissional() {
		
	}
	


	public Double getAvaliacaoProfissional() {
		return avaliacaoProfissional;
	}

	public void setAvaliacaoProfissional(Double avaliacaoProfissional) {
		this.avaliacaoProfissional = avaliacaoProfissional;
	}

	public Double getAvaliacaoContratante() {
		return avaliacaoContratante;
	}

	public void setAvaliacaoContratante(Double avaliacaoContratante) {
		this.avaliacaoContratante = avaliacaoContratante;
	}
	public List<Servico> getServicosPrestados() {
		return servicosPrestados;
	}
	public void setServicosPrestados(List<Servico> servicosPrestados) {
		this.servicosPrestados = servicosPrestados;
	}
	public List<Servico> getServicosContratados() {
		return servicosContratados;
	}
	public void setServicosContratados(List<Servico> servicosContratados) {
		this.servicosContratados = servicosContratados;
	}

	@Override
	public String toString() {
		return "ContratanteProfissional [avaliacaoProfissional=" + avaliacaoProfissional + ", avaliacaoContratante="
				+ avaliacaoContratante + ", servicosPrestados=" + servicosPrestados + ", servicosContratados="
				+ servicosContratados + "]";
	}



	
	
	
}

