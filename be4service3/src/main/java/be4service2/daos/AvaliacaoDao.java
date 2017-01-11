package be4service2.daos;

import java.util.List;

import be4service2.models.Avaliacao;

public interface AvaliacaoDao {

	List<Avaliacao> all();

	void save(Avaliacao avaliacao);

	Avaliacao findById(Integer id);

	void remove(Avaliacao avaliacao);

	void update(Avaliacao avaliacao);

}