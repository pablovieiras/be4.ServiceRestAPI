package be4service2.service;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be4service2.daos.ContratanteDao;
import be4service2.daos.ContratanteProfissionalDaoImpl;
import be4service2.daos.PessoaDaoImpl;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Pessoa;

@Service
@Transactional
public class ContratanteServiceImpl implements ContratanteService {
	@Autowired
	private ContratanteDao contratanteDao;
	@Autowired
	private PessoaDaoImpl pessoaDao;
	@Autowired
	private ContratanteProfissionalDaoImpl contratanteProfissionalDao;

	@Override
	public void save(Contratante contratante) throws ServletException {
		contratante.setTipo("contratante");
		Pessoa p = pessoaDao.verificaEmail(contratante.getEmail());
		if (p != null) {
			throw new ServletException("email ja cadastrado");
		}
		contratanteDao.save(contratante);
	}

	@Override

	public Contratante findById(Integer id) {

		return contratanteDao.findById(id);
	}

	@Override
	public void remove(Contratante contratante) {
		contratanteDao.remove(contratante);

	}

	@Override
	public void update(Contratante contratante) {
		contratanteDao.update(contratante);

	}

	@Override
	public void tornarProfissional(ContratanteProfissional contratante) {
		// c = contratanteDao.findById(contratanteProfissional.getId());
		if(contratante.getNumeroAvaliacoesProfissional()==0||contratante.getNumeroAvaliacoesProfissional()==null){
			contratante.setNumeroAvaliacoesProfissional(0);
			contratante.setAvaliacaoQualidade(0.0);
			contratante.setAvaliacaoPreco(0.0);
			contratante.setAvaliacaoPontualidade(0.0);
		}
		contratanteDao.tornarProfissional(contratante);
		
		System.out.println(contratante.toString());
				

	}

	@Override
	public void desativarConta(Contratante contratante) {
		contratanteDao.desativarConta(contratante);

	}
}
