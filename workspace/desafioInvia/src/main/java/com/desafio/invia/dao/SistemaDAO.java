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
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Sistema sistema = manager.find(Sistema.class, id);
		transaction.commit();
		manager.close();
		return sistema;
	}

	public List<Sistema> getSistemas() {
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("SELECT s FROM Sistema s");

		List<Sistema> orgaos = (List<Sistema>) query.getResultList();
		transaction.commit();
		manager.close();
		return orgaos;
	}

	public List<Sistema> getSistemas(String[] sistemaIds) {
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("SELECT s FROM Sistema s where s.id in (:ids)");
		query.setParameter("ids", sistemaIds);
		
		List<Sistema> orgaos = (List<Sistema>) query.getResultList();
		transaction.commit();
		manager.close();
		return orgaos;
	}  
}
