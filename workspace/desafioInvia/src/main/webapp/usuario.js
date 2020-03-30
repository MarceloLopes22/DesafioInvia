const URL_LOGIN = 'http://localhost:8080/desafioInvia/LoginViewServlet';
const URL_USUARIO = 'http://localhost:8080/desafioInvia/UsuarioViewServelet';

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

function listar(){
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

function editar(cpf){
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
//document.getElementById("cpf["+index+"]").value
function apagar(cpf){
	jQuery.ajax({
		type: "DELETE",
		async: false,
		url: URL_USUARIO,
		data:  {
			"cpfApagado" : cpf
		},
		success: function (dados) { 
			//var formLista = document.getElementById("formLogin");
			var formLista = document.getElementById("formLista");
			formLista.innerHTML = dados;
		},
		error: function (err){ 
			alert(err.responseText);
		}
	});
}
/*
function xpto1(){
	var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;
	var retorno = $.post("http://localhost:8080/desafioInvia/LoginViewServlet", 
			{ email: email, senha: senha});
	
	retorno.fail(function(error) {
	    console.log(error);
	 });
	
	retorno.done(function(data) {
		 //result.innerHTML = data;
		 console.log(data);
		});
}
function xpto2() {
	jQuery.ajax({
		type : "POST",
		url : "http://localhost:8080/desafioInvia/LoginViewServlet",
		async : false,
		data : {
			"email" : document.getElementById("email").value,
			"senha" : document.getElementById("senha").value
		},
		success : function(dados) {
			if (dados) {
				alert(dados);
			}
		}
	});
}

function xpto(){
	//Creating a new XMLHttpRequest object
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
    }
    //Create a asynchronous GET request
    xmlhttp.open("POST", 'http://localhost:8080/desafioInvia/LoginViewServlet', false);
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;
    xmlhttp.send("email="+email+"&"+"senha="+senha);
     
    //Execution blocked till server send the response
    if (xmlhttp.readyState == 4) { 
        if (xmlhttp.status == 200) 
        {
            document.getElementById("message").innerHTML = xmlhttp.responseText;
        } 
        else
        {
            alert('Something is wrong !!');
        }
    }
}

var request;
 
$("#login").submit(function(event) {
 
    // abort any pending request
    if (request) {
        request.abort();
    }
     
  event.preventDefault();
 
   $("#result").html('');
 
   var values = $(this).serialize();
 
  request =$.ajax({
      url: "http://localhost:8080/desafioInvia/LoginViewServlet",
      type: "post",
      data: {
			email : $("#email").val(),
			senha : $("#senha").val()
		},
      success: function(){
           $("#result").html('submitted successfully');
      },
      error:function(){
          $("#result").html('there is error while submit');
      }   
  }); 
});

function login(){
	$.ajax({
		url : 'http://localhost:8080/desafioInvia/LoginViewServlet',
		type : 'POST',
		data : {
			email : $("#email").val(),
			senha : $("#senha").val()
		}, success : function (response, text) {
			alert(response);
		},error : function (request, status, error) {
			$("#erro").val(request.responseText);
			alert(request.responseText);
		}
	}).fail(function() {
	   $("#erro").val(request.responseText);
	   alert(request.responseText);
	});
}*/