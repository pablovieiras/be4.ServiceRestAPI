package be4service2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be4service2.daos.ProfissionalDao;
import be4service2.models.Profissional;

@Service
@Transactional
public class ProfissionalServiceImpl implements ProfissionalService {
	@Autowired
	private ProfissionalDao profissionalDao;

	@Override
	public List<Profissional> all() {

		return profissionalDao.all();
	}

	@Override
	public void save(Profissional profissional) {
		profissional.setTipo("profissional");
		profissionalDao.save(profissional);

	}

	@Override
	public Profissional findById(Integer id) {

		return profissionalDao.findById(id);
	}

	@Override
	public void remove(Profissional profissional) {
		profissionalDao.remove(profissional);

	}

	@Override
	public void update(Profissional profissional) {
		profissionalDao.update(profissional);

	}

	public void tornarContratante(Integer id) {
		profissionalDao.tornarContratante(id);
	}

	@Override
	public void desativarConta(Profissional profissional) {
		profissionalDao.desativarConta(profissional);
		
	}

}
