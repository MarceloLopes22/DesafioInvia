package com.desafio.invia.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.desafio.invia.basicas.Entidade;

public class DAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String PERSISTENCE_NAME_UNIT = "desafioInvia";

	private EntityManager entityManager;

	public DAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME_UNIT);
		entityManager = emf.createEntityManager();
	}
	
	private void commit() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.commit();
	}

	private void abrirTrasacao() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
	}

	public <T extends Entidade> T salvar(T entity) {
		abrirTrasacao();
		entityManager.persist(entity);
		entityManager.flush();
		commit();
		return entity;
	}

	public <T extends Entidade> T atualizar(T entity) {
		abrirTrasacao();
		entityManager.merge(entity);
		entityManager.flush();
		commit();
		return entity;
	}

	public <T extends Entidade> void remover(T entity) {
		abrirTrasacao();
		entityManager.remove(entity);
		entityManager.flush();
		commit();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}