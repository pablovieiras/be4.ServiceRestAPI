package be4service2.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import be4service2.models.Profissional;

@Repository
public class ProfissionalDaoImpl implements ProfissionalDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Profissional> all() {
		return manager.createQuery("select c from Profissional c", Profissional.class).getResultList();
	}

	@Override
	public void save(Profissional profissional) {
		manager.persist(profissional);
	}

	@Override
	public Profissional findById(Integer id) {
		return manager.find(Profissional.class, id);
	}

	@Override
	public void remove(Profissional profissional) {
		manager.remove(profissional);
	}

	@Override
	public void update(Profissional profissional) {
		manager.merge(profissional);
	}
	
	@Override
	public void tornarContratante(Integer id) {
		/*String ss= "UPDATE Pessoa p SET p.tipo = 'ContratanteProfissional' WHERE p.id = :id";*/
		String ss="UPDATE Pessoa p set tipo='contratanteProfissional' where p.id=:id";
	    javax.persistence.Query query = manager.createQuery(ss);
	    query.setParameter("id", id);
	    query.executeUpdate();
		//manager.merge(profissional);
		
	}


}
