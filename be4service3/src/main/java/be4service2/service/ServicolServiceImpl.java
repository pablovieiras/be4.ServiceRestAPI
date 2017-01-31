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
import be4service2.daos.ContratanteProfissionalDao;
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
	private ContratanteProfissionalDao contratanteProfissionalDao;
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
	public void finalizarServico(Servico servico) throws ServletException {
		if(!servico.getStatus().equals("Em Andamento")){
			throw new ServletException("Serviço não está Em Andamento para ser Finalizado");
		}
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
	public void selecionarProfissional(Pessoa profissional, Servico servico) throws ServletException {
		if (profissional == null ) {
			throw new ServletException("Profissional não encontrado");
		}
		if(profissional.getTipo().equals("contratante")) {
			throw new ServletException("Profissional não encontrado");
		}
		if(profissional.getId()==servico.getContratante().getId()){
			throw new ServletException("Não é possivel fazer proposta para o seu proprio serviço");
		}
		if(!servico.getStatus().equals("Aberto")){
			throw new ServletException("Não é possivel fazer proposta para um serviço que não esta aberto");
		}
		servico.setProfissional(profissional);
		servico.setStatus("Aguardando Aceitação do Profissional");
		servicoDao.update(servico);
	}

	@Override
	public Servico criarServico(ContratanteProfissional contratanteProfissional, Servico servico) {
		servico.setContratante(contratanteProfissional);
		// contratanteProfissional.getServicosContratados().add(servico);arrrumar
		servico.setStatus("Aberto");
		return servicoDao.save(servico);

	}

	@Override
	public void selecionarProfissional(ContratanteProfissional profissional, Servico servico) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aceitarServico(Servico servico, Integer resposta) {
		// busca o servico
/*		Servico servico = servicoDao.findById(id);*/
	
		if (resposta == 0) {
			// muda status apenas da proposta para rejeitado
			System.out.println("aquiiiiiiiiiiiiiii"+servico.getIdServico());
			propostaDao.mudarStatusParaRejeitadoDaPropostaRecusada(servico.getIdServico(),servico.getProfissional().getId());
			System.out.println("ok");
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
	public void fazerProposta(Pessoa profissional, Servico servico, Proposta proposta) throws ServletException {
		
		if(!servico.getStatus().equals("Aberto")){
			throw new ServletException("Não é possivel fazer proposta para serviço que não esta em Aberto");
		}
		
		if(profissional.getTipo().equals("contratante")){
			throw new ServletException("Apenas profissionais pode fazer propostas");
		}
		
		if(servico.getContratante().getId().equals(profissional.getId())){
			throw new ServletException("Não é possivel fazer proposta para o seu proprio serviço");
		}
		
		 Proposta p = propostaDao.verificaProposta(servico.getIdServico(), profissional.getId());

		if (p!=null) {
			throw new ServletException("Este Profissional já fez uma proposta para este serviço");
		}
		proposta.setProfissional(profissional);
		proposta.setServico(servico);
		propostaDao.save(proposta);
	}

	@Override
	public void selecionarProposta(Integer id, Servico servico) throws ServletException {
		Proposta p = propostaDao.findById(id);
		if (p==null) {
			throw new ServletException("Proposta não encontrada");
		}
		servico.setProfissional(p.getProfissional());
		servico.setValor(p.getValorProposta());
		this.selecionarProfissional(p.getProfissional(), servico);

	}

	@Override
	public void avaliaProfissional(Integer idServico, AvaliacaoProfissional avaliacaoProfissional)
			throws ServletException {

		// busca o id do servico e guarda em uma variavel
		Servico servico = servicoDao.findById(idServico);
		if (servico.getAvaliacaoProfissional().equals("true")) {
			throw new ServletException("Profissional já avaliado neste serviço");
		}
	

		if (!servico.getStatus().equals("Em Andamento") && !servico.getStatus().equals("Finalizado")) {
			throw new ServletException("Erro status do serviço não está em andamento ou finalizado");
		}

		// guarda o servico dentro da avaliacao
		avaliacaoProfissional.setServico(servico);
		// guarda o profissional dentro da avaliacao
		avaliacaoProfissional.setProfissional(servico.getProfissional());


		if(servico.getProfissional().getTipo().equals("profissional")){
			Profissional p = profissionalDao.findById(servico.getProfissional().getId());
			p.mediaAvaliacao(avaliacaoProfissional.getAvaliacaoQualidade(), avaliacaoProfissional.getAvaliacaoPreco(),avaliacaoProfissional.getAvaliacaoPontualidade());
		}
		else if(servico.getProfissional().getTipo().equals("contratanteProfissional")){
			ContratanteProfissional p = contratanteProfissionalDao.findById(servico.getProfissional().getId());
			p.mediaAvaliacaoProfissional(avaliacaoProfissional.getAvaliacaoQualidade(), avaliacaoProfissional.getAvaliacaoPreco(),avaliacaoProfissional.getAvaliacaoPontualidade());
		}
		else{
			throw new ServletException("Tipo pessoa inválido");
		}
		
		// salvar a avaliacao
		avaliacaoProfissionalDao.save(avaliacaoProfissional);
		// seta avaliacao do profissionao como true"ja avaliado"
		servico.setAvaliacaoProfissional("true");
		servicoDao.update(servico);

	}

	@Override
	public void avaliaContratante(Integer idServico, AvaliacaoContratante avaliacaoContratante)
			throws ServletException {

		// busca o id do servico e guarda em uma variavel
		Servico servico = servicoDao.findById(idServico);
		if (servico.getAvaliacaoContratante().equals("true")) {
			throw new ServletException("Profissional já avaliado neste serviço");
		}
	
	
		if (!servico.getStatus().equals("Em Andamento") && !servico.getStatus().equals("Finalizado")) {
			throw new ServletException("Erro status do serviço não esta em andamento ou finalizado");
		}
		// guarda o servico dentro da avaliacao
		avaliacaoContratante.setServico(servico);
		// guarda o contratante dentro da avaliacao
		avaliacaoContratante.setContratante(servico.getContratante());
		//verifica se é contratantee ou contratanteProfissional
		if(servico.getContratante().getTipo().equals("contratante")){
			Contratante c = contratanteDao.findById(servico.getContratante().getId());
			c.mediaAvaliacao(avaliacaoContratante.getAvaliacaoCordialidade(),avaliacaoContratante.getAvaliacaoCompromisso());

		}
		else if(servico.getContratante().getTipo().equals("contratanteProfissional")){
			ContratanteProfissional c = contratanteProfissionalDao.findById(servico.getContratante().getId());
			c.mediaAvaliacaoContratante(avaliacaoContratante.getAvaliacaoCordialidade(),avaliacaoContratante.getAvaliacaoCompromisso());

		}
		else{
			throw new ServletException("Tipo pessoa inválido");
		}
		// salvar a avaliacao
		avaliacaoContratanteDao.save(avaliacaoContratante);
		// seta avaliacao do profissionao como true"ja avaliado"
		servico.setAvaliacaoContratante("true");
		servicoDao.update(servico);

	}

	@Override
	public List<Servico> getListaServicosContratados(Pessoa contratante) {

		return servicoDao.getListaServicosContratados(contratante);
	}

	@Override
	public List<Servico> listarAbertos() {

		return servicoDao.listarAbertos();
	}

	//somento serviços em aberto traz sua lista de porpostas 
	@Override
	public List<Proposta> listaPropostasServico(Servico servico) throws ServletException {
		if(!servico.getStatus().equals("Aberto")){
			throw new ServletException("Serviço não está em aberto no momento");
		}
		return servicoDao.listaPropostasServico(servico);

	}

	@Override
	public List<Servico> getAllServicosExecutados(Pessoa profissional) throws ServletException {
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
			if (x.getAvaliacaoContratante().equals("false")) {
				listAvaPendentes.add(x);
			}
		}

		return listAvaPendentes;
	}
	
	@Override
	public List<Servico> avalicoesPendentesProfissional(Profissional profissional) throws ServletException {

		List<Servico> listServicos = new ArrayList<>();
		List<Servico> listAvaPendentes = new ArrayList<>();

		listServicos = this.getAllServicosExecutados(profissional);

		for (Servico x : listServicos) {
			if (x.getAvaliacaoProfissional().equals("false")) {
				listAvaPendentes.add(x);
			}
		}

		return listAvaPendentes;
	}

	
	@Override
	public List<Servico> avalicoesPendentesContratanteProfissional(Pessoa contratanteProfissional) throws ServletException {
		List<Servico> listServicosContratados = new ArrayList<>();
		List<Servico> listServicosExecutados = new ArrayList<>();
		List<Servico> listAvaPendentes = new ArrayList<>();
		
		listServicosContratados = this.getListaServicosContratados(contratanteProfissional);
		listServicosExecutados = this.getAllServicosExecutados(contratanteProfissional);

		for (Servico x : listServicosContratados) {
			if (x.getAvaliacaoProfissional().equals("false")) {
				listAvaPendentes.add(x);
			}
		}

		for (Servico x : listServicosExecutados) {
			if (x.getAvaliacaoProfissional().equals("false")) {
				listAvaPendentes.add(x);
			}
		}
		return listAvaPendentes;
	
	}

	@Override
	public List<Servico> buscaServicoPorTitulo(String titulo) {
		return servicoDao.buscaServicoPorTitulo(titulo);
	}
	
	

}
