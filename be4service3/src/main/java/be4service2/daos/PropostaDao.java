package be4service2.daos;

import java.util.List;

import be4service2.models.Proposta;
import be4service2.models.Servico;

public interface PropostaDao {

	List<Proposta> all();

	void save(Proposta proposta);

	Proposta findById(Integer id);

	void remove(Proposta proposta);

	void update(Proposta proposta);
	
	void mudarStatusParaRejeitado(Integer idServico,Integer idSelecionado);
	
	void mudarStatusParaRejeitadoDaPropostaRecusada(Integer idServico,Integer idSelecionado);

}