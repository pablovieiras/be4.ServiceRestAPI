package be4service2.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be4service2.models.ContratanteProfissional;

@Repository
public class ContratanteProfissionalDaoImpl implements ContratanteProfissionalDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ContratanteProfissional> all() {
		return manager.createQuery("select c from ContratanteProfissional c", ContratanteProfissional.class).getResultList();

	}

	@Override
	public void save(ContratanteProfissional contratanteProfissional) {
		manager.persist(contratanteProfissional);
	}

	@Override
	public ContratanteProfissional findById(Integer id) {
		return manager.find(ContratanteProfissional.class, id);
	}

	@Override
	public void remove(ContratanteProfissional contratanteProfissional) {
		manager.remove(manager.merge(contratanteProfissional));
	}

	@Override
	public void update(ContratanteProfissional contratanteProfissional) {
		manager.merge(contratanteProfissional);
	}

	public void deixarDeSerProfissional(ContratanteProfissional contratante) {
		String ss = "UPDATE Pessoa p set tipo_pessoa='contratante',tipo='contratante' where p.id=:id";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("id", contratante.getId());
		int a = query.executeUpdate();
		// manager.merge(contratante);
	}

}
