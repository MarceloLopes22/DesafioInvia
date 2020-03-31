package com.desafio.invia.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.desafio.invia.basicas.Sistema;
import com.desafio.invia.dao.SistemaDAO;

@Stateless
public class SistemaController {

	@Inject
	private SistemaDAO sistemaDAO;
	
	public Sistema salvar(Sistema sistema) {
		return this.sistemaDAO.salvar(sistema);
	}

	public Sistema atualizar(Sistema sistema) {
		return this.sistemaDAO.atualizar(sistema);
	}

	public void remover(Long id) {
		Sistema sistema = getSistema(id);
		this.sistemaDAO.remover(sistema, id);
	}

	public List<Sistema> getSistemas() {
		return this.sistemaDAO.getSistemas();
	}

	public Sistema getSistema(Long id) {
		return this.sistemaDAO.getSistema(id);
	}

	public List<Sistema> getSistemas(String[] sistemaIds) {
		return this.sistemaDAO.getSistemas(sistemaIds);
	}
}
