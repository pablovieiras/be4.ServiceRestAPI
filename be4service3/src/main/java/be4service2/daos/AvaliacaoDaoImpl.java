package be4service2.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import be4service2.models.Avaliacao;

@Repository
public class AvaliacaoDaoImpl implements AvaliacaoDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Avaliacao> all() {
		return manager.createQuery("select c from Avaliacao c", Avaliacao.class).getResultList();
	}

	
	@Override
	public void save(Avaliacao avaliacao) {
		manager.persist(avaliacao);
	}

	
	@Override
	public Avaliacao findById(Integer id) {
		return manager.find(Avaliacao.class, id);
	}

	
	@Override
	public void remove(Avaliacao avaliacao) {
		manager.remove(avaliacao);
	}

	
	@Override
	public void update(Avaliacao avaliacao) {
		manager.merge(avaliacao);
	}
	
}
