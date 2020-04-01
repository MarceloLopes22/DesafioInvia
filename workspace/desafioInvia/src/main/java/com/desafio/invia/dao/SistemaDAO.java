package com.desafio.invia.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

	public List<Sistema> getSistemas(ArrayList<BigDecimal> idsSistemas) {
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String ids = idsSistemas.toString().replace("[", "(").replace("]", ")");
		Query nativeQuery = manager.createNativeQuery("SELECT * FROM sistema s where s.id in " + ids, Sistema.class);
		List<Sistema> orgaos = (List<Sistema>) nativeQuery.getResultList();
		transaction.commit();
		manager.close();
		return orgaos;
	}  
}
