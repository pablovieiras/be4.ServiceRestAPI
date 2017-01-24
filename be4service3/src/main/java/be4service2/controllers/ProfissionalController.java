package be4service2.controllers;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.models.AvaliacaoContratante;
import be4service2.models.Profissional;
import be4service2.models.Proposta;
import be4service2.models.Servico;
import be4service2.service.ProfissionalService;
import be4service2.service.ServicoService;

@RestController
/* @CrossOrigin(origins = "http://localhost:50281") */
@RequestMapping(value = "/profissional")

public class ProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	@Autowired
	private ServicoService servicoService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Profissional> load() {
		return profissionalService.all();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Profissional findById(@PathVariable("id") Integer id) {
		return profissionalService.findById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(@RequestBody Profissional profissional) throws ServletException {

		profissionalService.save(profissional);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody Profissional profissional) {

		profissionalService.update(profissional);
	}

	@RequestMapping(value = "/{id}/tornarContratante", method = RequestMethod.PUT)
	public void tornarContratante(@PathVariable("id") Integer id) {
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+id);
		profissionalService.tornarContratante(id);
	}

	@RequestMapping(value = "/servico/{id}/aceitarServico/{resposta}", method = RequestMethod.POST)
	public void aceitarServico(@PathVariable("id") Integer id, @PathVariable("resposta") Integer resposta) {
		servicoService.aceitarServico(id, resposta);
	}

	@RequestMapping(value = "/{id}/servico/{idServico}/fazerProposta", method = RequestMethod.POST)
	public void fazerProposta(@PathVariable("id") Integer id, @PathVariable("idServico") Integer idServico,
			@RequestBody Proposta proposta) {
		System.out.println(proposta.toString());
		servicoService.fazerProposta(profissionalService.findById(id), servicoService.findById(idServico), proposta);
	}

	@RequestMapping(value = "/servico/{id_servico}/avaliaContratante", method = RequestMethod.POST)
	public void avaliaContratante(@PathVariable("id_servico") Integer idServico,
			@RequestBody AvaliacaoContratante avaliacaoContratante) throws ServletException {
		servicoService.avaliaContratante(idServico, avaliacaoContratante);
	}

	@RequestMapping(value = "/servico/{id_servico}/finalizarServico", method = RequestMethod.PUT)
	public void finalizarServico(@PathVariable("id_servico") Integer idServico) {
		servicoService.finalizarServico(servicoService.findById(idServico));
	}
	
	@RequestMapping(value="/{idProfissional}/desativarConta",method = RequestMethod.PUT)
	public void desativarConta (@PathVariable("idProfissional")Integer idProfissional){
		profissionalService.desativarConta(profissionalService.findById(idProfissional));
	}
	
	@RequestMapping(value="/{id}/servicosExecutados",method = RequestMethod.GET)
	   public List<Servico> getAllServicosExecutados(@PathVariable("id") Integer id)
	   { 
		   return servicoService.getAllServicosExecutados(profissionalService.findById(id));
				   
	   }


}
