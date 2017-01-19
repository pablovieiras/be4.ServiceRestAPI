package be4service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import be4service2.controllers.TokenFilter;

@SpringBootApplication
/*
@Configuration
@EnableAutoConfiguration
@ComponentScan({"controller","service"})*/
public class Boot
{
	@Bean
	public FilterRegistrationBean filtroJwt(){
		FilterRegistrationBean frb=new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/security/*");
		return frb;
	}
	
   public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
  
   }
}
