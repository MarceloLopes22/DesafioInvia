package com.desafio.invia.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.desafio.invia.basicas.Orgao;
import com.desafio.invia.dao.OrgaoDAO;

@Stateless
public class OrgaoController {

	@Inject
	private OrgaoDAO orgaoDAO;
	
	public Orgao salvar(Orgao orgao) {
		return this.orgaoDAO.salvar(orgao);
	}

	public Orgao atualizar(Orgao orgao) {
		return this.orgaoDAO.atualizar(orgao);
	}

	public void remover(Long id) {
		Orgao orgao = getOrgao(id);
		this.orgaoDAO.remover(orgao);
	}

	public List<Orgao> getOrgaos() {
		return this.orgaoDAO.getOrgaos();
	}

	public Orgao getOrgao(Long id) {
		return this.orgaoDAO.getOrgao(id);
	}
}
