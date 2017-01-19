package be4service2.service;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be4service2.daos.AvaliacaoContratanteDao;
import be4service2.daos.AvaliacaoProfissionalDao;
import be4service2.daos.ContratanteDao;
import be4service2.daos.ProfissionalDao;
import be4service2.daos.PropostaDao;
import be4service2.daos.ServicoDao;
import be4service2.models.AvaliacaoContratante;
import be4service2.models.AvaliacaoProfissional;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Pessoa;
import be4service2.models.Profissional;
import be4service2.models.Proposta;
import be4service2.models.Servico;


@Service
@Transactional
public class ServicolServiceImpl implements ServicoService {
	 @Autowired
	   private ServicoDao servicoDao;
	 @Autowired
	   private PropostaDao propostaDao;
	 @Autowired
	   private ProfissionalDao profissionalDao;
	 @Autowired
	   private AvaliacaoContratanteDao avaliacaoContratanteDao;
	 @Autowired
	   private AvaliacaoProfissionalDao avaliacaoProfissionalDao;
	 @Autowired
	   private ContratanteDao contratanteDao;
	
	@Override
	public List<Servico> all() {
		
		return servicoDao.all();
	}

	@Override
	public void save(Servico servico) {
		 servicoDao.save(servico);
		
	}

	@Override
	public Servico findById(Integer id) {
		
		return servicoDao.findById(id);
	}

	@Override
	public void remove(Servico servico) {
		servicoDao.remove(servico);
		
	}

	@Override
	public void update(Servico servico) {
		servicoDao.update(servico);
		
	}

	@Override
	public Servico criarServico(Contratante contratante,Servico servico) {
		servico.setContratante(contratante);
		
		servico.setStatus("Aberto");
		return servicoDao.save(servico);
		 
	}
	
	@Override
	public void finalizarServico(Servico servico) {
		servico.setStatus("Finalizado");
		servicoDao.update(servico);
	}
	

	@Override
	public void selecionarProfissional(Profissional profissional, Servico servico,Integer idProposta) throws ServletException {
		if(profissional==null){
			throw new ServletException("Profissional invalido");
		}
		Proposta p=propostaDao.findById(idProposta);
		if(p==null){
			throw new ServletException("proposta não existe");
		}
		
		servico.setProfissional(profissional);
		servico.setStatus("Aguardando Aceitação do Profissional");
		
		servico.setValor(p.getValorProposta());
		servicoDao.update(servico);
		
	}
	
	@Override
	public void selecionarProfissional(Pessoa profissional, Servico servico) {
		if(profissional!=null){
			servico.setProfissional(profissional);

			servico.setStatus("Aguardando Aceitação do Profissional");
			servicoDao.update(servico);
		}else{
			try {
				throw new Exception("Profissional não cadastrado.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void criarServico(ContratanteProfissional contratanteProfissional, Servico servico) {
		servico.setContratante(contratanteProfissional);
		contratanteProfissional.getServicosContratados().add(servico);
		servico.setStatus("Aberto");
		servicoDao.save(servico);
		
	}

	@Override
	public void selecionarProfissional(ContratanteProfissional profissional, Servico servico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aceitarServico(Integer id,Integer resposta) {
		//busca o servico
		Servico servico=servicoDao.findById(id);
		if(resposta==0){
			//muda status da proposta no banco
			propostaDao.mudarStatusParaRejeitadoDaPropostaRecusada(servico.getIdServico(), servico.getProfissional().getId());
			//muda o status
			servico.setStatus("Aberto");
			//remove o profissional do serviço
			servico.setProfissional(null);	
			//remove o valor
			servico.setValor(null);
		}
		else{
			servico.setStatus("Em Andamento");
			propostaDao.mudarStatusParaRejeitado(servico.getIdServico(), servico.getProfissional().getId());
			servicoDao.update(servico);//remover
			
		}
		
	}

	@Override
	public void fazerProposta(Pessoa pessoa,Servico servico, Proposta proposta) {
		proposta.setPessoa(pessoa);
		proposta.setServico(servico);		
		propostaDao.save(proposta);
		
	}
	

	@Override
	public void selecionarProposta(Integer id, Servico servico) {
		Proposta proposta= propostaDao.findById(id);
		servico.setProfissional(proposta.getPessoa());
		servico.setValor(proposta.getValorProposta());
		this.selecionarProfissional(proposta.getPessoa(), servico);
		
	}

	@Override
	public void avaliaProfissional(Integer idServico, AvaliacaoProfissional avaliacaoProfissional) throws ServletException  {
		
		//busca o id do servico e guarda em uma variavel
		Servico servico=servicoDao.findById(idServico);
		
		if(servico.isAvaliacaoProfissional()==true){
			throw new ServletException("avaliação ja realizada");
		}
		servico.setAvaliacaoProfissional(true);
		if(servico.getStatus().equals("Em Andamento")||servico.getStatus().equals("Finalizado")){
			//guarda o servico dentro da avaliacao
			avaliacaoProfissional.setServico(servico);
			//guarda o profissional dentro da avaliacao
			avaliacaoProfissional.setProfissional(servico.getProfissional());
			Profissional p=profissionalDao.findById(servico.getProfissional().getId());
			p.mediaAvaliacao(avaliacaoProfissional.getAvaliacaoQualidade(),avaliacaoProfissional.getAvaliacaoPreco(),avaliacaoProfissional.getAvaliacaoPontualidade());
			profissionalDao.update(p);
			//salvar a avaliacao
			avaliacaoProfissionalDao.save(avaliacaoProfissional);
			
		}
		else{
			try {
				throw new Exception("Erro status do servico não esta em andamento ou finalizado");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void avaliaContratante(Integer idServico, AvaliacaoContratante avaliacaoContratante)  {
		
		//busca o id do servico e guarda em uma variavel
		Servico servico=servicoDao.findById(idServico);
		
		if(servico.getStatus().equals("Em Andamento")||servico.getStatus().equals("Finalizado")){
			//guarda o servico dentro da avaliacao
			avaliacaoContratante.setServico(servico);
			//guarda o contratante dentro da avaliacao
			avaliacaoContratante.setContratante(servico.getContratante());
			Contratante c=contratanteDao.findById(servico.getContratante().getId());
			c.mediaAvaliacao(avaliacaoContratante.getAvaliacaoCordialidade(),avaliacaoContratante.getAvaliacaoCompromisso());
			//salvar a avaliacao
			avaliacaoContratanteDao.save(avaliacaoContratante);
		}
		else{
			try {
				throw new Exception("Erro status do servico não esta em andamento ou finalizado");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Servico> getListaServicosContratados(Contratante contratante) {
	
		return servicoDao.allId(contratante);
	}

	@Override
	public List<Servico> listarAbertos() {

		return servicoDao.listarAbertos();
	}

	@Override
	public List<Proposta> listaPropostasServico(Servico servico) {

		return servicoDao.listaPropostasServico(servico);

	}

	
}
