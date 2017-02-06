package be4service2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.daos.PessoaDaoImpl;
import be4service2.models.AvaliacaoContratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Profissional;
import be4service2.models.Proposta;
import be4service2.models.Servico;
import be4service2.service.ContratanteProfissionalService;
import be4service2.service.ProfissionalService;
import be4service2.service.ServicoService;

@RestController
// @CrossOrigin(origins = "http://172.23.16.18")
@RequestMapping(value = "/profissional")
public class ProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private PessoaDaoImpl pessoaDao;
	@Autowired
	private ContratanteProfissionalService contrantranteProfissionalService;

	// Lista todos os profissionais e contratantesProfissionais usando o
	// ProfissionalDTO como objeto temporario
	@RequestMapping(method = RequestMethod.GET)
	public List<ProfissionalDTO> load() {

		// lista auxiliar com todos os profissionais
		List<Profissional> listlAux = profissionalService.all();
		List<ContratanteProfissional> listlAux2 = contrantranteProfissionalService.all();

		// lista com profissionais DTO
		List<ProfissionalDTO> listDTO = new ArrayList<>();

		for (Profissional x : listlAux) {
			listDTO.add(new ProfissionalDTO(x.getId(), x.getNome(), x.getEmail(), x.getResumoProfissional(),
					x.getProfissao(), x.getTelefone(), x.getCelular(), x.getCompetencias(), x.getAvaliacaoQualidade(),
					x.getAvaliacaoPreco(), x.getAvaliacaoPontualidade(), x.getCep(), x.getLougradouro(), x.getFoto()));
		}

		for (ContratanteProfissional x : listlAux2) {
			listDTO.add(new ProfissionalDTO(x.getId(), x.getNome(), x.getEmail(), x.getResumoProfissional(),
					x.getProfissao(), x.getTelefone(), x.getCelular(), x.getCompetencias(), x.getAvaliacaoQualidade(),
					x.getAvaliacaoPreco(), x.getAvaliacaoPontualidade(), x.getAvaliacaoCordialidade(),
					x.getAvaliacaoCompromisso(), x.getCep(), x.getLougradouro(), x.getFoto()));
		}

		return listDTO;
	}

	// procura ppor um profissional usando um determinado id e retorna um DTO
	@RequestMapping(value = "/{id_profissional}/DTO", method = RequestMethod.GET)
	public ProfissionalDTO findByIdDTO(@PathVariable("id_profissional") Integer id) {

		Profissional profissional = profissionalService.findById(id);
		ProfissionalDTO pdto = new ProfissionalDTO();
		pdto.setNome(profissional.getNome());
		pdto.setEmail(profissional.getEmail());
		pdto.setResumoProfissional(profissional.getResumoProfissional());
		pdto.setProfissao(profissional.getProfissao());
		pdto.setTelefone(profissional.getTelefone());
		pdto.setCelular(profissional.getCelular());
		pdto.setCompetencias(profissional.getCompetencias());
		pdto.setAvaliacaoQualidade(profissional.getAvaliacaoQualidade());
		pdto.setAvaliacaoPreco(profissional.getAvaliacaoPreco());
		pdto.setAvaliacaoPontualidade(profissional.getAvaliacaoPontualidade());
		pdto.setCep(profissional.getCep());
		pdto.setLogradouro(profissional.getLougradouro());
		pdto.setFoto(profissional.getFoto());
		
		return pdto;

	}
	@RequestMapping(value = "/{id_profissional}", method = RequestMethod.GET)
	public Profissional findById(@PathVariable("id_profissional") Integer id) {
		return profissionalService.findById(id);
	}
	// salva o profissional no banco
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(@RequestBody Profissional profissional) throws ServletException {
		System.out.println(profissional.toString());
		profissionalService.save(profissional);
	}

	// altera as informações do profissional
	@RequestMapping(value = "/{id_profissional}", method = RequestMethod.PUT)
	public void update(@RequestBody Profissional profissional) {
		profissionalService.update(profissional);
	}

	// o profissional se torna um contratanteProfissional
	@RequestMapping(value = "/{id_profissional}/tornarContratante", method = RequestMethod.PUT)
	public void tornarContratante(@PathVariable("id_profissional") Integer id) {
		profissionalService.tornarContratante(id);
	}

	// aceita ou recusa um determinado serviço, se responder 0 ele recusa e 1
	// ele aceita
	@RequestMapping(value = "/servico/{idServicol}/aceitarServico/{resposta}", method = RequestMethod.POST)
	public void aceitarServico(@PathVariable("idServicol") Integer idServicol,
			@PathVariable("resposta") Integer resposta) {
		servicoService.aceitarServico(servicoService.findById(idServicol), resposta);
	}

	// escolhe um determinado serviço e faz uma proposta para o mesmo
	@RequestMapping(value = "/{id_profissional}/servico/{idServico}/fazerProposta", method = RequestMethod.POST)
	public void fazerProposta(@PathVariable("id_profissional") Integer id, @PathVariable("idServico") Integer idServico,
			@RequestBody Proposta proposta) throws ServletException {
		servicoService.fazerProposta(pessoaDao.findById(id), servicoService.findById(idServico), proposta);
	}

	// avalia o contratante baseado no serviço
	@RequestMapping(value = "/servico/{id_servico}/avaliaContratante", method = RequestMethod.POST)
	public void avaliaContratante(@PathVariable("id_servico") Integer idServico,
			@RequestBody AvaliacaoContratante avaliacaoContratante) throws ServletException {
		servicoService.avaliaContratante(idServico, avaliacaoContratante);
	}

	// finaliza um serviço que esta Em Andamento
	@RequestMapping(value = "/servico/{id_servico}/finalizarServico", method = RequestMethod.PUT)
	public void finalizarServico(@PathVariable("id_servico") Integer idServico) throws ServletException {
		servicoService.finalizarServico(servicoService.findById(idServico));
	}

	// traz toda a lista de serviços que o profissional executou ou que foi
	// selecionado para executar
	@RequestMapping(value = "/{id_servico}/servicosExecutados", method = RequestMethod.GET)
	public List<Servico> getAllServicosExecutados(@PathVariable("id_servico") Integer id) throws ServletException {
		return servicoService.getAllServicosExecutados(profissionalService.findById(id));
	}

	// retorna uma lista de avaliações pendentes que o profissional ainda não
	// avaliou
	@RequestMapping(value = "/{id_profissional}/avalicoesPendentes", method = RequestMethod.GET)
	public List<Servico> avalicoesPendentes(@PathVariable("id_profissional") Integer id) throws ServletException {
		return servicoService.avalicoesPendentesProfissional(profissionalService.findById(id));
	}

}
