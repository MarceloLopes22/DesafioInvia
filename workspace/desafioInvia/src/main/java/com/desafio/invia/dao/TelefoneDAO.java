package com.desafio.invia.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.desafio.invia.basicas.Telefone;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class TelefoneDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public Telefone getTelefone(Long id) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Telefone telefone = manager.find(Telefone.class, id);
		manager.close();
		return telefone;
	}

	public List<Telefone> getTelefones() {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("SELECT t FROM Telefone t");

		List<Telefone> telefones = (List<Telefone>) query.getResultList();
		manager.close();
		return telefones;
	}  
}
