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
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Usuario usuario = manager.find(Usuario.class, cpf);
		return usuario;
	}

	public List<Usuario> getUsuarios() {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("SELECT u FROM Usuario u");

		List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		return usuarios;
	}  
}
