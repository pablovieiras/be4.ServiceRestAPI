package be4service2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.models.Servico;
import be4service2.service.ServicoService;

@RestController
@RequestMapping(value= "/servico")
public class ServicoController
{

	@Autowired
   private ServicoService servicoService;
	
	private List<Servico> servico;
	public ServicoController(){
		servico=new ArrayList<Servico>();
		//servico.add(new Servico(1,"aa","aa","aa"));
		//servico.add(new Servico(2,"bb","bb","bb"));
		//servico.add(new Servico(2,"cc","cc","cc"));
	}
	
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
   
/*   produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE*/
   
   
	@RequestMapping(value="/save",method = RequestMethod.POST)
	  public void save(@RequestBody Servico servico){
		
		   servicoService.save(servico);
	   }
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public void update(@RequestBody Servico servico){
		
		   servicoService.update(servico);
	 }

	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void remove(@RequestBody Servico servico){
		   servicoService.remove(servico);
	 }
	
	 @RequestMapping(value="/listarAbertos",method = RequestMethod.GET)
	   public List<Servico> listarAbertos()
	   {
	      return servicoService.listarAbertos();
	   }














 /*  @RequestMapping(method = RequestMethod.POST)
   public void save(@Valid Servico servico)
   {
    
      servicoService.save(servico);
      
   }*/
   
/*
   @RequestMapping("/form")
   public ModelAndView form(Servico servico)
   {
      ModelAndView modelAndView = new ModelAndView("servico/form-add");
      return modelAndView;

   }

   @RequestMapping(method = RequestMethod.POST)
   public ModelAndView save(@Valid Servico servico, BindingResult bindingResult)
   {
      if (bindingResult.hasErrors())
      {
         return form(servico);
      }
      servicoService.save(servico);
      return new ModelAndView("redirect:/servico");
   }
*/

   
/*   
   @RequestMapping(method = RequestMethod.GET, value = "/{id}")
   public ModelAndView load(@PathVariable("id") Integer id)
   {
      ModelAndView modelAndView = new ModelAndView("servico/form-update");
      modelAndView.addObject("servico", servicoService.findById(id));
      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.GET)
   public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page)
   {
      ModelAndView modelAndView = new ModelAndView("servico/list");
      modelAndView.addObject("paginatedList", servicoService.paginated(page, 10));
      return modelAndView;
   }

   //just because get is easier here. Be my guest if you want to change.
   @RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
   public String remove(@PathVariable("id") Integer id)
   {
      Servico servico = servicoService.findById(id);
      servicoService.remove(servico);
      return "redirect:/servico";
   }

   @RequestMapping(method = RequestMethod.POST, value = "/{id}")
   public ModelAndView update(@PathVariable("id") Integer id, @Valid Servico servico, BindingResult bindingResult)
   {
      servico.setId(id);
      if (bindingResult.hasErrors())
      {
         return new ModelAndView("servico/form-update");
      }

      servicoService.update(servico);
      return new ModelAndView("redirect:/servico");
   }*/
}
