package be4service2.controllers;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.models.ContratanteProfissional;
import be4service2.models.Servico;
import be4service2.service.ContratanteProfissionalService;
import be4service2.service.ProfissionalService;
import be4service2.service.ServicoService;

@RestController
@RequestMapping(value= "/contratanteProfissional")

public class ContratanteProfissionalController
{

	@Autowired
   private ContratanteProfissionalService contratanteProfissionalService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private ProfissionalService profissionalService;
		
   @RequestMapping(method = RequestMethod.GET)
   public List<ContratanteProfissional> load()
   {
      return contratanteProfissionalService.all();
   }
   
   @RequestMapping(value="/{id}",method = RequestMethod.GET)
   public ContratanteProfissional findById(@PathVariable("id") Integer id)
   {
      return contratanteProfissionalService.findById(id);
   }
   
  
	@RequestMapping(value="/save",method = RequestMethod.POST)
	  public void save(@RequestBody ContratanteProfissional contratanteProfissional){
		
		   contratanteProfissionalService.save(contratanteProfissional);
	   }
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public void update(@RequestBody ContratanteProfissional contratanteProfissional){
		
		   contratanteProfissionalService.update(contratanteProfissional);
	 }
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void remove(@RequestBody ContratanteProfissional contratanteProfissional){
		   contratanteProfissionalService.remove(contratanteProfissional);
	 }




	@RequestMapping(value="/{id}/deixarDeSerProfissional",method = RequestMethod.PUT)
	public void deixarDeSerProfissional(@RequestBody ContratanteProfissional contratante){
			//Contratante contratante= new Contratante(c.getId(),c.getNome(),c.getAvaliacaoContratante());
		   contratanteProfissionalService.deixarDeSerProfissional(contratante);
	 }


	@RequestMapping(value="/{id}/criarServico",method = RequestMethod.POST)
	public void criarServico(@PathVariable("id") Integer id,@RequestBody Servico servico){
	
		   	servicoService.criarServico(contratanteProfissionalService.findById(id),servico);
	 }
	
	@RequestMapping(value="/profissional/{id}/servico/{id_servico}",method = RequestMethod.POST)
	public void selecionarProfissional(@PathVariable("id") Integer id,@PathVariable("id_servico") Integer idServico) throws ServletException{
	
		   	servicoService.selecionarProfissional(profissionalService.findById(id),servicoService.findById(idServico));
	 }

}
