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
import be4service2.service.ProfissionalService;
import be4service2.service.ServicoService;
/*   produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE*/

@RestController
@RequestMapping(value = "contratante")
public class ContratanteController {

	@Autowired
	private ContratanteService contratanteService;

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private PessoaDaoImpl pessoaDao;


	@RequestMapping(value = "/{id}/servicosContratados", method = RequestMethod.GET)
	public List<Servico> getAllServicosContratados(@PathVariable("id") Integer id) {
		return servicoService.getListaServicosContratados(pessoaDao.findById(id));
	}

	@RequestMapping(value = "/{id}/propostasServico", method = RequestMethod.GET)
	public List<Proposta> getListaPropostasServico(@PathVariable("id") Integer id) throws ServletException {
		return servicoService.listaPropostasServico(servicoService.findById(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(@RequestBody Contratante contratante) throws ServletException {
		contratanteService.save(contratante);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody Contratante contratante) {
		contratanteService.update(contratante);
	}

	@RequestMapping(value = "/tornarProfissional", method = RequestMethod.PUT)
	public void tornarProfissional(@RequestBody ContratanteProfissional contratante) {
		contratanteService.tornarProfissional(contratante);
	}

	@RequestMapping(value = "/{id}/criarServico", method = RequestMethod.POST)
	public Servico criarServico(@PathVariable("id") Integer id, @RequestBody Servico servico) {

		return servicoService.criarServico(contratanteService.findById(id), servico);
	}

	// seleciona um profissional com servico e seta o profissional no servico
	// direto sem proposta
	@RequestMapping(value = "/profissional/{id}/servico/{id_servico}/fazerOferta", method = RequestMethod.POST)
	public void selecionarProfissionalDireto(@PathVariable("id") Integer id,
			@PathVariable("id_servico") Integer idServico) throws ServletException {
		System.out.println(id+"kkkkkkkkk"+idServico);
		servicoService.selecionarProfissional(pessoaDao.findById(id), servicoService.findById(idServico));
	}



	/*
	 * @RequestMapping(value=
	 * "/profissional/{id}/servico/{id_servico}/fazerOferta",method =
	 * RequestMethod.POST) public void
	 * selecionarPrsofissional(@PathVariable("id") Integer
	 * id,@PathVariable("id_servico") Integer idServico) throws
	 * ServletException{
	 * servicoService.selecionarProfissional(profissionalService.findById(id),
	 * servicoService.findById(idServico)); }
	 */

	@RequestMapping(value = "/servico/{id_servico}/proposta/{id}", method = RequestMethod.POST)
	public void selecionarProposta(@PathVariable("id") Integer id, @PathVariable("id_servico") Integer idServico)
			throws ServletException {
		servicoService.selecionarProposta(id, servicoService.findById(idServico));
	}

	@RequestMapping(value = "/servico/{id_servico}/avaliaProfissional", method = RequestMethod.POST)
	public void avaliaProfissional(@PathVariable("id_servico") Integer idServico,
			@RequestBody AvaliacaoProfissional avaliacaoProfissional) throws ServletException {
		servicoService.avaliaProfissional(idServico, avaliacaoProfissional);
	}

	@RequestMapping(value = "/servico/{id_servico}/finalizarServico", method = RequestMethod.PUT)
	public void finalizarServico(@PathVariable("id_servico") Integer idServico) throws ServletException {
		servicoService.finalizarServico(servicoService.findById(idServico));
	}
	
	@RequestMapping(value = "/{id}/avalicoesPendentes", method = RequestMethod.GET)
	public List<Servico> avalicoesPendentes(@PathVariable("id") Integer id) {
				
		return servicoService.avalicoesPendentesContratante(contratanteService.findById(id));
	}

}
