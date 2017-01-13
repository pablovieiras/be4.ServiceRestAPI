package be4service2.daos;

import java.util.List;

import be4service2.models.AvaliacaoProfissional;

public interface AvaliacaoProfissionalDao {

	List<AvaliacaoProfissional> all();

	void save(AvaliacaoProfissional avaliacaoProfissional);

	AvaliacaoProfissional findById(Integer id);

	void remove(AvaliacaoProfissional avaliacaoProfissional);

	void update(AvaliacaoProfissional avaliacaoProfissional);

}