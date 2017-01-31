package be4service2.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import be4service2.models.ContratanteProfissional;
import be4service2.models.Pessoa;
import ch.qos.logback.core.net.SyslogOutputStream;

@Repository
public class PessoaDaoImpl {

	@PersistenceContext
	private EntityManager manager;

	public Pessoa findById(Integer id) {
	 	return manager.find(Pessoa.class, id);
	
	}

	public Pessoa verificaLogin(String login, String senha) {
		String ss = "select p from Pessoa p where p.email=:emailurl AND p.senha=:senhaurl";

		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("emailurl", login);
		query.setParameter("senhaurl", senha);
		// Pessoa p = (Pessoa) query.getSingleResult();
		// String[] params={this.verificaTipo(p.getId()),p.getId()+""};

		return (Pessoa) query.getSingleResult();

	}

	public String verificaTipo(Integer id) {
		String t1 = "select count(p) from Pessoa p where p.id=:id && p.tipo='contratante'";
		String t2 = "select count(p) from Pessoa p where p.id=:id && p.tipo='profissional'";
		String t3 = "select count(p) from Pessoa p where p.id=:id && p.tipo='contratanteProfissional'";
		javax.persistence.Query query1 = manager.createQuery(t1);
		javax.persistence.Query query2 = manager.createQuery(t2);
		javax.persistence.Query query3 = manager.createQuery(t3);
		query1.setParameter("id", id);
		query2.setParameter("id", id);
		query3.setParameter("id", id);

		if (query1.getMaxResults() == 1) {
			return "contratante";
		} else if (query2.getMaxResults() == 1) {
			return "profissional";
		} else if (query3.getMaxResults() == 1) {
			return "contratanteProfissional";
		} else
			return "não encontrado";
	}

	@Transactional
	public void desativarConta(Integer id) {
		String ss = "UPDATE Pessoa p set tipo_pessoa='contaDesativada'where p.id=:idp";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("idp", id);
		query.executeUpdate();
	}

	@Transactional
	public void ativarConta(Integer id) {
		Pessoa p=this.findById(id);
		String ss = "UPDATE Pessoa p set tipo_pessoa=:tipo where p.id=:idp";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("idp", id);
		query.setParameter("tipo", p.getTipo());
		query.executeUpdate();
	}
	public Pessoa verificaEmail(String email) {

		try {
			String ss = "select p from Pessoa p where p.email=:emailurl";

			javax.persistence.Query query = manager.createQuery(ss);
			query.setParameter("emailurl", email);

			return (Pessoa) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Profissional> buscaProfissionalPorProfissao(String profissao) {
		String ss = "select p from Pessoa p where profissao LIKE :profissao AND tipo='profissional'";
		javax.persistence.Query query = manager.createQuery(ss);
		
		query.setParameter("profissao", "%"+profissao+"%");
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ContratanteProfissional> buscaContratanteProfissionalPorProfissao(String profissao) {
		String ss = "select p from Pessoa p where profissao LIKE :profissao AND tipo='contratanteProfissional'";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("profissao", "%"+profissao+"%");

		return query.getResultList();
	}
}
