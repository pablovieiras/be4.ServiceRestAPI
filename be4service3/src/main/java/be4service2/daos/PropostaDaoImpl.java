package be4service2.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import be4service2.models.Proposta;

@Repository
public class PropostaDaoImpl implements PropostaDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Proposta> all() {
		return manager.createQuery("select c from Proposta c", Proposta.class).getResultList();
	}

	
	@Override
	public void save(Proposta proposta) {
		manager.persist(proposta);
	}

	
	@Override
	public Proposta findById(Integer id) {
		return manager.find(Proposta.class, id);
	}

	
	@Override
	public void remove(Proposta proposta) {
		manager.remove(proposta);
	}

	
	@Override
	public void update(Proposta proposta) {
		manager.merge(proposta);
	}
	
}