package be4service2.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be4service2.models.Contratante;
import be4service2.models.Profissional;
import be4service2.models.Proposta;
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
	public Servico save(Servico servico) {
		manager.persist(servico);
		return servico;
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
		// servico=manager.persist(servico);
		manager.merge(this.findById(servico.getIdServico()));
	}

	// retorna lista dos contratantes do id
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> getListaServicosContratados(Contratante contratante) {
		String ss = "select s from Servico s where id_contratante=:idc";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("idc", contratante.getId());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> listarAbertos() {
		String ss = "select s from Servico s where s.status='Aberto'";
		javax.persistence.Query query = manager.createQuery(ss);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> listaPropostasServico(Servico servico) {
		String ss = "select s from Proposta s where s.id_servico=:ids";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("ids", servico.getIdServico());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> getAllServicosExecutados(Profissional profissional) {
		String ss = "select s from Servico s where id_profissional=:idc";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("idc", profissional.getId());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> getAllPropostasFeitas(Profissional profissional) {
		String ss = "select s from Proposta s where s.id_profissional=:id";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("id", profissional.getId());
		return query.getResultList();
	}

}
