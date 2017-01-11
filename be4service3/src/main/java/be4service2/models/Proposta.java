package be4service2.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Proposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProposta;
	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;
	@ManyToOne
	@JoinColumn(name="id_profissional")
	private Profissional profissional;
	private String titulo;
	private BigDecimal valorProposta;
	private int prazoEstimado;
	private String descricao;
	
	public Proposta(){
		
	}
	
	public Proposta(Servico servico, BigDecimal valorProposta) {
		super();
		this.servico = servico;
		this.valorProposta = valorProposta;
	}

	public Proposta(Servico servico, Profissional profissional, BigDecimal valorProposta) {
		super();
		this.servico = servico;
		this.profissional = profissional;
		this.valorProposta = valorProposta;
	}
	
	public Proposta(Integer idProposta, Servico servico, Profissional profissional, BigDecimal valorProposta) {
		super();
		this.idProposta = idProposta;
		this.servico = servico;
		this.profissional = profissional;
		this.valorProposta = valorProposta;
	}

	public Proposta(BigDecimal valorProposta) {
		super();
		this.valorProposta = valorProposta;
	}
	@JsonIgnore
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	@JsonIgnore
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	public BigDecimal getValorProposta() {
		return valorProposta;
	}
	public void setValorProposta(BigDecimal valorProposta) {
		this.valorProposta = valorProposta;
	}
	// ignore
	public Integer getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(Integer idProposta) {
		this.idProposta = idProposta;
	}

	@Override
	public String toString() {
		return "Proposta [idProposta=" + idProposta + ", servico=" + servico + ", profissional=" + profissional
				+ ", valorProposta=" + valorProposta + "]";
	}
	
	
	
}
