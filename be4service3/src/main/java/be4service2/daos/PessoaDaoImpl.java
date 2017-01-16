package be4service2.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be4service2.models.Pessoa;

@Repository
public class PessoaDaoImpl {

	@PersistenceContext
	private EntityManager manager;

	public Pessoa findById(Integer id) {
		return manager.find(Pessoa.class, id);
	}

	
	public Pessoa verificaLogin (String login, String senha){
		String ss="select p from Pessoa p where p.email=':email' && p.senha=':senha'";
		
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("email", login);
		query.setParameter("senha", senha);
		/*Pessoa p = (Pessoa) query.getSingleResult();
		String[] params={this.verificaTipo(p.getId()),p.getId()+""};*/
		
		return (Pessoa) query.getSingleResult();
		
	}
	
	public String verificaTipo(Integer id){
		String t1="select count(p) from Pessoa p where p.id=:id && p.tipo='contratante'";
		String t2="select count(p) from Pessoa p where p.id=:id && p.tipo='profissional'";
		String t3="select count(p) from Pessoa p where p.id=:id && p.tipo='contratanteProfissional'";
		javax.persistence.Query query1 = manager.createQuery(t1);
		javax.persistence.Query query2 = manager.createQuery(t2);
		javax.persistence.Query query3 = manager.createQuery(t3);
		query1.setParameter("id", id);
		query2.setParameter("id", id);
		query3.setParameter("id", id);
			
		if(query1.getMaxResults()==1){
				return "contratante";
		}else if (query2.getMaxResults()==1){
			return "profissional";
		}else if(query3.getMaxResults()==1){
			return "contratanteProfissional";
		}else return "não encontrado";
	}
		
}
	
	
	
	
	

