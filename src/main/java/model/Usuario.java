package model;

import java.time.LocalDate;


public class Usuario{

	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private LocalDate dataNascimento;

	
	
	public Usuario(String nome, String cpf, String email, String senha, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}
	
	public Usuario() {
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
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
	
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
