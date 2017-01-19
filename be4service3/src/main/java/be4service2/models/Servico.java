package be4service2.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServico;

	@ManyToOne
	@JoinColumn(name = "id_contratante")
	private Pessoa contratante;
	@ManyToOne
	@JoinColumn(name = "id_profissional")
	private Pessoa profissional;
	private boolean avaliacaoContratante=false;
	private boolean avaliacaoProfissional=false;

	private String titulo;
	private String status;
	private BigDecimal valor;
	private int prazoEstimado;
	private String competencias;
	private String descricao;

	public Servico(Pessoa contratante, String titulo, String status, BigDecimal valor, int prazoEstimado,
			String competencias, String descricao) {
		super();
		this.contratante = contratante;
		this.titulo = titulo;
		this.status = status;
		this.valor = valor;
		this.prazoEstimado = prazoEstimado;
		this.competencias = competencias;
		this.descricao = descricao;
	}

	public Servico() {
		super();
	}

	public Servico(Servico s, Contratante contratante) {
		super();
		this.contratante = contratante;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
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

	public String getCompetencias() {
		return competencias;
	}

	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	@Override
	public String toString() {
		return "Servico [idServico=" + idServico + ", contratante=" + contratante + ", profissional=" + profissional
				+ "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

/*	public void limpa(List<Proposta> listaProposta) {
		for (int i = listaProposta.size() - 1; i >= 0; i--) {
			listaProposta.remove(i);
		}
		System.out.println(listaProposta.size());
	}
*/

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public boolean isAvaliacaoContratante() {
		return avaliacaoContratante;
	}


	public void setAvaliacaoContratante(boolean avaliacaoContratante) {
		this.avaliacaoContratante = avaliacaoContratante;
	}


	public boolean isAvaliacaoProfissional() {
		return avaliacaoProfissional;
	}


	public void setAvaliacaoProfissional(boolean avaliacaoProfissional) {
		this.avaliacaoProfissional = avaliacaoProfissional;
	}
	
	
}
