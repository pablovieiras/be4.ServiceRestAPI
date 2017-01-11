package be4service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import be4service2.daos.ContratanteDao;
import be4service2.daos.ContratanteDaoImpl;
import be4service2.models.Contratante;

@SpringBootApplication
/*
@Configuration
@EnableAutoConfiguration
@ComponentScan({"controller","service"})*/
public class Boot
{

   public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
  
   }
}
