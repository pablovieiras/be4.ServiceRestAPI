package be4service2.models;

public class Usuario {
	private String email;
	private String senha;
	private String chave;
	//para verificar a autenticação login
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario(String email, String senha, String chave) {
		super();
		this.email = email;
		this.senha = senha;
		this.chave = chave;
	}

	public Usuario(){
		
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
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	
}
