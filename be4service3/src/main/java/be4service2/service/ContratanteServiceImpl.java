package be4service2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import be4service2.daos.ContratanteDao;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;

@Service
@Transactional
public class ContratanteServiceImpl implements ContratanteService {
	@Autowired
	private ContratanteDao contratanteDao;
	@Autowired
	public List<Contratante> all() {

		return contratanteDao.all();
	}

	@Override
	public void save(Contratante contratante) {
		contratante.setTipo("contratante");
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
	/*	if(c.getCpf()!=null){
			contratante.setCpf(c.getCpf());
		}
		if(c.getBairro()!=null){
			contratante.setCpf(c.getBairro());
		}*/
		System.out.println(contratante);

		contratanteDao.tornarProfissional(contratante);

	}
}
