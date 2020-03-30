<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Usuarios</title>
</head>
<body>
	<div id="formLista">
		<form>
			<div align="center">
				<h1>Gerenciamento de usuarios</h1>
				<h2>
					<button type="submit" onclick="novo()">Novo Usuario</button> &nbsp;&nbsp;&nbsp; 
					<button type="submit" onclick="listar()">Lista de Usuarios</button>
				</h2>
			</div>
			<div align="center">
				<table border="1" cellpadding="5">
					<caption>
						<h2>Lista de Usuarios</h2>
					</caption>
					<tr>
						<th>CPF</th>
						<th>Nome</th>
						<th>Cargo</th>
						<th>Orgao</th>
						<th>Email</th>
						<th>Senha</th>
						<th>Sistemas</th>
						<th>Ações</th>
					</tr>
					<c:forEach var="u" items="${usuarios}">
						<tr>
							<td>
								<c:out value="${u.cpf}" />
							</td>
							<td><c:out value="${u.nome}" /></td>
							<td><c:out value="${u.cargo.descricao}" /></td>
							<td><c:out value="${u.orgao.nome}" /></td>
							<td><c:out value="${u.email}" /></td>
							<td><c:out value="${u.senha}" /></td>
							<td>
						        <c:forEach var="i" items= "${u.sistemas}">
						            <c:out value="${i.nome}"> &nbsp;&nbsp;</c:out>
						        </c:forEach>
					        </td>
							<td>
								<button type="submit" id="editar" name="editar" onclick="editar(${u.cpf.toString()})">Editar</button>&nbsp;&nbsp;&nbsp;&nbsp; 
								<button type="submit" id="deletar" name="deletar" onclick="apagar(${u.cpf.toString()})">Apagar</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	
	</div>
</body>
</html>