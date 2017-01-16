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


	public Pessoa findById(Integer id) {
		return manager.find(Pessoa.class, "select c.id from Pessoa c");
	}
	
	

}
