package be4service2.daos;

import java.util.List;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;

public interface ContratanteProfissionalDao {

	List<ContratanteProfissional> all();

	void save(ContratanteProfissional contratanteProfissional);

	ContratanteProfissional findById(Integer id);

	void remove(ContratanteProfissional contratanteProfissional);

	void update(ContratanteProfissional contratanteProfissional);
	
	void deixarDeSerProfissional(ContratanteProfissional contratante);

}