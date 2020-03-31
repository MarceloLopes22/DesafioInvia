<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Criar/Alterar Usuario</title>
<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<meta charset="utf-8"> 
<script type="text/javascript" src="usuario.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

$(window).on('load', function() {
	$(document).ready(function() {
		var cpf = ${usuario.cpf};
		if(cpf != null){
			var selecionados = ${selecionados};
			var sistemas = ${allSistemas};//Deverá ser carregada do banco de dados
			console.log(selecionados);
			console.log(sistemas);

			$.each(sistemas,function(i, value){
				$.each(selecionados,function(j, value2){
					if(value.idSistema == value2.idSistema) {
						$('#sistemas option[value=' + value2.idSistema + ']').attr('selected', true);
					}
				});
			});
		}
	});
});
</script> -->
</head>
<body>
	<div align="center" id="formUsuario">
		<c:if test="${usuario.cpf != null}">
			<form action="atualizar" method="post">
		</c:if>
		<c:if test="${usuario.cpf == null}">
			<form action="inserir" method="post">
		</c:if>
		<div align="center">
			<h1>Gerenciamento de usuarios</h1>
			<h2>
				<button type="submit" onclick="listar()">Lista</button>&nbsp;&nbsp;&nbsp;&nbsp; 
				<button type="submit" onclick="loggof()">Loggof</button>
			</h2>
		</div>
			<table border="1" cellpadding="5">
				<caption>
					<h2>
						<c:if test="${usuario.cpf != null}">
	                        Alterar Usuario
	                    </c:if>
						<c:if test="${usuario.cpf == null}">
	                        Inserir Usuario
	                    </c:if>
					</h2>
				</caption>
				<c:if test="${usuario.cpf != null}">
					<input type="hidden" name="cpf" id="cpf" value="<c:out value='${usuario.cpf}' />" />
				</c:if>
				<tr>
					<th>CPF:</th>
					<td>
						<input type="text" name="cpf" id="cpf" size="45" value="<c:out value='${usuario.cpf}' />" />
						<c:if test="${not empty erro}">
							<pre>${erro}</pre>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>Nome:</th>
					<td>
						<input type="text" name="nome" id="nome" size="45" value="<c:out value='${usuario.nome}' />" />
					</td>
				</tr>
				<tr>
					<th>Email:</th>
					<td>
						<input type="text" name="email" id="email" size="45" value="<c:out value='${usuario.email}' />" />
					</td>
				</tr>
				<tr>
					<th>Senha:</th>
					<td>
						<input type="text" name="senha" id="senha" size="45" value="<c:out value='${usuario.senha}' />" />
					</td>
				</tr>
				<tr>
					<th>Cargo:</th>
					<c:choose>
						<c:when test="${usuario.cpf == null}">
							<td>
								<select name="cargo" id="cargo">
									<option value="0">Selecione</option>
								    <c:forEach items="${cargos}" var="c">
								       <option value="${c.id}">${c.descricao}</option>
								    </c:forEach>
								</select>
							</td>
						</c:when>
						<c:when test="${usuario.cpf != null}">
							<td>
								<select name="cargo" id="cargo">
									<option value="0">Selecione</option>
								    <c:forEach items="${cargos}" var="c">
								       <option value="${c.id}" ${c.id == usuario.cargo.idCargo ? 'selected' : ''}>${c.descricao}</option>
								    </c:forEach>
								</select>
							</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<th>Orgão:</th>
					<c:choose>
						<c:when test="${usuario.cpf == null}">
							<td>
								<select name="orgao" id="orgao">
									<option value="0">Selecione</option>
								    <c:forEach items="${orgaos}" var="c">
								       <option value="${c.id}">${c.nome}</option>
								    </c:forEach>
								</select>
							</td>
						</c:when>
						<c:when test="${usuario.cpf != null}">
							<td>
								<select name="orgao" id="orgao">
									<option value="0">Selecione</option>
								    <c:forEach items="${orgaos}" var="c">
								       <option value="${c.id}" ${c.id == usuario.orgao.idOrgao ? 'selected' : ''}>${c.nome}</option>
								    </c:forEach>
								</select>
							</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<th>Sistema:</th>
					<c:choose>
						<c:when test="${usuario.cpf == null}">
							<td>
								<select id="sistemas" name="sistemas" multiple="multiple" style="width: 200px;">
									<option value="0">Selecione</option>
								    <c:forEach items="${sistemas}" var="c">
								       <option value="${c.id}">${c.nome}</option>
								    </c:forEach>
								</select>
							</td>
						</c:when>
						<c:when test="${usuario.cpf != null}">
							<td>
								<select id="sistemas" name="sistemas" multiple="multiple" style="width: 200px;">
									<option value="0">Selecione</option>
								    <c:forEach items="${sistemas}" var="c">
								       <option value="${c.id}" ${c.id == usuario.orgao.idOrgao ? 'selected' : ''}>${c.nome}</option>
								    </c:forEach>
								</select>
							</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<c:if test="${usuario.cpf == null}">
							<button name="inserir" type="submit" onclick="incluir()">inserir</button>
						</c:if>
						<c:if test="${usuario.cpf != null}">
							<button name="atualizar" type="submit" onclick="alterar(${usuario.cpf})">atualizar</button>
						</c:if>
						<input type="reset" value="Limpar campos" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>