package be4service2.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;
////////foi kkk

@Entity
@DiscriminatorValue(value="contratante")
public class Contratante extends Pessoa
{


	private Double avaliacaoCordialidade=0.0;
	private Double avaliacaoCompromisso=0.0;

	@OneToMany(mappedBy="contratante")
	private List<Servico> servicosContratados=new ArrayList<>();

	private Integer numeroAvaliacoesContratante=0;



	public Contratante(String nome, String email, String senha, String celular, String foto) {
		super(nome, email, senha, celular, foto);
	}

	public Contratante() {
		super();
	}

	public List<Servico> getServicosContratados() {
		return servicosContratados;
	}

	public void setServicosContratados(List<Servico> servicosContratados) {
		this.servicosContratados = servicosContratados;
	}


	public Integer getNumeroAvaliacoesContratante() {
		return numeroAvaliacoesContratante;
	}

	public void setNumeroAvaliacoesContratante(Integer numeroAvaliacoesContratante) {
		this.numeroAvaliacoesContratante = numeroAvaliacoesContratante;
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

	public void mediaAvaliacao(Double avCompromisso,Double avCordialidade){
		Double valorAntigoCompromisso=this.getAvaliacaoCordialidade()*numeroAvaliacoesContratante;
		this.numeroAvaliacoesContratante++;
		Double mediaCompromisso=(valorAntigoCompromisso+avCompromisso)/this.numeroAvaliacoesContratante;
		this.setAvaliacaoCompromisso(mediaCompromisso);
		
		Double valorAntigoCordialidade=this.getAvaliacaoCompromisso()*numeroAvaliacoesContratante;
		this.numeroAvaliacoesContratante++;
		Double mediaCordialidade=(valorAntigoCordialidade+avCordialidade)/this.numeroAvaliacoesContratante;
		this.setAvaliacaoCompromisso(mediaCordialidade);
	}
}
