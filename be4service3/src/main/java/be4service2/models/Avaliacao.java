package be4service2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAvaliacao;
	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;
	@ManyToOne
	@JoinColumn(name="id_contratante")
	private Pessoa contratante;
	@ManyToOne
	@JoinColumn(name="id_profissional")
	private Pessoa profissional;
	
	private Double avaliacaoProfissional=0.0;
	private Double avaliacaoContratante=0.0;
	
	public Avaliacao(){
		
	}
	public Avaliacao(Integer idAvaliacao, Servico servico, Pessoa contratante, Pessoa profissional,
			Double avaliacaoProfissional, Double avaliacaoContratante) {
		super();
		this.idAvaliacao = idAvaliacao;
		this.servico = servico;
		this.contratante = contratante;
		this.profissional = profissional;
		this.avaliacaoProfissional = avaliacaoProfissional;
		this.avaliacaoContratante = avaliacaoContratante;
	}
	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Pessoa getContratante() {
		return contratante;
	}
	public void setContratante(Pessoa contratante) {
		this.contratante = contratante;
	}
	public Pessoa getProfissional() {
		return profissional;
	}
	public void setProfissional(Pessoa profissional) {
		this.profissional = profissional;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAvaliacao == null) ? 0 : idAvaliacao.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (idAvaliacao == null) {
			if (other.idAvaliacao != null)
				return false;
		} else if (!idAvaliacao.equals(other.idAvaliacao))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Avaliacao [idAvaliacao=" + idAvaliacao + ", servico=" + servico + ", contratante=" + contratante
				+ ", profissional=" + profissional + ", avaliacaoProfissional=" + avaliacaoProfissional
				+ ", avaliacaoContratante=" + avaliacaoContratante + "]";
	}
	

	
	
}
