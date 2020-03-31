package com.desafio.invia.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.desafio.invia.basicas.Usuario;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class UsuarioDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public Usuario getUsuario(String cpf) {
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Usuario usuario = manager.find(Usuario.class, cpf);
		
		manager.close();
		
		return usuario;
	}

	public List<Usuario> getUsuarios() {
		
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("SELECT u FROM Usuario u");

		List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		transaction.commit();
		manager.close();
		return usuarios;
	}

	public Usuario login(String email, String senha) {
		
		EntityManager manager = getEntityManager();
		if (!manager.isOpen()) {
			manager = getEntityManagerFactory().createEntityManager();
		}
		
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Query query = manager.createQuery("SELECT u FROM Usuario u where u.email = :email and u.senha = :senha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		Usuario usuarioDoLogin = query.getResultList().isEmpty() ?  null : 
			Usuario.class.cast(query.getResultList().get(0));

		transaction.commit();
		manager.close();
		return usuarioDoLogin;
	}  
}
