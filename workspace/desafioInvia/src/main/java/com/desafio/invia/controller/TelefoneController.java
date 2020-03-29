package com.desafio.invia.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.desafio.invia.basicas.Telefone;
import com.desafio.invia.dao.TelefoneDAO;

@Stateless
public class TelefoneController {

	@Inject
	private TelefoneDAO sistemaDAO;
	
	public Telefone salvar(Telefone telefone) {
		return this.sistemaDAO.salvar(telefone);
	}

	public Telefone atualizar(Telefone telefone) {
		return this.sistemaDAO.atualizar(telefone);
	}

	public void remover(Long id) {
		Telefone telefone = getTelefone(id);
		this.sistemaDAO.remover(telefone);
	}

	public List<Telefone> getTelefones() {
		return this.sistemaDAO.getTelefones();
	}

	public Telefone getTelefone(Long id) {
		return this.sistemaDAO.getTelefone(id);
	}
}
