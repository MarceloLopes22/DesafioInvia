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

@SuppressWarnings("deprecation")
@WebServlet("/LoginViewServlet")
public class LoginViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioController controller;
       
    public LoginViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setAttribute("erro", "");
		 request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		try {
			Usuario usuario = controller.login(email, senha);
			
			if (usuario != null) {
				request.getSession().putValue("usuarioLogado", usuario);
				List<Usuario> usuarios = controller.getUsuarios();
				request.setAttribute("usuarios", usuarios);
				response.setStatus(HttpServletResponse.SC_OK);
				request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
			} 
		} catch (Exception e) {
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			//response.getWriter().print(e.getMessage());
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("usuarioLogado", null);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
