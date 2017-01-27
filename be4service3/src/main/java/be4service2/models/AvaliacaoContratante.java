package be4service2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class AvaliacaoContratante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAvaliacaoContratante;
	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;
	@ManyToOne
	@JoinColumn(name="id_contratante")
	private Pessoa contratante;
	
	private Double avaliacaoCordialidade=1.0;
	private Double avaliacaoCompromisso=1.0;

	public AvaliacaoContratante(){
		
	}

	public AvaliacaoContratante(Servico servico, Pessoa contratante, Double avaliacaoCordialidade,
			Double avaliacaoCompromisso) {
		super();
		this.servico = servico;
		this.contratante = contratante;
		this.avaliacaoCordialidade = avaliacaoCordialidade;
		this.avaliacaoCompromisso = avaliacaoCompromisso;
	}

	
	public Integer getIdAvaliacaoContratante() {
		return idAvaliacaoContratante;
	}

	public void setIdAvaliacaoContratante(Integer idAvaliacaoContratante) {
		this.idAvaliacaoContratante = idAvaliacaoContratante;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAvaliacaoContratante == null) ? 0 : idAvaliacaoContratante.hashCode());
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
		AvaliacaoContratante other = (AvaliacaoContratante) obj;
		if (idAvaliacaoContratante == null) {
			if (other.idAvaliacaoContratante != null)
				return false;
		} else if (!idAvaliacaoContratante.equals(other.idAvaliacaoContratante))
			return false;
		return true;
	}
	
	
	
}
