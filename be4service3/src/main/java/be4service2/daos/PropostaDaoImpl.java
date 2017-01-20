package be4service2.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be4service2.models.Proposta;

@Repository
public class PropostaDaoImpl implements PropostaDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Proposta> all() {
		return manager.createQuery("select c from Proposta c", Proposta.class).getResultList();
	}

	
	@Override
	public void save(Proposta proposta) {
		manager.persist(proposta);
	}

	
	@Override
	public Proposta findById(Integer id) {
		return manager.find(Proposta.class, id);
	}

	
	@Override
	public void remove(Proposta proposta) {
		manager.remove(proposta);
	}

	
	@Override
	public void update(Proposta proposta) {
		manager.merge(proposta);
	}


	@Override
	public void mudarStatusParaRejeitado(Integer idServico, Integer idSelecionado) {
		String ss="UPDATE Proposta p set status='Rejeitado' where p.id=:idServico AND p.id!=:idselecionado";
		   javax.persistence.Query query = manager.createQuery(ss);
		   query.setParameter("idServicol", idServico);
		   query.setParameter("idselecionado", idSelecionado);
		   query.executeUpdate();
		
	}


	@Override
	public void mudarStatusParaRejeitadoDaPropostaRecusada(Integer idServico, Integer idSelecionado) {
		String ss="UPDATE Proposta p set p.status='Rejeitado' where p.id=:idselecionado";
		   javax.persistence.Query query = manager.createQuery(ss);
		   query.setParameter("idServicol", idServico);
		   query.setParameter("idselecionado", idSelecionado);
		   query.executeUpdate();
		
	}
	
	@Override
	public void mudarStatusParaAceitoDaPropostaEscolhida(Integer idServico, Integer idSelecionado) {
		String ss="UPDATE Proposta p set p.status='Aceito' where p.id=:idselecionado";
		   javax.persistence.Query query = manager.createQuery(ss);
		   query.setParameter("idServicol", idServico);
		   query.setParameter("idselecionado", idSelecionado);
		   query.executeUpdate();
		
	}
}
