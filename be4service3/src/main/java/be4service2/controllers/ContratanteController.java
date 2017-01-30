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
import be4service2.models.AvaliacaoProfissional;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Proposta;
import be4service2.models.Servico;
import be4service2.service.ContratanteService;
import be4service2.service.ServicoService;
/*   produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE*/

@RestController
@RequestMapping(value = "contratante")
public class ContratanteController {

	@Autowired
	private ContratanteService contratanteService;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private PessoaDaoImpl pessoaDao;

	// traz toda a lista de serviços que o contratante contratou
	@RequestMapping(value = "/{id}/servicosContratados", method = RequestMethod.GET)
	public List<Servico> getAllServicosContratados(@PathVariable("id") Integer id) {
		return servicoService.getListaServicosContratados(pessoaDao.findById(id));
	}

	// traz toda a lista de Propostas feitas para um determinado serviço
	@RequestMapping(value = "/{id}/propostasServico", method = RequestMethod.GET)
	public List<Proposta> getListaPropostasServico(@PathVariable("id") Integer id) throws ServletException {
		return servicoService.listaPropostasServico(servicoService.findById(id));
	}

	// salva o contratante
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(@RequestBody Contratante contratante) throws ServletException {
		contratanteService.save(contratante);
	}

	//update no contratante
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody Contratante contratante) {
		contratanteService.update(contratante);
	}

	// faz com que o contratante torne-se um contratanteProfissional
	@RequestMapping(value = "/tornarProfissional", method = RequestMethod.PUT)
	public void tornarProfissional(@RequestBody ContratanteProfissional contratante) {
		contratanteService.tornarProfissional(contratante);
	}

	// o contratante cria um serviço e esse serviço fica salvo na tabela serviço
	@RequestMapping(value = "/{id}/criarServico", method = RequestMethod.POST)
	public Servico criarServico(@PathVariable("id") Integer id, @RequestBody Servico servico) {
		return servicoService.criarServico(contratanteService.findById(id), servico);
	}

	// seleciona um profissional com servico e seta o profissional no servico
	// direto sem proposta, o contratante escolhe o profissional que quer para
	// executar seu serviço
	@RequestMapping(value = "/profissional/{id}/servico/{id_servico}/fazerOferta", method = RequestMethod.POST)
	public void selecionarProfissionalDireto(@PathVariable("id") Integer id,
			@PathVariable("id_servico") Integer idServico) throws ServletException {
		System.out.println(id + "kkkkkkkkk" + idServico);
		servicoService.selecionarProfissional(pessoaDao.findById(id), servicoService.findById(idServico));
	}

	// contratante seleciona uma das proposta em que o serviço recebeu para
	// executalo
	@RequestMapping(value = "/servico/{id_servico}/proposta/{id}", method = RequestMethod.POST)
	public void selecionarProposta(@PathVariable("id") Integer id, @PathVariable("id_servico") Integer idServico)
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

	// retorna uma lista de avaliações pendentes que o contratante ainda não
	// avaliou
	@RequestMapping(value = "/{id}/avalicoesPendentes", method = RequestMethod.GET)
	public List<Servico> avalicoesPendentes(@PathVariable("id") Integer id) {
		return servicoService.avalicoesPendentesContratante(contratanteService.findById(id));
	}

}
