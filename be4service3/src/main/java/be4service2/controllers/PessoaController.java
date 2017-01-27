package be4service2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.daos.PessoaDaoImpl;
import be4service2.models.Pessoa;

@RestController
public class PessoaController {
	
	@Autowired
    private PessoaDaoImpl pessoaDao;
	
	@RequestMapping(value="login2/email/{email}/senha/{senha}",method = RequestMethod.POST)
	  public Pessoa verificaLogin(@PathVariable("email") String email, @PathVariable("senha") String senha){
		
		return pessoaDao.verificaLogin(email, senha);
	   }
	
	@RequestMapping(value="desativarConta/{idPessoa}",method = RequestMethod.PUT)
	public void desativarConta (@PathVariable("idPessoa")Integer idPessoa){
		pessoaDao.desativarConta(idPessoa);
	}
	
	@RequestMapping(value="ativarConta/{idPessoa}",method = RequestMethod.PUT)
	public void ativarConta (@PathVariable("idPessoa")Integer idPessoa){
		pessoaDao.ativarConta(idPessoa);
	}
	
	
}
