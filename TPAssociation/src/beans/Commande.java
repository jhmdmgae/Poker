package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMMANDE", schema="ASSOC_EMN")
public class Commande {
	@Id
	@Column(name="CODE")
	private String code;
	@Column(name="IDENTIFIANT")
	private String identifiant;
	
	
	public Commande(){
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	
}
