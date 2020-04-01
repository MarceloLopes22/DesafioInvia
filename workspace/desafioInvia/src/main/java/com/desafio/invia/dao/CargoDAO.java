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
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Cargo cargo = manager.find(Cargo.class, id);
		transaction.commit();
		manager.close();
		
		return cargo;
	}
	
	public List<Cargo> getCargos() {
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query createNamedQuery = manager.createNativeQuery("select * from cargo", Cargo.class);
		List<Cargo> cargos = (List<Cargo>) createNamedQuery.getResultList();
		transaction.commit();
		manager.close();
		return cargos;
	} 
}
