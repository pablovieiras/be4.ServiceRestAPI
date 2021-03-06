package be4service2.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;

@Repository
public class ContratanteDaoImpl implements ContratanteDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Contratante> all() {
		return manager.createQuery("select c from Contratante c", Contratante.class).getResultList();
	}

	@Override
	public void save(Contratante contratante) {
		manager.persist(contratante);
	}

	@Override
	public Contratante findById(Integer id) {
		return manager.find(Contratante.class, id);
	}

	@Override
	public void remove(Contratante contratante) {
		manager.remove(manager.merge(contratante));
	}

	@Override
	public void update(Contratante contratante) {
		this.findById(contratante.getId());
		// Query query = (Query) manager.createNamedQuery("UPDATE pessoa SET
		// tipo = 'ContratanteProfissional' WHERE id = :id");
		manager.merge(contratante);
	}

@Override
public void tornarProfissional(ContratanteProfissional contratante) {
	// o que colocar
	/*String ss= "UPDATE Pessoa p SET p.tipo = 'ContratanteProfissional' WHERE p.id = :id";*/
	
	System.out.println("kkkkkkkkkkkkkkkkkkkkkkk"+contratante.toString());
	String ss="UPDATE Pessoa p set tipo_pessoa='contratanteProfissional',tipo='contratanteProfissional' where p.id=:id";

   javax.persistence.Query query = manager.createQuery(ss);

   query.setParameter("id", contratante.getId());
   
   query.executeUpdate();

	
}



}
