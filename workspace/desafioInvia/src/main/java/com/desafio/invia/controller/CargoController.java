package com.desafio.invia.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.desafio.invia.basicas.Cargo;
import com.desafio.invia.dao.CargoDAO;

@Stateless
public class CargoController {

	@Inject
	private CargoDAO cargoDAO;
	
	public Cargo salvar(Cargo cargo) {
		return this.cargoDAO.salvar(cargo);
	}

	public Cargo atualizar(Cargo cargo) {
		return this.cargoDAO.atualizar(cargo);
	}

	public void remover(Long id) {
		Cargo cargo = getCargo(id);
		this.cargoDAO.remover(cargo, id);
	}

	public List<Cargo> getCargos() {
		return this.cargoDAO.getCargos();
	}

	public Cargo getCargo(Long id) {
		return this.cargoDAO.getCargo(id);
	}
}
