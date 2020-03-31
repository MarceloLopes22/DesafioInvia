package com.desafio.invia.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoggofViewServlet")
public class LoggofViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoggofViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			request.getSession().setAttribute("usuarioLogado", null);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
