package be4service2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.daos.PessoaDaoImpl;
import be4service2.models.Pessoa;

@RestController
@RequestMapping(value= "/login")
public class PessoaController {
	
	@Autowired
    private PessoaDaoImpl pessoaDao;
	
	@RequestMapping(value="/{email}/{senha}",method = RequestMethod.POST)
	  public Pessoa verificaLogin(@PathVariable("email") String email, @PathVariable("senha") String senha){
		return pessoaDao.verificaLogin(email, senha);
		   
	   }
	
}
