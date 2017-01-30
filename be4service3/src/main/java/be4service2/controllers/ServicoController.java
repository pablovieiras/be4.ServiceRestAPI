package be4service2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be4service2.models.Servico;
import be4service2.service.ServicoService;

@RestController
@RequestMapping(value = "/servico")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;

	// lista todos os serviços em aberto
	@RequestMapping(value = "/listarAbertos", method = RequestMethod.GET)
	public List<Servico> listarAbertos() {
		return servicoService.listarAbertos();
	}
}
