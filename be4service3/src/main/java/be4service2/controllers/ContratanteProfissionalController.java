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
import be4service2.service.ContratanteService;
import be4service2.service.ProfissionalService;
import be4service2.service.ServicoService;

@RestController
@RequestMapping(value = "/contratanteProfissional")

public class ContratanteProfissionalController {

	@Autowired
	private ContratanteProfissionalService contratanteProfissionalService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private ProfissionalService profissionalService;
	@Autowired
	private ContratanteService contratanteService;
	@Autowired
	private PessoaDaoImpl pessoaDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<ContratanteProfissional> load() {
		return contratanteProfissionalService.all();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ContratanteProfissional findById(@PathVariable("id") Integer id) {
		return contratanteProfissionalService.findById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(@RequestBody ContratanteProfissional contratanteProfissional) {

		contratanteProfissionalService.save(contratanteProfissional);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody ContratanteProfissional contratanteProfissional) {

		contratanteProfissionalService.update(contratanteProfissional);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void remove(@RequestBody ContratanteProfissional contratanteProfissional) {
		contratanteProfissionalService.remove(contratanteProfissional);
	}

	@RequestMapping(value = "/{id}/servico/{idServico}/fazerProposta", method = RequestMethod.POST)
	public void fazerProposta(@PathVariable("id") Integer id, @PathVariable("idServico") Integer idServico,
			@RequestBody Proposta proposta) throws ServletException {
		System.out.println(proposta.toString());
		servicoService.fazerProposta(profissionalService.findById(id), servicoService.findById(idServico), proposta);
	}

	@RequestMapping(value = "/{id}/deixarDeSerProfissional", method = RequestMethod.PUT)
	public void deixarDeSerProfissional(@RequestBody ContratanteProfissional contratante) {
		// Contratante contratante= new
		// Contratante(c.getId(),c.getNome(),c.getAvaliacaoContratante());
		contratanteProfissionalService.deixarDeSerProfissional(contratante);
	}

	@RequestMapping(value = "/{id}/criarServico", method = RequestMethod.POST)
	public Servico criarServico(@PathVariable("id") Integer id, @RequestBody Servico servico) {

		return servicoService.criarServico(contratanteProfissionalService.findById(id), servico);
	}

	@RequestMapping(value = "/{id}/servicosExecutados", method = RequestMethod.GET)
	public List<Servico> getAllServicosExecutados(@PathVariable("id") Integer id) throws ServletException {
		return servicoService.getAllServicosExecutados(pessoaDao.findById(id));

	}

	@RequestMapping(value = "/{id}/servicosContratados", method = RequestMethod.GET)
	public List<Servico> getAllServicosContratados(@PathVariable("id") Integer id) {
		return servicoService.getListaServicosContratados(pessoaDao.findById(id));
	}
	
	// seleciona um profissional com servico e seta o profissional no servico
	// direto sem proposta
	@RequestMapping(value = "/profissional/{id}/servico/{id_servico}/fazerOferta", method = RequestMethod.POST)
	public void selecionarProfissionalDireto(@PathVariable("id") Integer id,
			@PathVariable("id_servico") Integer idServico) throws ServletException {
		System.out.println(id+"kkkkkkkkk"+idServico);
		servicoService.selecionarProfissional(pessoaDao.findById(id), servicoService.findById(idServico));
	}


	//retorna toda a lista de avaliações pendentes tanto do lado contratante quanto do lado profissional
	@RequestMapping(value = "/{id}/avalicoesPendentes", method = RequestMethod.GET)
	public List<Servico> avalicoesPendentes(@PathVariable("id") Integer id) throws ServletException {
				
		return servicoService.avalicoesPendentesContratanteProfissional(pessoaDao.findById(id));
	}

	//aceitar ou recusar o serviço resposta 0 ele recusa e 1 ele aceita 
	@RequestMapping(value = "/servico/{idServicol}/aceitarServico/{resposta}", method = RequestMethod.POST)
	public void aceitarServico(@PathVariable("idServicol") Integer idServicol, @PathVariable("resposta") Integer resposta) {
		servicoService.aceitarServico(servicoService.findById(idServicol), resposta);
	}
	//finaliza o serviço 
	@RequestMapping(value = "/servico/{id_servico}/finalizarServico", method = RequestMethod.PUT)
	public void finalizarServico(@PathVariable("id_servico") Integer idServico) throws ServletException {
		servicoService.finalizarServico(servicoService.findById(idServico));
	}
	//avalia o contratante baseado no serviço
	@RequestMapping(value = "/servico/{id_servico}/avaliaContratante", method = RequestMethod.POST)
	public void avaliaContratante(@PathVariable("id_servico") Integer idServico,
			@RequestBody AvaliacaoContratante avaliacaoContratante) throws ServletException {
		servicoService.avaliaContratante(idServico, avaliacaoContratante);
	}
	//avalia o profissional baseado no serviço
	@RequestMapping(value = "/servico/{id_servico}/avaliaProfissional", method = RequestMethod.POST)
	public void avaliaProfissional(@PathVariable("id_servico") Integer idServico,
			@RequestBody AvaliacaoProfissional avaliacaoProfissional) throws ServletException {
		servicoService.avaliaProfissional(idServico, avaliacaoProfissional);
	}
}
