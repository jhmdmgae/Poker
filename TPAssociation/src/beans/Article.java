package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.*;

@Entity
@Table(name="ARTICLE", schema="ASSOC_EMN")
public class Article{
	
	@Id
	@Column(name="CODE")
	//@NotNull
	//@Size(min = 1, max = 10)
	private String code; 
	
	@Column(name="NOM")
	//@NotNull
	//@Size(min = 1, max = 10)
	private String nom;
	
	@Column(name="PRIX")
	//@NotNull
	//@Min(1)
	private double prix;
	
	@Column(name="STOCK")
	//@NotNull
	//@Min(0)
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
