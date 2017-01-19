package be4service2.daos;

import java.util.List;

import be4service2.models.Contratante;
import be4service2.models.Proposta;
import be4service2.models.Servico;

public interface ServicoDao {

	List<Servico> all();

	Servico save(Servico servico);

	Servico findById(Integer id);

	void remove(Servico servico);

	void update(Servico servico);
	
	List<Servico> allId(Contratante contratante);
	
	List<Servico> listarAbertos ();
	
	List<Proposta> listaPropostasServico(Servico servico);
	
}