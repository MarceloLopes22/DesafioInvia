<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script>-->
		<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<meta charset="utf-8"> 
		<script type="text/javascript" src="usuario.js"></script>
		<title>Login</title>
	</head>
	<body>
		<div align="center" id="formLogin">
			<h1>Sefaz - Login</h1>
			<form>
				<table style="with: 100%">
					<tr>
						<td>Usuario</td>
						<td><input type="text" id="email" name="email" /></td>
					</tr>
					<tr>
						<td>Senha</td>
						<td><input type="password" id="senha" name="senha" /></td>
					</tr>
				</table>
				<button onclick="login()">Logar</button>
				<input type="reset" value="Reset" />
			</form>
		</div>
	</body>
</html>