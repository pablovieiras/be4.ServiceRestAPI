package be4service2.service;

import java.util.List;

import javax.servlet.ServletException;

import be4service2.models.AvaliacaoContratante;
import be4service2.models.AvaliacaoProfissional;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Profissional;
import be4service2.models.Proposta;
import be4service2.models.Servico;

public interface ServicoService {

	List<Servico> all();

	void save(Servico servico);

	Servico findById(Integer id);

	void remove(Servico servico);

	void update(Servico servico);
	
	Servico criarServico(Contratante contratante,Servico servico);
	
	void criarServico(ContratanteProfissional contratante,Servico servico);
	
	void selecionarProfissional(Profissional profissional,Servico servico) throws ServletException;
	

	
	void selecionarProfissional(ContratanteProfissional profissional,Servico servico);
	
	void selecionarProposta(Integer id,Servico servico) throws ServletException;
	
	void aceitarServico(Integer id,Integer resposta);
	
	void fazerProposta(Profissional p,Servico servico,Proposta proposta);
	
	void avaliaProfissional(Integer idServico,AvaliacaoProfissional avaliacao) throws ServletException;
	
	void avaliaContratante(Integer idServico, AvaliacaoContratante avaliacao) throws ServletException ;
	
	void finalizarServico (Servico servico);
	
	List<Servico> getListaServicosContratados(Contratante contratante);
	
	List<Servico> getAllServicosExecutados(Profissional profissional);
	
	List<Servico> listarAbertos ();
	
	List<Proposta> listaPropostasServico(Servico servico);
	
	List<Proposta> getAllPropostasFeitas(Profissional profissional);
	
}