package beans;

import javax.validation.constraints.*;

public class Article{
	
	@NotNull
	@Size(min = 1, max = 10)
	private String code; 
	
	@NotNull
	@Size(min = 1, max = 10)
	private String nom;
	
	@NotNull
	@Min(1)
	private double prix;
	
	@NotNull
	@Min(0)
	private int quantite;
	
	
	public Article() {
		
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	
	
	
}
