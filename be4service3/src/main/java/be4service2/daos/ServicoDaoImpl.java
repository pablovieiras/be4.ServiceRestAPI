package be4service2.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be4service2.models.Contratante;
import be4service2.models.Servico;

@Repository
public class ServicoDaoImpl implements ServicoDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Servico> all() {
		return manager.createQuery("select c from Servico c", Servico.class).getResultList();
	}

	@Override
	public void save(Servico servico) {
		manager.persist(servico);
	}

	@Override
	public Servico findById(Integer id) {
		return manager.find(Servico.class, id);
	}

	@Override
	public void remove(Servico servico) {
		manager.remove(servico);
	}

	@Override
	public void update(Servico servico) {
		//servico=manager.persist(servico);
		manager.merge(this.findById(servico.getIdServico()));
	}

	//retorna lista dos contratantes do id
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> allId(Contratante contratante) {
		String ss="select s from Servico s where id_contratante=:idc";
		   javax.persistence.Query query = manager.createQuery(ss);
		   query.setParameter("idc", contratante.getId());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> listarAbertos() {
		String ss="select s from Servico s where status=Aberto";
		   javax.persistence.Query query = manager.createQuery(ss);
		 return query.getResultList();
	}




}
