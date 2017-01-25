package be4service2.service;

import java.util.List;

import javax.servlet.ServletException;

import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;

public interface ContratanteService {
	

	void save(Contratante contratante) throws ServletException;

	Contratante findById(Integer id);

	void remove(Contratante contratante);

	void update(Contratante contratante);
	
	void tornarProfissional(ContratanteProfissional contratante);
	
	
}