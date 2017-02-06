package be4service2.controllers;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.daos.PessoaDaoImpl;
import be4service2.models.AvaliacaoContratante;
import be4service2.models.AvaliacaoProfissional;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Proposta;
import be4service2.models.Servico;
import be4service2.service.ContratanteProfissionalService;
import be4service2.service.ServicoService;

@RestController
// @CrossOrigin(origins = "http://172.23.16.18")
@RequestMapping(value = "/contratanteProfissional")

public class ContratanteProfissionalController {

	@Autowired
	private ContratanteProfissionalService contratanteProfissionalService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private PessoaDaoImpl pessoaDao;

	// traz uma lista com todos contratanteProfissionais
	@RequestMapping(method = RequestMethod.GET)
	public List<ContratanteProfissional> load() {
		return contratanteProfissionalService.all();
	}

	// traz um contratante profissional por um determinado id
	@RequestMapping(value = "/{id_contratanteProfissional}", method = RequestMethod.GET)
	public ContratanteProfissional findById(@PathVariable("id_contratanteProfissional") Integer id) {
		return contratanteProfissionalService.findById(id);
	}

	// salva um contratanteProfissional
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(@RequestBody ContratanteProfissional contratanteProfissional) {
		contratanteProfissionalService.save(contratanteProfissional);
	}

	// update no contratanteProfissional
	@RequestMapping(value = "/{id_contratanteProfissional}", method = RequestMethod.PUT)
	public void update(@RequestBody ContratanteProfissional contratanteProfissional) {
		contratanteProfissionalService.update(contratanteProfissional);
	}

	// traz toda a lista de Propostas feitas para um determinado serviço
	@RequestMapping(value = "/{id_servico}/propostasServico", method = RequestMethod.GET)
	public List<Proposta> getListaPropostasServico(@PathVariable("id_servico") Integer id) throws ServletException {
		return servicoService.listaPropostasServico(servicoService.findById(id));
	}

	// traz toda a lista de serviços que o contratante contratou
	@RequestMapping(value = "/{id_contratante}/servicosContratados", method = RequestMethod.GET)
	public List<Servico> getAllServicosContratados(@PathVariable("id_contratante") Integer id) {
		return servicoService.getListaServicosContratados(pessoaDao.findById(id));
	}

	// o contratante cria um serviço e esse serviço fica salvo na tabela serviço
	@RequestMapping(value = "/{id_contratante}/criarServico", method = RequestMethod.POST)
	public Servico criarServico(@PathVariable("id_contratante") Integer id, @RequestBody Servico servico) {
		return servicoService.criarServico(contratanteProfissionalService.findById(id), servico);
	}

	// seleciona um profissional com servico e seta o profissional no servico
	// direto sem proposta, o contratante escolhe o profissional que quer para
	// executar seu serviço
	@RequestMapping(value = "/profissional/{id_profissional}/servico/{id_servico}/fazerOferta", method = RequestMethod.POST)
	public void selecionarProfissionalDireto(@PathVariable("id_profissional") Integer id,
			@PathVariable("id_servico") Integer idServico) throws ServletException {
		servicoService.selecionarProfissional(pessoaDao.findById(id), servicoService.findById(idServico));
	}

	// contratanteProfissional seleciona uma das proposta em que o serviço
	// recebeu para executalo
	@RequestMapping(value = "/servico/{id_servico}/proposta/{id_proposta}", method = RequestMethod.POST)
	public void selecionarProposta(@PathVariable("id_proposta") Integer id, @PathVariable("id_servico") Integer idServico)
			throws ServletException {
		servicoService.selecionarProposta(id, servicoService.findById(idServico));
	}

	// avalia o profissional baseado em um determinado serviço serviço
	@RequestMapping(value = "/servico/{id_servico}/avaliaProfissional", method = RequestMethod.POST)
	public void avaliaProfissional(@PathVariable("id_servico") Integer idServico,
			@RequestBody AvaliacaoProfissional avaliacaoProfissional) throws ServletException {
		servicoService.avaliaProfissional(idServico, avaliacaoProfissional);
	}

	// finaliza um serviço que esta Em Andamento
	@RequestMapping(value = "/servico/{id_servico}/finalizarServico", method = RequestMethod.PUT)
	public void finalizarServico(@PathVariable("id_servico") Integer idServico) throws ServletException {
		servicoService.finalizarServico(servicoService.findById(idServico));
	}

	// retorna toda a lista de avaliações pendentes tanto do lado contratante
	// quanto do lado profissional
	@RequestMapping(value = "/{id_pessoa}/avalicoesPendentes", method = RequestMethod.GET)
	public List<Servico> avalicoesPendentes(@PathVariable("id_pessoa") Integer id) throws ServletException {
		return servicoService.avalicoesPendentesContratanteProfissional(pessoaDao.findById(id));
	}

	// aceita ou recusa um determinado serviço, se responder 0 ele recusa e 1
	// ele aceita
	@RequestMapping(value = "/servico/{idServicol}/aceitarServico/{resposta}", method = RequestMethod.POST)
	public void aceitarServico(@PathVariable("idServicol") Integer idServicol,
			@PathVariable("resposta") Integer resposta) {
		servicoService.aceitarServico(servicoService.findById(idServicol), resposta);
	}

	// escolhe um determinado serviço e faz uma proposta para o mesmo
	@RequestMapping(value = "/{id_profissional}/servico/{idServico}/fazerProposta", method = RequestMethod.POST)
	public void fazerProposta(@PathVariable("id_profissional") Integer id, @PathVariable("idServico") Integer idServico,
			@RequestBody Proposta proposta) throws ServletException {
		servicoService.fazerProposta(pessoaDao.findById(id), servicoService.findById(idServico), proposta);
	}

	// avalia o contratante baseado no serviço
	@RequestMapping(value = "/servico/{id_servico}/avaliaContratante", method = RequestMethod.POST)
	public void avaliaContratante(@PathVariable("id_servico") Integer idServico,
			@RequestBody AvaliacaoContratante avaliacaoContratante) throws ServletException {
		servicoService.avaliaContratante(idServico, avaliacaoContratante);
	}

	// traz toda a lista de serviços que o profissional executou
	@RequestMapping(value = "/{id_profissional}/servicosExecutados", method = RequestMethod.GET)
	public List<Servico> getAllServicosExecutados(@PathVariable("id_profissional") Integer id) throws ServletException {
		return servicoService.getAllServicosExecutados(pessoaDao.findById(id));

	}

	// Deixa de ser um profissional e passa a ser apenas contratante
	@RequestMapping(value = "/{id_profissional}/deixarDeSerProfissional", method = RequestMethod.PUT)
	public void deixarDeSerProfissional(@RequestBody ContratanteProfissional contratante) {
		contratanteProfissionalService.deixarDeSerProfissional(contratante);
	}
}
