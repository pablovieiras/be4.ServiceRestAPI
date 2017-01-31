package be4service2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.daos.PessoaDaoImpl;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Pessoa;
import be4service2.models.Usuario;

@RestController
public class PessoaController {

	@Autowired
	private PessoaDaoImpl pessoaDao;

	// verifica o login do usuario com a senha
	@RequestMapping(value = "login2/email/{email}/senha/{senha}", method = RequestMethod.POST)
	public Pessoa verificaLogin(@PathVariable("email") String email, @PathVariable("senha") String senha) {

		return pessoaDao.verificaLogin(email, senha);
	}

	// Desativa a conta por tempo indeterminado
	@RequestMapping(value = "desativarConta/{idPessoa}", method = RequestMethod.PUT)
	public void desativarConta(@PathVariable("idPessoa") Integer idPessoa) {
		pessoaDao.desativarConta(idPessoa);
	}

	// ativa a conta
	@RequestMapping(value = "ativarConta/{idPessoa}", method = RequestMethod.PUT)
	public void ativarConta(@PathVariable("idPessoa") Integer idPessoa) {
		pessoaDao.ativarConta(idPessoa);
	}
	
	//login auxilar para o android
	@RequestMapping(value="/email",method = RequestMethod.POST)
	  public Pessoa verificaLoginAndroide(@RequestBody Usuario p){
		System.out.println(p.getEmail() + " " + p.getSenha());
		return pessoaDao.verificaLogin(p.getEmail(), p.getSenha());
	   }

	//busca uma pessoa ppor um determinado id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Pessoa findByIdPessoa(@PathVariable("id") String idString) {
		Integer id=Integer.parseInt(idString);
		return pessoaDao.findById(id);
		
	}
	// realiza a busca de um profissional de acordo com sua profissão
	@RequestMapping(value = "buscaProfissionalPorProfissao/{profissao}", method = RequestMethod.GET)
	public List<ProfissionalDTO> buscaProfissionalPorProfissao(@PathVariable("profissao") String profissao) {

		List<Profissional> listlAuxProf = pessoaDao.buscaProfissionalPorProfissao(profissao);
		List<ContratanteProfissional> listlAux2ContraProf = pessoaDao.buscaContratanteProfissionalPorProfissao(profissao);

		// lista com profissionais DTO
		List<ProfissionalDTO> listDTO = new ArrayList<>();

		for (Profissional x : listlAuxProf) {
			listDTO.add(new ProfissionalDTO(x.getId(), x.getNome(), x.getEmail(), x.getResumoProfissional(),
					x.getProfissao(), x.getTelefone(), x.getCelular(), x.getCompetencias(), x.getAvaliacaoQualidade(),
					x.getAvaliacaoPreco(), x.getAvaliacaoPontualidade(), x.getCep(), x.getLougradouro(), x.getFoto()));
		}

		for (ContratanteProfissional x : listlAux2ContraProf) {
			listDTO.add(new ProfissionalDTO(x.getId(), x.getNome(), x.getEmail(), x.getResumoProfissional(),
					x.getProfissao(), x.getTelefone(), x.getCelular(), x.getCompetencias(), x.getAvaliacaoQualidade(),
					x.getAvaliacaoPreco(), x.getAvaliacaoPontualidade(), x.getAvaliacaoCordialidade(),
					x.getAvaliacaoCompromisso(), x.getCep(), x.getLougradouro(), x.getFoto()));
		}

		return listDTO;

	}

}
