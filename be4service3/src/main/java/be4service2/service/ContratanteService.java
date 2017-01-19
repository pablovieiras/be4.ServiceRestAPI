package be4service2.service;

import java.util.List;

import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;

public interface ContratanteService {
	List<Contratante> all();

	void save(Contratante contratante);

	Contratante findById(Integer id);

	void remove(Contratante contratante);

	void update(Contratante contratante);
	
	void tornarProfissional(Integer id,ContratanteProfissional contratante);
	
}