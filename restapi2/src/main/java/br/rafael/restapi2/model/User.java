package br.rafael.restapi2.model;

public class User {
    private Long id;
	private String nome;
	private String email;
	
	public User(String nome, String email) {
		// TODO Auto-generated constructor stub
		this.nome = nome;
		this.email= email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    

    // Getters and setters
}