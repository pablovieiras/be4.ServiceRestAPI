package be4service2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ExceptionTypeFilter;

import be4service2.daos.ContratanteDao;
import be4service2.daos.ContratanteProfissionalDao;
import be4service2.daos.PessoaDaoImpl;
import be4service2.daos.ProfissionalDao;
import be4service2.daos.ServicoDao;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Pessoa;
import be4service2.models.Profissional;
import be4service2.models.Servico;


@Service
@Transactional
public class ContratanteServiceImpl implements ContratanteService {
	 @Autowired
	   private ContratanteDao contratanteDao;
	 @Autowired
	   private ContratanteProfissionalDao contratanteProfissionalDao;
	 @Autowired
	 private ServicoDao servicoDao;
	
	 public List<Contratante> all() {
		
		return contratanteDao.all();
	}

	@Override
	public void save(Contratante contratante) {
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
	public void tornarProfissional(Integer id,ContratanteProfissional contratante) {
		Contratante c=contratanteDao.findById(id);
		if(c.getCpf()!=null){
			contratante.setCpf(c.getCpf());
		}
		
		System.out.println(contratante);
		
		
		contratanteDao.tornarProfissional(contratante);
		
		
		//
		//contratante.setNome(c.getNome());
		//contratante.setAvaliacaoContratante(c.getAvaliacaoContratante());
		
		//contratante.setAvaliacaoProfissional(0.0);
		//contratanteDao.update(c);
		
	}

/*	@Override
	public void criarServico(Contratante contratante,Servico servico) {
		servico.setContratante(contratante);
		contratante.getServicosContratados().add(servico);
		servico.setStatus("Aberto");
		servicoDao.save(servico);
	}

	@Override
	public void selecionarProfissional(Profissional profissional, Servico servico) {
		if(profissional!=null){
			servico.setProfissional(profissional);
			profissional.getServicosPrestados().add(servico);
			servico.setStatus("Aguardando Aceitação do Profissional");
			servicoDao.update(servico);
		}else{
			try {
				throw new Exception("Profissional não cadastrado.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}*/



}
