package com.desafio.invia.util;

import com.desafio.invia.basicas.Cargo;
import com.desafio.invia.basicas.Entidade;
import com.desafio.invia.basicas.Orgao;
import com.desafio.invia.basicas.Sistema;
import com.desafio.invia.basicas.Telefone;
import com.desafio.invia.basicas.Usuario;

public class Util {

	public static Object entidade(Object entidade) {
		
		Entidade tabela = null;

		try {
			if (entidade instanceof Cargo) {
				tabela = Cargo.class.newInstance();
			} else if (entidade instanceof Orgao) {
				tabela = Orgao.class.newInstance();
			} else if (entidade instanceof Sistema) {
				tabela = Sistema.class.newInstance();
			} else if (entidade instanceof Telefone) {
				tabela = Telefone.class.newInstance();
			} else {
				tabela = Usuario.class.newInstance();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return tabela;
	}
}