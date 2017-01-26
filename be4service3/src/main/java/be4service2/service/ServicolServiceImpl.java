package be4service2.service;

import java.util.ArrayList;
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
	public Servico criarServico(Contratante contratante, Servico servico) {
		servico.setContratante(contratante);

		servico.setStatus("Aberto");
		return servicoDao.save(servico);
	}

	@Override
	public void finalizarServico(Servico servico) {
		servico.setStatus("finalizado");
		servicoDao.update(servico);
	}

	/*
	 * @Override public void selecionarProfissional(Profissional profissional,
	 * Servico servico,BigDecimal valor) { if(profissional!=null){
	 * servico.setProfissional(profissional);
	 * 
	 * servico.setStatus("Aguardando Aceitação do Profissional");
	 * servico.setValor(valor); servicoDao.update(servico); }else{ try { throw
	 * new Exception("Profissional não cadastrado."); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * }
	 */

	@Override
	public void selecionarProfissional(Profissional profissional, Servico servico) throws ServletException {
		if (profissional == null) {
			throw new ServletException("Profissional não encontrado");
		}
		servico.setProfissional(profissional);
		servico.setStatus("Aguardando Aceitação do Profissional");
		servicoDao.update(servico);
	}

	@Override
	public void criarServico(ContratanteProfissional contratanteProfissional, Servico servico) {
		servico.setContratante(contratanteProfissional);
		// contratanteProfissional.getServicosContratados().add(servico);arrrumar
		servico.setStatus("Aberto");
		servicoDao.save(servico);

	}

	@Override
	public void selecionarProfissional(ContratanteProfissional profissional, Servico servico) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aceitarServico(Integer id, Integer resposta) {
		// busca o servico
		Servico servico = servicoDao.findById(id);
		if (resposta == 0) {
			// muda status apenas da proposta para rejeitado
			propostaDao.mudarStatusParaRejeitadoDaPropostaRecusada(servico.getIdServico(),
					servico.getProfissional().getId());
			// muda o status
			servico.setStatus("Aberto");
			// remove o profissional do serviço
			servico.setProfissional(null);
			// remove o valor
			servico.setValor(null);
		} else {
			servico.setStatus("Em Andamento");
			// muda o status da proposta de todos para rejeitado menos do
			// selecionado
			propostaDao.mudarStatusParaRejeitado(servico.getIdServico(), servico.getProfissional().getId());
			// muda o status do selecionado para Aceito
			propostaDao.mudarStatusParaAceitoDaPropostaEscolhida(servico.getIdServico(),
					servico.getProfissional().getId());
			servicoDao.update(servico);// remover

		}

	}

	@Override
	public void fazerProposta(Profissional profissional, Servico servico, Proposta proposta) throws ServletException {
		Proposta p = null;
		p = propostaDao.verificaProposta(servico.getIdServico(), profissional.getId());
		if (p == null) {
			throw new ServletException("Este Profissional já fez uma proposta para este serviço");
		}
		proposta.setProfissional(profissional);
		proposta.setServico(servico);
		propostaDao.save(proposta);
	}

	@Override
	public void selecionarProposta(Integer id, Servico servico) throws ServletException {
		Proposta p = propostaDao.findById(id);
		servico.setProfissional(p.getProfissional());
		servico.setValor(p.getValorProposta());
		this.selecionarProfissional(p.getProfissional(), servico);

	}

	@Override
	public void avaliaProfissional(Integer idServico, AvaliacaoProfissional avaliacaoProfissional)
			throws ServletException {

		// busca o id do servico e guarda em uma variavel
		Servico servico = servicoDao.findById(idServico);
		if (servico.isAvaliacaoProfissional() == true) {
			throw new ServletException("Profissional já avaliado neste serviço");
		}
		if (servico.isAvaliacaoProfissional() == true) {
			throw new ServletException("avaliação já realizada");
		}
		servico.setAvaliacaoProfissional(true);
		if (!servico.getStatus().equals("Em Andamento") && !servico.getStatus().equals("Finalizado")) {
			throw new ServletException("Erro status do serviço não está em andamento ou finalizado");
		}
		// guarda o servico dentro da avaliacao
		avaliacaoProfissional.setServico(servico);
		// guarda o profissional dentro da avaliacao
		avaliacaoProfissional.setProfissional(servico.getProfissional());
		Profissional p = profissionalDao.findById(servico.getProfissional().getId());
		p.mediaAvaliacao(avaliacaoProfissional.getAvaliacaoQualidade(), avaliacaoProfissional.getAvaliacaoPreco(),
				avaliacaoProfissional.getAvaliacaoPontualidade());
		profissionalDao.update(p);
		// salvar a avaliacao
		avaliacaoProfissionalDao.save(avaliacaoProfissional);
		// seta avaliacao do profissionao como true"ja avaliado"
		servico.setAvaliacaoProfissional(true);
		servicoDao.update(servico);

	}

	@Override
	public void avaliaContratante(Integer idServico, AvaliacaoContratante avaliacaoContratante)
			throws ServletException {

		// busca o id do servico e guarda em uma variavel
		Servico servico = servicoDao.findById(idServico);
		if (servico.isAvaliacaoContratante() == true) {
			throw new ServletException("Profissional já avaliado neste serviço");
		}
		if (servico.isAvaliacaoContratante() == true) {
			throw new ServletException("avaliação já realizada");
		}
		servico.setAvaliacaoContratante(true);
		if (!servico.getStatus().equals("Em Andamento") && !servico.getStatus().equals("Finalizado")) {
			throw new ServletException("Erro status do serviço não esta em andamento ou finalizado");
		}

		// guarda o servico dentro da avaliacao
		avaliacaoContratante.setServico(servico);
		// guarda o contratante dentro da avaliacao
		avaliacaoContratante.setContratante(servico.getContratante());
		Contratante c = contratanteDao.findById(servico.getContratante().getId());
		c.mediaAvaliacao(avaliacaoContratante.getAvaliacaoCordialidade(),
				avaliacaoContratante.getAvaliacaoCompromisso());
		// salvar a avaliacao
		avaliacaoContratanteDao.save(avaliacaoContratante);
		// seta avaliacao do profissionao como true"ja avaliado"
		servico.setAvaliacaoContratante(true);
		servicoDao.update(servico);

	}

	@Override
	public List<Servico> getListaServicosContratados(Contratante contratante) {

		return servicoDao.getListaServicosContratados(contratante);
	}

	@Override
	public List<Servico> listarAbertos() {

		return servicoDao.listarAbertos();
	}

	@Override
	public List<Proposta> listaPropostasServico(Servico servico) {

		return servicoDao.listaPropostasServico(servico);

	}

	@Override
	public List<Servico> getAllServicosExecutados(Profissional profissional) throws ServletException {
		List<Servico> lista = null;
		lista = servicoDao.getAllServicosExecutados(profissional);

		if (lista == null) {
			throw new ServletException("Nenhum serviço encontrado");
		}
		return lista;
	}

	@Override
	public List<Proposta> getAllPropostasFeitas(Profissional profissional) {
		return servicoDao.getAllPropostasFeitas(profissional);
	}

	@Override
	public List<Servico> avalicoesPendentesContratante(Contratante contratante) {

		List<Servico> listServicos = new ArrayList<>();
		List<Servico> listAvaPendentes = new ArrayList<>();

		listServicos = this.getListaServicosContratados(contratante);

		for (Servico x : listServicos) {
			if (x.isAvaliacaoContratante() == false) {
				listAvaPendentes.add(x);
			}
		}

		return listAvaPendentes;
	}

}
