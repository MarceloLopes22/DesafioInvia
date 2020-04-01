package com.desafio.invia.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafio.invia.basicas.Cargo;
import com.desafio.invia.basicas.Orgao;
import com.desafio.invia.basicas.Sistema;
import com.desafio.invia.basicas.Usuario;
import com.desafio.invia.controller.CargoController;
import com.desafio.invia.controller.OrgaoController;
import com.desafio.invia.controller.SistemaController;
import com.desafio.invia.controller.UsuarioController;

@WebServlet("/UsuarioViewServelet")
public class UsuarioViewServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioController usuarioController;
	
	@Inject
	private CargoController cargoController;
	
	@Inject
	private OrgaoController orgaoController;

	@Inject
	private SistemaController sistemaController;
       
    public UsuarioViewServelet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String acao = request.getParameter("acao");
    	String cpf = request.getParameter("cpf");
    	
    	if (acao != null && !acao.isEmpty()) {
    		
    		if (acao.equalsIgnoreCase("acessarPagIncluir")) {
    			carregarListas(request);
    			request.setAttribute("usuario", new Usuario());
    			request.getRequestDispatcher("novoUsuario.jsp").forward(request, response);
			
    		} else if (acao.equalsIgnoreCase("acessarPagAlterar") && !cpf.isEmpty()) {
    			carregarListas(request);
    			Usuario usuario = usuarioController.getUsuario(cpf);
    			request.setAttribute("usuario", usuario);
    			request.getRequestDispatcher("novoUsuario.jsp").forward(request, response);
    		
			} else if(acao.equalsIgnoreCase("acessarPagLista")) {
    			List<Usuario> usuarios = usuarioController.getUsuarios();
    			request.setAttribute("usuarios", usuarios);
    			request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
			} else if (acao.equalsIgnoreCase("deletar")) {
				this.doDelete(request, response);
			}
		}
	}
    
	private void carregarListas(HttpServletRequest request) {
		
		List<Cargo> cargos = cargoController.getCargos();
		List<Orgao> orgaos = orgaoController.getOrgaos();
		List<Sistema> sistemas = sistemaController.getSistemas();
		
		request.setAttribute("cargos", cargos);
		request.setAttribute("orgaos", orgaos);
		request.setAttribute("sistemas", sistemas);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		Usuario usuario = preencherDadosUsuario(request);
		
		try {
			
			if (acao != null && !acao.equals("") && acao.equals("incluir")) {
				usuarioController.salvar(usuario, acao);
			} else if (acao != null && !acao.equals("") && acao.equals("atualizar")){
				usuarioController.atualizar(usuario);
			}
			
			List<Usuario> usuarios = usuarioController.getUsuarios();
			request.setAttribute("usuarios", usuarios);
			response.setStatus(HttpServletResponse.SC_OK);
			request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
		} catch (Exception e) {
			carregarListas(request);
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/novoUsuario.jsp").forward(request, response);
		}
	}

	private Usuario preencherDadosUsuario(HttpServletRequest request) {
		
		String cpf = String.class.cast(request.getParameter("cpf"));
		String nome = String.class.cast(request.getParameter("nome"));
		String cargoId = String.valueOf(request.getParameter("cargo"));
		String orgaoId = String.valueOf(request.getParameter("orgao"));
		String email = String.class.cast(request.getParameter("email"));
		String senha = String.class.cast(request.getParameter("senha"));
		String[] sistemaIds = request.getParameterValues("sistemas[]");
		ArrayList<BigDecimal> idsSistemas = new ArrayList<BigDecimal>();

		if (sistemaIds != null && sistemaIds.length > 0) {
			for (String id : sistemaIds) {
				idsSistemas.add(new BigDecimal(id));
			}
		}
		
		Usuario usuario = new Usuario();
		if (cpf != null && !cpf.isEmpty()) {
			usuario.setCpf(cpf.replaceAll("[.-]", ""));
		}
		if (cargoId != null && Long.valueOf(cargoId) > 0) {
			usuario.setCargo(cargoController.getCargo(Long.valueOf(cargoId)));
		}
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		if (orgaoId != null && Long.valueOf(orgaoId) > 0) {
			usuario.setOrgao(orgaoController.getOrgao(Long.valueOf(orgaoId)));
		}
		if (idsSistemas != null && idsSistemas.size() > 0) {
			usuario.setSistemas(sistemaController.getSistemas(idsSistemas));
		}
		return usuario;
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = preencherDadosUsuario(request);
		
		try {
			usuarioController.atualizar(usuario);
			List<Usuario> usuarios = usuarioController.getUsuarios();
			request.setAttribute("usuarios", usuarios);
			response.setStatus(HttpServletResponse.SC_OK);
			request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/novoUsuario.jsp").forward(request, response);
		}
	}
}
