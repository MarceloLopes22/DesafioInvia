package com.desafio.invia.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.desafio.invia.basicas.Cargo;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class CargoDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public Cargo getCargo(Long id) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Cargo cargo = manager.find(Cargo.class, id);
		return cargo;
	}
	
	public List<Cargo> getCargos() {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		 Query query = manager.createQuery("SELECT c FROM Cargo c");
		
		List<Cargo> cargos = (List<Cargo>) query.getResultList();
		return cargos;
	} 
}