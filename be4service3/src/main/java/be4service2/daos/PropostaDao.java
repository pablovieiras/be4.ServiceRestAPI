package be4service2.daos;

import java.util.List;

import be4service2.models.Proposta;

public interface PropostaDao {

	List<Proposta> all();

	void save(Proposta proposta);

	Proposta findById(Integer id);

	void remove(Proposta proposta);

	void update(Proposta proposta);

}