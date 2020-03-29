package com.desafio.invia.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.desafio.invia.basicas.Usuario;
import com.desafio.invia.dao.UsuarioDAO;

@Stateless
public class UsuarioController {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Usuario salvar(Usuario usuario) {
		
		if (usuario != null && usuario.getCpf().isEmpty() || usuario.getCpf() == null) {
			throw new RuntimeException("Por favor preencha o CPF.");
		}
		
		return this.usuarioDAO.salvar(usuario);
	}

	public Usuario atualizar(Usuario usuario) {
		
		if (usuario != null && usuario.getCpf().isEmpty() || usuario.getCpf() == null) {
			throw new RuntimeException("Por favor preencha o CPF.");
		}
		
		return this.usuarioDAO.atualizar(usuario);
	}

	public void remover(String cpf) {
		
		if (cpf.isEmpty() || cpf == null) {
			throw new RuntimeException("Por favor informe o CPF.");
		}
		
		Usuario usuario = getUsuario(cpf);
		this.usuarioDAO.remover(usuario);
	}

	public List<Usuario> getUsuarios() {
		return this.usuarioDAO.getUsuarios();
	}

	public Usuario getUsuario(String cpf) {
		return this.usuarioDAO.getUsuario(cpf);
	}
}
