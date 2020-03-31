const URL_LOGIN = 'http://localhost:8080/desafioInvia/LoginViewServlet';
const URL_USUARIO = 'http://localhost:8080/desafioInvia/UsuarioViewServelet';
const URL_LOGGOF= 'http://localhost:8080/desafioInvia/LoggofViewServlet';

function login(){
	jQuery.ajax({
		type: "POST",
		async: false,
		url: URL_LOGIN,
		data:  {
			"email" : document.getElementById("email").value,
			"senha" : document.getElementById("senha").value
		},
		success: function (dados) { 
			var formLista = document.getElementById("formLogin");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}

function novo(){
	jQuery.ajax({
		type: "GET",
		async: false,
		url: URL_USUARIO,
		data:  {
			"acao" : "acessarPagIncluir"
		},
		success: function (dados) { 
			var formLista = document.getElementById("formLista");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}

function alterar(cpf){
	jQuery.ajax({
		type: "GET",
		async: false,
		url: URL_USUARIO,
		data:  {
			"acao": "acessarPagAlterar",
			"cpf" : cpf
		},
		success: function (dados) { 
			var formLista = document.getElementById("formLista");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}

function incluir(){
	
	var selecionados = document.getElementById("sistemas").selectedOptions
	var valores = [];
	for(i = 0; i < selecionados.length; i++){
		valores.push(selecionados[i].value);
	}
	jQuery.ajax({
		type: "POST",
		async: false,
		url: URL_USUARIO,
		data:  {
			"acao" : "incluir",
			"cpf" : document.getElementById("cpf").value,
			"nome" : document.getElementById("nome").value,
			"email" : document.getElementById("email").value,
			"senha" : document.getElementById("senha").value,
			"cargo" : document.getElementById("cargo").value,
			"orgao" : document.getElementById("orgao").value,
			valores : valores
		},
		success: function (dados) { 
			var formLista = document.getElementById("formLista");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}

function loggof(){
	jQuery.ajax({
		type: "GET",
		async: false,
		url: URL_LOGGOF,
		success: function (dados) { 
			var formLista = document.getElementById("formLista");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}

function listar(){
	jQuery.ajax({
		type: "GET",
		async: false,
		url: URL_USUARIO,
		data:  {
			"acao" : "acessarPagLista",
		},
		success: function (dados) { 
			var formLista = document.getElementById("formLista");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}

function apagar(index){
	jQuery.ajax({
		type: "DELETE",
		async: false,
		url: URL_USUARIO,
		data: {
			"cpf" : document.getElementById("cpf["+index+"]").value
		},
		success: function (dados) { 
			var formLista = document.getElementById("formLista");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}
