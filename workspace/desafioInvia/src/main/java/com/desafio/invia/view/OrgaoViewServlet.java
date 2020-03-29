package com.desafio.invia.view;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafio.invia.basicas.Orgao;
import com.desafio.invia.controller.OrgaoController;

@WebServlet("/OrgaoViewServlet")
public class OrgaoViewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrgaoController orgaoController;
	
    public OrgaoViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Orgao orgao = new Orgao();
		orgao.setNome("Sefaz-JP");
		orgaoController.salvar(orgao);
		System.out.println("Deu certo!");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
