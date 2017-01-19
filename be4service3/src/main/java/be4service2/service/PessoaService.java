package be4service2.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import be4service2.daos.PessoaDaoImpl;
import be4service2.models.AvaliacaoContratante;
import be4service2.models.AvaliacaoProfissional;
import be4service2.models.Contratante;
import be4service2.models.ContratanteProfissional;
import be4service2.models.Profissional;
import be4service2.models.Proposta;
import be4service2.models.Servico;

public class PessoaService {

	@Autowired
    private PessoaDaoImpl pessoaDao;
	
}