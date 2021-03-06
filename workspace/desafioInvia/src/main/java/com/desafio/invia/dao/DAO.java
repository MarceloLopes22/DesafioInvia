package com.desafio.invia.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.desafio.invia.basicas.Entidade;
import com.desafio.invia.util.Util;

public class DAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String PERSISTENCE_NAME_UNIT = "desafioInvia";

	private EntityManager entityManager;
	
	private EntityManagerFactory emf;

	public DAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME_UNIT);
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
		if (!getEntityManager().isOpen()) {
			this.entityManager = getEntityManagerFactory().createEntityManager();
		}
		abrirTrasacao();
		entityManager.persist(entity);
		entityManager.flush();
		commit();
		return entity;
	}

	public <T extends Entidade> T atualizar(T entity) {
		if (!getEntityManager().isOpen()) {
			this.entityManager = getEntityManagerFactory().createEntityManager();
		}
		abrirTrasacao();
		entityManager.merge(entity);
		entityManager.flush();
		commit();
		return entity;
	}

	public <T extends Entidade> void remover(T entity, Long id) {
		if (!getEntityManager().isOpen()) {
			this.entityManager = getEntityManagerFactory().createEntityManager();
		}
		abrirTrasacao();
		entityManager.remove(entityManager.getReference(Util.entidade(entity).getClass(), id));
		entityManager.flush();
		commit();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_NAME_UNIT);
	}
}
