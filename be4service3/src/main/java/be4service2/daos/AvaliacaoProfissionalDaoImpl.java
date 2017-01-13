package be4service2.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import be4service2.models.AvaliacaoProfissional;

@Repository
public class AvaliacaoProfissionalDaoImpl implements AvaliacaoProfissionalDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AvaliacaoProfissional> all() {
		return manager.createQuery("select c from AvaliacaoProfissional c", AvaliacaoProfissional.class).getResultList();
	}

	
	@Override
	public void save(AvaliacaoProfissional avaliacaoProfissional) {
		manager.persist(avaliacaoProfissional);
	}

	
	@Override
	public AvaliacaoProfissional findById(Integer id) {
		return manager.find(AvaliacaoProfissional.class, id);
	}

	
	@Override
	public void remove(AvaliacaoProfissional avaliacaoProfissional) {
		manager.remove(avaliacaoProfissional);
	}

	
	@Override
	public void update(AvaliacaoProfissional avaliacaoProfissional) {
		manager.merge(avaliacaoProfissional);
	}
	
}
