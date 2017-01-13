package be4service2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import be4service2.models.ContratanteProfissional;
import be4service2.models.Servico;
import be4service2.service.ContratanteProfissionalService;
import be4service2.service.ProfissionalService;
import be4service2.service.ServicoService;

@RestController
@CrossOrigin(origins = "http://localhost:50281")
@RequestMapping(value= "/contratanteProfissional")

public class ContratanteProfissionalController
{

	@Autowired
   private ContratanteProfissionalService contratanteProfissionalService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private ProfissionalService profissionalService;
		
	
	private List<ContratanteProfissional> contratanteProfissional;
	public ContratanteProfissionalController(){
		contratanteProfissional=new ArrayList<ContratanteProfissional>();
		//contratanteProfissional.add(new ContratanteProfissional(1,"aa","aa","aa"));
		//contratanteProfissional.add(new ContratanteProfissional(2,"bb","bb","bb"));
		//contratanteProfissional.add(new ContratanteProfissional(2,"cc","cc","cc"));
	}
	
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
   
/*   produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE*/
   
   
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
	public void selecionarProfissional(@PathVariable("id") Integer id,@PathVariable("id_servico") Integer idServico){
	
		   	servicoService.selecionarProfissional(profissionalService.findById(id),servicoService.findById(idServico));
	 }






}
