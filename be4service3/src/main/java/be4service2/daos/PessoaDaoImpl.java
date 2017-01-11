package be4service2.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import be4service2.models.Pessoa;

@Repository
public class PessoaDaoImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Pessoa> all() {
		return manager.createQuery("select c from Pessoa c", Pessoa.class).getResultList();
	}

	
	public void save(Pessoa pessoa) {
		manager.persist(pessoa);
	}

	
	public Pessoa findById(Integer id) {
		return manager.find(Pessoa.class, id);
	}

	
	public void remove(Pessoa pessoa) {
		manager.remove(pessoa);
	}

	
	public void update(Pessoa pessoa) {
		manager.merge(pessoa);
	}
	
}
