package com.desafio.invia.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.desafio.invia.basicas.Usuario;
import com.desafio.invia.dao.UsuarioDAO;
import com.desafio.invia.util.Util;

@Stateless
public class UsuarioController {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Usuario salvar(Usuario usuario, String acao) throws Exception {
		
		validarDados(usuario, acao);
		
		return this.usuarioDAO.salvar(usuario);
	}

	private void validarDados(Usuario usuario, String acao) throws Exception {
		if (usuario != null && usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
			throw new Exception("Por favor preencha o CPF.");
		}
		
		if (usuario != null && !Util.isCPF(usuario.getCpf())) {
			throw new Exception("Por favor informe um CPF valido.");
		}
		
		if (acao != null && acao.equalsIgnoreCase("incluir")) {
			Usuario usuarioConsultado = getUsuario(usuario.getCpf());
			if (usuarioConsultado != null &&  usuarioConsultado.getCpf().equalsIgnoreCase(usuario.getCpf())) {
				throw new Exception("Nao e permitido cadastrar mais de um usuario com o mesmo CPF.");
			}
		}
	}

	public Usuario atualizar(Usuario usuario) throws Exception {
		
		validarDados(usuario, null);
		
		return this.usuarioDAO.atualizar(usuario);
	}

	public void remover(String cpf) throws Exception {
		
		if (cpf == null || cpf.isEmpty()) {
			throw new Exception("Por favor informe o CPF.");
		}
		
		Usuario usuario = getUsuario(cpf);

		if (usuario == null) {
			throw new Exception("Usuario inexistente, favor informe um usuario valido.");
		}
		if (usuario != null) {
			this.usuarioDAO.remover(usuario);
		}
		
	}

	public List<Usuario> getUsuarios() {
		return this.usuarioDAO.getUsuarios();
	}

	public Usuario getUsuario(String cpf) {
		return this.usuarioDAO.getUsuario(cpf);
	}

	public Usuario login(String email, String senha) throws Exception {
		
		if (email == null || senha == null && email.isEmpty() || senha.isEmpty()) {
			throw new Exception("Email ou senha nulo por favor preencher os campos corretamente.");
		}
		
		Usuario usuario = this.usuarioDAO.login(email, senha);
		
		if (usuario == null) {
			throw new Exception("Usuario ou senha invalida.");
		}
		
		return usuario;
	}
	
	public void remover(Usuario usuario){
		this.usuarioDAO.remover(usuario);
	}
}
