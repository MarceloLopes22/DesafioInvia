package com.desafio.invia.view;

import java.io.IOException;
import java.util.List;

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
		String cpf = String.class.cast(request.getParameter("cpf"));
		String nome = String.class.cast(request.getParameter("nome"));
		Long cargoId = Long.valueOf(request.getParameter("cargo"));
		Long orgaoId = Long.valueOf(request.getParameter("orgao"));
		String email = String.class.cast(request.getParameter("email"));
		String senha = String.class.cast(request.getParameter("senha"));
		String[] sistemaIds = request.getParameterValues("valores");
		//long[] longArray = Arrays.stream(sistemaIds).mapToLong(i -> i).toArray();
		
		Usuario usuario = new Usuario();
		usuario.setCpf(cpf);
		usuario.setCargo(cargoController.getCargo(cargoId));
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setOrgao(orgaoController.getOrgao(orgaoId));
		usuario.setSistemas(sistemaController.getSistemas(sistemaIds));
		//Falta o sistema verificar como vai ficar na jsp e no java
		
		try {
			usuarioController.salvar(usuario);
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

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		try {
			usuarioController.remover(cpf);
			List<Usuario> usuarios = usuarioController.getUsuarios();
			request.setAttribute("usuarios", usuarios);
			response.setStatus(HttpServletResponse.SC_OK);
			request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
		} catch (Exception e) {
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			//response.getWriter().print(e.getMessage());
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/novoUsuario.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = String.class.cast(request.getAttribute("cpf"));
		String nome = String.class.cast(request.getAttribute("nome"));
		String cargo = String.class.cast(request.getAttribute("cargo"));
		String orgao = String.class.cast(request.getAttribute("orgao"));
		String email = String.class.cast(request.getAttribute("email"));
		String senha = String.class.cast(request.getAttribute("senha"));
		//String cpf = String.class.cast(request.getAttribute("cpf"));
		
		Usuario usuario = new Usuario();
		usuario.setCpf(cpf);
		usuario.setCargo(cargoController.getCargo(Long.valueOf(cargo)));
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setOrgao(orgaoController.getOrgao(Long.valueOf(orgao)));
		//Falta o sistema verificar como vai ficar na jsp e no java
		
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
