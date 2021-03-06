package com.desafio.invia.view;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafio.invia.basicas.Usuario;
import com.desafio.invia.controller.UsuarioController;

@WebServlet("/RemoverUsuarioViewServelet")
public class RemoverUsuarioViewServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioController usuarioController;
	
    public RemoverUsuarioViewServelet() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String cpf = request.getParameter("cpf");
		try {
			usuarioController.remover(cpf);
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
