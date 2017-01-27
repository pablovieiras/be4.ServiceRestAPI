package be4service2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be4service2.daos.ContratanteProfissionalDao;
import be4service2.models.ContratanteProfissional;


@Service
@Transactional
public class ContratanteProfissionalServiceImpl implements ContratanteProfissionalService {
	 @Autowired
	   private ContratanteProfissionalDao contratanteProfissionalDao;
	
	@Override
	public List<ContratanteProfissional> all() {
		
		return contratanteProfissionalDao.all();
	}

	@Override
	public void save(ContratanteProfissional contratanteProfissional) {
		contratanteProfissional.setTipo("contratanteProfissional");
		 contratanteProfissionalDao.save(contratanteProfissional);
	}

	@Override
	public ContratanteProfissional findById(Integer id) {
		
		return contratanteProfissionalDao.findById(id);
	}

	@Override
	public void remove(ContratanteProfissional contratanteProfissional) {
		contratanteProfissionalDao.remove(contratanteProfissional);
		
	}

	@Override
	public void update(ContratanteProfissional contratanteProfissional) {
		contratanteProfissionalDao.update(contratanteProfissional);
		
	}

	public void deixarDeSerProfissional(ContratanteProfissional contratante){
		contratante.setTipo("contratante");
		contratanteProfissionalDao.deixarDeSerProfissional(contratante);
	}



}
