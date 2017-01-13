package be4service2.daos;

import java.util.List;

import be4service2.models.AvaliacaoContratante;

public interface AvaliacaoContratanteDao {

	List<AvaliacaoContratante> all();

	void save(AvaliacaoContratante avaliacaoContratante);

	AvaliacaoContratante findById(Integer id);

	void remove(AvaliacaoContratante avaliacaoContratante);

	void update(AvaliacaoContratante avaliacaoContratante);

}