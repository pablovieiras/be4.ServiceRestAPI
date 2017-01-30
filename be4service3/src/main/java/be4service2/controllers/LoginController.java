package be4service2.controllers;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.daos.PessoaDaoImpl;
import be4service2.models.Pessoa;
import be4service2.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
@RestController
@RequestMapping(value= "/login",consumes=MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
	@Autowired
    private PessoaDaoImpl pessoaDao;
	
	@RequestMapping(value="/verificar",method = RequestMethod.POST)
	  public LoginResponse verificaLogin(@RequestBody Usuario p) throws ServletException{
		
		if(p.getEmail()==null || p.getEmail().equals("") || p.getSenha()==null || p.getSenha().equals("") ){
			throw new ServletException("Email e Senha são obrigatorios");
		}
		Pessoa pessoaAutenticada=null;
		try{
			//consulta banco
			 pessoaAutenticada= pessoaDao.verificaLogin(p.getEmail(), p.getSenha());
		}catch(SignatureException e){
			throw new ServletException("Usuario ou senha invalidos");
		}
		
		
		//olhar o setclain para permissoes
		//gera token de autenticação
		String token=Jwts.builder().
				setSubject(pessoaAutenticada.getEmail()).
				signWith(SignatureAlgorithm.HS512, "neppo").
				/*setExpiration(new Date(System.currentTimeMillis()+ 30 * 24 * 60 * 60 * 1000)).*/
				compact();
		
		return new LoginResponse(token);
	   }
	
	private class LoginResponse{
		
		@SuppressWarnings("unused")
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
	}
	
}
