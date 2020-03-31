<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<meta charset="utf-8"> 
<script type="text/javascript" src="usuario.js"></script>
<title>Lista de Usuarios</title>
</head>
<body>
	<div id="formLista">
		<form>
			<c:if test="${not empty erro}">
				<pre>${erro}</pre>
			</c:if>
			<div align="center">
				<h1>Gerenciamento de usuarios</h1>
				<h2>
					<button type="submit" onclick="novo()">Novo</button>&nbsp;&nbsp;&nbsp;&nbsp; 
					<button type="submit" onclick="loggof()">Loggof</button>
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
					<c:forEach var="u" items="${usuarios}" varStatus="o">
						<tr>
							<td>
								<span name="cpf">${u.cpf}</span>
							</td>
							<td id="nome" name="nome"><c:out value="${u.nome}" /></td>
							<td id="descricao" name="descricao"><c:out value="${u.cargo.descricao}" /></td>
							<td id="orgao.nome" name="orgao.nome"><c:out value="${u.orgao.nome}" /></td>
							<td id="email" name="email"><c:out value="${u.email}" /></td>
							<td id="senha" name="senha"><c:out value="${u.senha}" /></td>
							<td>
						        <c:forEach var="i" items= "${u.sistemas}">
						            <c:out value="${i.nome}"> &nbsp;&nbsp;</c:out>
						        </c:forEach>
					        </td>
							<td>
								<button type="submit" onclick="alterar(${u.cpf})">Editar</button>&nbsp;&nbsp;&nbsp;&nbsp; 
								<button type="submit" onclick="apagar(${u.cpf})">Apagar</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
<!-- 
<html><!-- <input type="submit" name="delete" formmethod="DELETE" formaction="request.getContextPath()/UsuarioViewServelet?cpf=${u.cpf}/>" value="Apagar"/>
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
</html> -->

<!-- <div id="formLista">
		<form>
			<div align="center">
				<h1>Gerenciamento de usuarios</h1>
				<h2>
					<input type="submit" name="novo" formmethod="get" formaction="request.getContextPath()%>/UsuarioViewServelet?listar=${false}" value="Novo Usuario"/>&nbsp;&nbsp;&nbsp; 
					<input type="submit" name="listar" formmethod="get" formaction="request.getContextPath()%>/UsuarioViewServelet?listar=${true}" value="Lista de Usuarios">
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
					<c:forEach var="u" items="${usuarios}" varStatus="e">
						<tr>
							<input type="hidden" id="posicao" name="posicao" value="${e.index}"/>
							
							<td>
								<span id="cpf" name="cpf"><c:out value="${u.cpf}" /></span>
							</td>
							<td>
								<span id="nome" name="nome"><c:out value="${u.nome}" /></span>
							</td>
							
							<td>
								<span id="descricao" name="descricao"><c:out value="${u.cargo.descricao}" /></span>
							</td>
							
							<td>
								<span id="nomeOrgao" name="nomeOrgao"><c:out value="${u.orgao.nome}" /></span>
							</td>
							
							<td>
								<span id="email" name="email"><c:out value="${u.email}" /></span>
							</td>
							
							<td>
								<span id="senha" name="senha"><c:out value="${u.senha}" /></span>
							</td>
							
							<td>
						        <c:forEach var="i" items= "${u.sistemas}">
						            <span id="nomeSistema" name="nomeSistema"><c:out value="${i.nome}"> &nbsp;&nbsp;</c:out></span>
						        </c:forEach>
					        </td>
							<td>
								<input type="submit" name="editar" formmethod="PUT" formaction="request.getContextPath()%>/UsuarioViewServelet?cpf=${u.cpf}" value="Editar"/>&nbsp;&nbsp;&nbsp;&nbsp; 
								<input type="submit" name="editar" formmethod="DELETE" formaction="request.getContextPath()%>/UsuarioViewServelet?cpf=${u.cpf}&acao=deletar" value="Editar"/>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	
	</div> -->