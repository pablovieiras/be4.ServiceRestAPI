package be4service2.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.models.Avaliacao;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Servico;
import be4service2.service.ContratanteService;
import be4service2.service.ProfissionalService;
import be4service2.service.ServicoService;

@RestController
@RequestMapping(value= "/contratante")

public class ContratanteController
{

	@Autowired
    private ContratanteService contratanteService;
	
	@Autowired
	private ProfissionalService profissionalService;
		
	@Autowired
	private ServicoService servicoService;
		
	private List<Contratante> contratante;
	public ContratanteController(){
		contratante=new ArrayList<Contratante>();
		//contratante.add(new Contratante(1,"aa","aa","aa"));
		//contratante.add(new Contratante(2,"bb","bb","bb"));
		//contratante.add(new Contratante(2,"cc","cc","cc"));
	}
	
   @RequestMapping(method = RequestMethod.GET)
   public List<Contratante> load()
   {
      return contratanteService.all();
   }
   
   @RequestMapping(value="/{id}",method = RequestMethod.GET)
   public Contratante findById(@PathVariable("id") Integer id)
   {
      return contratanteService.findById(id);
   }
   
/*   produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE*/
   
   
	@RequestMapping(value="/save",method = RequestMethod.POST)
	  public void save(@RequestBody Contratante contratante){
		
		   contratanteService.save(contratante);
	   }
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public void update(@RequestBody Contratante contratante){
		
		   contratanteService.update(contratante);
	 }
	

	@RequestMapping(value="/{id}/tornarProfissional",method = RequestMethod.PUT)
	public void tornarProfissional(@PathVariable("id") Integer id,@RequestBody ContratanteProfissional contratante){
			
		   	contratanteService.tornarProfissional(id,contratante);
	 }

	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void remove(@RequestBody Contratante contratante){
		   contratanteService.remove(contratante);
	 }

	@RequestMapping(value="/{id}/criarServico",method = RequestMethod.POST)
	public void criarServico(@PathVariable("id") Integer id,@RequestBody Servico servico){
	
		   	servicoService.criarServico(contratanteService.findById(id),servico);
	 }
	//seleciona um profissional e faz uma oferta para ele fazer o determinado servico
	@RequestMapping(value="/profissional/{id}/servico/{id_servico}/fazerOferta",method = RequestMethod.POST)
	public void selecionarProfissional(@PathVariable("id") Integer id,@PathVariable("id_servico") Integer idServico,
			@RequestBody BigDecimal valor){
		   	servicoService.selecionarProfissional(profissionalService.findById(id),servicoService.findById(idServico),
		   			valor);
	 }


	@RequestMapping(value="/servico/{id_servico}/proposta/{id}",method = RequestMethod.POST)
	public void selecionarProposta(@PathVariable("id") Integer id,@PathVariable("id_servico") Integer idServico){
		   	servicoService.selecionarProposta(id,servicoService.findById(idServico));
	 }

	@RequestMapping(value="/servico/{id_servico}/avaliaProfissional",method = RequestMethod.POST)
	public void avaliaProfissional(@PathVariable("id_servico")Integer idServico,@RequestBody Avaliacao avaliacao){
		servicoService.avaliaProfissional(idServico, avaliacao);
	}
}
