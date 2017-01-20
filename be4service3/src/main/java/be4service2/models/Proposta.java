package be4service2.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Proposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProposta;
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;
	@ManyToOne
	@JoinColumn(name = "id_profissional")
	private Profissional profissional;
	private String titulo;
	private BigDecimal valorProposta;
	private int prazoEstimado;
	private String descricao;
	private String status = "Em Avaliação";

	public Proposta() {

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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPrazoEstimado() {
		return prazoEstimado;
	}

	public void setPrazoEstimado(int prazoEstimado) {
		this.prazoEstimado = prazoEstimado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Proposta [idProposta=" + idProposta + ", servico=" + servico + ", profissional=" + profissional
				+ ", titulo=" + titulo + ", valorProposta=" + valorProposta + ", prazoEstimado=" + prazoEstimado
				+ ", descricao=" + descricao + ", status=" + status + "]";
	}

}
