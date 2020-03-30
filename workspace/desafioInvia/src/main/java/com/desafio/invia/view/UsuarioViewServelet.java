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

@WebServlet("/UsuarioViewServelet")
public class UsuarioViewServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioController controller;
       
    public UsuarioViewServelet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Usuario> usuarios = controller.getUsuarios();
		request.setAttribute("usuarios", usuarios);
	}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpfApagado");
		try {
			controller.remover(cpf);
			List<Usuario> usuarios = controller.getUsuarios();
			request.setAttribute("usuarios", usuarios);
			response.setStatus(HttpServletResponse.SC_OK);
			request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
	}
}
