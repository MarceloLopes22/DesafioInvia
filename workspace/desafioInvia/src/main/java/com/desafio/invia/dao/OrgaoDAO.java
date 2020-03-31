package com.desafio.invia.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.desafio.invia.basicas.Orgao;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class OrgaoDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public Orgao getOrgao(Long id) {
		
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Orgao orgao = manager.find(Orgao.class, id);
		transaction.commit();
		manager.close();
		return orgao;
	}
	
	public List<Orgao> getOrgaos() {
		
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		 Query query = manager.createQuery("SELECT o FROM Orgao o");
		
		List<Orgao> orgaos = (List<Orgao>) query.getResultList();
		transaction.commit();
		manager.close();
		return orgaos;
	}
}
