package be4service2.service;

import java.util.List;

import javax.servlet.ServletException;

import be4service2.models.AvaliacaoContratante;
import be4service2.models.AvaliacaoProfissional;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Pessoa;
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
	
	Servico criarServico(ContratanteProfissional contratante,Servico servico);
	
	void selecionarProfissional(Pessoa profissional,Servico servico) throws ServletException;
		
	void selecionarProfissional(ContratanteProfissional profissional,Servico servico);
	
	void selecionarProposta(Integer id,Servico servico) throws ServletException;
	
	void aceitarServico(Servico servico,Integer resposta);
	
	void fazerProposta(Pessoa p,Servico servico,Proposta proposta) throws ServletException;
	
	void avaliaProfissional(Integer idServico,AvaliacaoProfissional avaliacao) throws ServletException;
	
	void avaliaContratante(Integer idServico, AvaliacaoContratante avaliacao) throws ServletException ;
	
	void finalizarServico (Servico servico) throws ServletException;
	
	List<Servico> getListaServicosContratados(Pessoa contratante);
	
	List<Servico> getAllServicosExecutados(Pessoa profissional) throws ServletException;
	
	List<Servico> listarAbertos ();
	
	List<Proposta> listaPropostasServico(Servico servico) throws ServletException;
	
	List<Proposta> getAllPropostasFeitas(Profissional profissional);
	
	List<Servico> avalicoesPendentesContratante(Contratante contratante);
	
	List<Servico> avalicoesPendentesProfissional(Profissional profissional) throws ServletException;
	
	List<Servico> avalicoesPendentesContratanteProfissional(Pessoa profissional) throws ServletException;
	
	List <Servico> buscaServicoPorTitulo (String titulo);

	
}