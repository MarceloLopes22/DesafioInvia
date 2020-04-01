<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Criar/Alterar Usuario</title>
<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript" src="usuario.js"></script>

</head>
<body>
	<div align="center" id="formUsuario">
		
		<form>
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
						<c:if test="${not empty usuario.cpf}">
	                        Alterar Usuario
	                    </c:if>
						<c:if test="${empty usuario.cpf}">
	                        Inserir Usuario
	                    </c:if>
					</h2>
				</caption>
				<tr>
					<th>CPF:</th>
					<td>
						<input type="text" name="cpf" id="cpf" maxlength="11" size="45"  onfocus="javascript: retirarFormatacao(this);" onblur="javascript: formatarCampo(this);" value="<c:out value="${usuario.cpf}" />" />
						<c:if test="${not empty erro}">
							<pre>${erro}</pre>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>Nome:</th>
					<td>
						<input type="text" name="nome" id="nome" size="45" value="<c:out value="${usuario.nome}" />" />
					</td>
				</tr>
				<tr>
					<th>Email:</th>
					<td>
						<input type="text" name="email" id="email" size="45" value="<c:out value="${usuario.email}" />" />
					</td>
				</tr>
				<tr>
					<th>Senha:</th>
					<td>
						<input type="password" name="senha" id="senha" size="45" value="<c:out value="${usuario.senha}" />" />
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
								       <option value="${c.id}" ${c.id == usuario.cargo.id ? 'selected' : ''}>${c.descricao}</option>
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
								       <option value="${c.id}" ${c.id == usuario.orgao.id ? 'selected' : ''}>${c.nome}</option>
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
								    	<c:forEach items="${usuario.sistemas}" var="s">
									       <option value="${c.id}" ${c.id == s.id ? 'selected' : ''}>${c.nome}</option>
								    	</c:forEach>
								    </c:forEach>
								</select>
							</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<c:if test="${usuario.cpf == null}">
							<button type="submit" onclick="incluir('incluir')">inserir</button>
						</c:if>
						<c:if test="${usuario.cpf != null}">
							<button type="submit" onclick="incluir('atualizar')">atualizar</button>
						</c:if>
						<input type="reset" value="Limpar campos" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>