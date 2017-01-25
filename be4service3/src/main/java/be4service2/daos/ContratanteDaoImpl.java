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
	String ss="UPDATE Pessoa p set tipo_pessoa='contratanteProfissional',"
			+ "p.nome=:nome,"
			+ "p.cpf=:cpf,"
			+ "p.data_nascimento=:dataNascimento,"
			+ "p.email=:email,"
			+ "p.senha=:senha,"
			+ "p.telefone=:telefone,"
			+ "p.celular=:celular,"
			+ "p.cep=:cep,"
			+ "p.lougradouro=:lougradouro,"
			+ "p.numero=:numero,"
			+ "p.cidade=:cidade,"
			+ "p.bairro=:bairro,"
			+ "p.complemento=:complemento,"
			+ "p.tipo=:'contratanteProfissional',"
			+ "p.avaliacao_cordialidade=:avcordialidade,"
			+ "p.avaliacaoCompromisso=:avcompromisso,"
			+ "p.numero_avaliacoes_contratante=:numeroAvContrtante"
			+ "p.profissao=:profissao,"
			+ "p.resumo_profissional=:resumoProfissional,"
			+ "p.competencias=:competencias,"
			+ "p.avaliacao_qualidade=:avaliacaoQualidade,"
			+ "p.avaliacao_preco=:avaliacaoPreco,"
			+ "p.avaliacao+pontualidade=:avaliacaoPontualidade,"
			+ "p.numero_avaliacoes_profissional=:numeroAvProfissional"
			+ "where p.id=:id";
	

   javax.persistence.Query query = manager.createQuery(ss);
   query.setParameter("nome", contratante.getNome());
   query.setParameter("cpf", contratante.getCpf());
   query.setParameter("dataNascimento", contratante.getDataNascimento());
   query.setParameter("email", contratante.getEmail());
   query.setParameter("senha", contratante.getSenha());
   query.setParameter("telefone", contratante.getTelefone());
   query.setParameter("celular", contratante.getCelular());
   query.setParameter("cep", contratante.getCep());
   query.setParameter("lougradouro", contratante.getLougradouro());
   query.setParameter("numero", contratante.getNumero());
   query.setParameter("cidade", contratante.getCidade());
   query.setParameter("bairro", contratante.getBairro());
   query.setParameter("complemento", contratante.getComplemento());
   query.setParameter("avcordialidade", contratante.getAvaliacaoCordialidade());
   query.setParameter("avcompromisso", contratante.getAvaliacaoCompromisso());
   query.setParameter("numeroAvContrtante", contratante.getNumeroAvaliacoesContratante());
   query.setParameter("profissao", contratante.getProfissao());
   query.setParameter("resumoProfissional", contratante.getResumoProfissional());
   query.setParameter("competencias", contratante.getCompetencias());
   query.setParameter("avaliacaoQualidade", contratante.getAvaliacaoQualidade());
   query.setParameter("avaliacaoPreco", contratante.getAvaliacaoPreco());
   query.setParameter("avaliacaoPontualidade", contratante.getAvaliacaoPontualidade());
   query.setParameter("numeroAvProfissional", contratante.getNumeroAvaliacoesProfissional());
   
   query.executeUpdate();

	
}

	@Override
	public void desativarConta(Contratante contratante) {
		String ss = "UPDATE Pessoa p set tipo_pessoa='contaDesativada'where p.id=:id";
		javax.persistence.Query query = manager.createQuery(ss);
		query.setParameter("id", contratante.getId());
		query.executeUpdate();

	}

}
