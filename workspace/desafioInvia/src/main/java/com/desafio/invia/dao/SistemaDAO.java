package com.desafio.invia.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.desafio.invia.basicas.Sistema;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class SistemaDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public Sistema getSistema(Long id) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Sistema sistema = manager.find(Sistema.class, id);
		manager.close();
		return sistema;
	}

	public List<Sistema> getSistemas() {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("SELECT s FROM Sistema s");

		List<Sistema> orgaos = (List<Sistema>) query.getResultList();
		manager.close();
		return orgaos;
	}  
}
