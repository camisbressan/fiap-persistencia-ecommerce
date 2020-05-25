package br.com.fiap.persistencia.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDTO implements Serializable {

	private static final long serialVersionUID = -8324704583975851222L;

	@NotNull
	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 caracteres e no maximo 100.")
	private String email;

	@NotNull
	@Size(min = 1, max = 8, message = "Obrigatório no minimo 1 caracter e no maximo 8.")
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
