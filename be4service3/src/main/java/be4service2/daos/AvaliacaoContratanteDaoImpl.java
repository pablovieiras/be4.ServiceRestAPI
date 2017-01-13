package be4service2.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import be4service2.models.AvaliacaoContratante;

@Repository
public class AvaliacaoContratanteDaoImpl implements AvaliacaoContratanteDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AvaliacaoContratante> all() {
		return manager.createQuery("select c from AvaliacaoContratante c", AvaliacaoContratante.class).getResultList();
	}

	
	@Override
	public void save(AvaliacaoContratante avaliacaoContratante) {
		manager.persist(avaliacaoContratante);
	}

	
	@Override
	public AvaliacaoContratante findById(Integer id) {
		return manager.find(AvaliacaoContratante.class, id);
	}

	
	@Override
	public void remove(AvaliacaoContratante avaliacaoContratante) {
		manager.remove(avaliacaoContratante);
	}

	
	@Override
	public void update(AvaliacaoContratante avaliacaoContratante) {
		manager.merge(avaliacaoContratante);
	}
	
}
