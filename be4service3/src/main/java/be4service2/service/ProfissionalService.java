package be4service2.service;

import java.util.List;

import javax.servlet.ServletException;

import be4service2.models.Profissional;

public interface ProfissionalService {

	List<Profissional> all();

	void save(Profissional profissional) throws ServletException;

	Profissional findById(Integer id);

	void remove(Profissional profissional);

	void update(Profissional profissional);

	void tornarContratante(Integer id);
	
	void desativarConta(Profissional profissional);


}