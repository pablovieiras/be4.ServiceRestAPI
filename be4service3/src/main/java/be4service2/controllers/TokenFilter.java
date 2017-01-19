package be4service2.controllers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class TokenFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		
		String header=req.getHeader("Authorization");
		System.out.println("IIIIIIIIIIIIIIIIIIIIIIIII  "+header );
		
		if(header==null || !header.startsWith("Bearer ")){
			throw new ServletException("Token inexistente ou invalido");
		}
		
		String token=header.substring(7);//Extrai a string do token sem o bearer
		try{
			//verifica se o token eh valido descodifica o token
			Jwts.parser().setSigningKey("neppo").parseClaimsJws(token).getBody();
		}
		catch(SignatureException e){
			throw new ServletException("Token inválido");
		}
		arg2.doFilter(arg0, arg1);
	}

}
