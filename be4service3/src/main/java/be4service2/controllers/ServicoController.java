package be4service2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.models.Servico;
import be4service2.service.ServicoService;

/*   produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE*/

@RestController
@RequestMapping(value= "/servico")
public class ServicoController 
{

	@Autowired
   private ServicoService servicoService;

    
@RequestMapping(method = RequestMethod.GET)
   public List<Servico> load()
   {
      return servicoService.all();
   }
   
    
@RequestMapping(value="/{id}",method = RequestMethod.GET)
   public Servico findById(@PathVariable("id") Integer id)
   {
      return servicoService.findById(id);
   }

   
	
	 
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public void update(@RequestBody Servico servico){
		
		   servicoService.update(servico);
	 }

	
	  
	@RequestMapping(value="/listarAbertos",method = RequestMethod.GET)
	   public List<Servico> listarAbertos()
	   {
	      return servicoService.listarAbertos();
	   }
}
