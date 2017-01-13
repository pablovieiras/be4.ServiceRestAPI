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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value="contratante")
public class Contratante extends Pessoa
{


	private Double avaliacaoCordialidade=0.0;
	private Double avaliacaoCompromisso=0.0;


	private Integer numeroAvaliacoesContratante=0;


	public Contratante() {
		super();
	}
	public Contratante(String nome, String email, String senha, String celular, String foto) {
		super(nome, email, senha, celular, foto);
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
	//media geral das avaliacoes contratante
	public void mediaAvaliacao(Double avCompromisso,Double avCordialidade){
		//faz calculo para achar o valor completo de todas avaliacoes
		Double valorAntigoCompromisso=this.getAvaliacaoCordialidade()*numeroAvaliacoesContratante;
		Double valorAntigoCordialidade=this.getAvaliacaoCompromisso()*numeroAvaliacoesContratante;
		//aumenta o numero de pessoas que avaliaram o contratante
		this.numeroAvaliacoesContratante++;
		//faz a media do valor total e seta o valor na avaliacao do contratante
		Double mediaCompromisso=(valorAntigoCompromisso+avCompromisso)/this.numeroAvaliacoesContratante;
		this.setAvaliacaoCompromisso(mediaCompromisso);
		Double mediaCordialidade=(valorAntigoCordialidade+avCordialidade)/this.numeroAvaliacoesContratante;
		this.setAvaliacaoCompromisso(mediaCordialidade);
	}
}
