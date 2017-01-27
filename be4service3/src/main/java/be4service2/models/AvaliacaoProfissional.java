package be4service2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class AvaliacaoProfissional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAvaliacaoProfissional;
	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;
	@ManyToOne
	@JoinColumn(name="id_profissional")
	private Pessoa profissional;
	
	private Double avaliacaoQualidade=1.0;
	private Double avaliacaoPreco=1.0;
	private Double avaliacaoPontualidade=1.0;
	
	public AvaliacaoProfissional(){
		
	}
	public AvaliacaoProfissional( Servico servico, Pessoa profissional,
			Double avaliacaoQualidade, Double avaliacaoPreco, Double avaliacaoPontualidade) {
		super();
		this.servico = servico;
		this.profissional = profissional;
		this.avaliacaoQualidade = avaliacaoQualidade;
		this.avaliacaoPreco = avaliacaoPreco;
		this.avaliacaoPontualidade = avaliacaoPontualidade;
	}
	
	public AvaliacaoProfissional(Double avaliacaoQualidade, Double avaliacaoPreco, Double avaliacaoPontualidade) {
		super();
		this.avaliacaoQualidade = avaliacaoQualidade;
		this.avaliacaoPreco = avaliacaoPreco;
		this.avaliacaoPontualidade = avaliacaoPontualidade;
	}
	public Integer getIdAvaliacaoProfissional() {
		return idAvaliacaoProfissional;
	}
	public void setIdAvaliacaoProfissional(Integer idAvaliacaoProfissional) {
		this.idAvaliacaoProfissional = idAvaliacaoProfissional;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Pessoa getProfissional() {
		return profissional;
	}
	public void setProfissional(Pessoa profissional) {
		this.profissional = profissional;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAvaliacaoProfissional == null) ? 0 : idAvaliacaoProfissional.hashCode());
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
		AvaliacaoProfissional other = (AvaliacaoProfissional) obj;
		if (idAvaliacaoProfissional == null) {
			if (other.idAvaliacaoProfissional != null)
				return false;
		} else if (!idAvaliacaoProfissional.equals(other.idAvaliacaoProfissional))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AvaliacaoProfissional [idAvaliacaoProfissional=" + idAvaliacaoProfissional + ", servico=" + servico
				+ ", profissional=" + profissional + ", avaliacaoQualidade=" + avaliacaoQualidade + ", avaliacaoPreco="
				+ avaliacaoPreco + ", avaliacaoPontualidade=" + avaliacaoPontualidade + "]";
	}
	
	
	
}
