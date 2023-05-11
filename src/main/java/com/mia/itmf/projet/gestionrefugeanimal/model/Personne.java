
package com.mia.itmf.projet.gestionrefugeanimal.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mia.itmf.projet.gestionrefugeanimal.exception.ExceptionEmploye;

public abstract class Personne {
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String adresse;
	
	public enum Critere{
		NOM,PRENOM,EMAIL;
	}

	public Personne() {
	}
	
	public Personne(String nom, String prenom, String email, String telephone, String adresse) {
		setNom(nom);
		setPrenom(prenom);
		try {
			setEmail(email);
		} catch (ExceptionEmploye e) {
			e.printStackTrace();
		}
		setAdresse(adresse);
		setTelephone(telephone);
	}
	
	public abstract String getKey();
	
	public String getNom() {
		return nom;
	}


	protected void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	protected void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	protected boolean setEmail(String email) throws ExceptionEmploye {
//		//email.matches(".+@.+\\.[a-z]+");
		if(isEmailAdress(email)) {
			if(emailAdressAccept(email)) {
				this.email = email;
				return true;
			}
			throw new ExceptionEmploye(email+": Ne pas utiliser '.', '-' et '_' caractères consécutivement");
		}
		
		throw new ExceptionEmploye("L'email "+email+" est incorrect");
	
	}
	
    public boolean isEmailAdress(String email) {
        Pattern parttern = Pattern.compile("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Matcher matcher = parttern.matcher(email);
        
        return matcher.matches();
    }
    
    private boolean emailAdressAccept(String email) throws ExceptionEmploye {
    	for(int i = 0; i < email.length(); i++) {
    		if(email.charAt(i) == '@' && email.substring(0, i).length()<3) {
        		throw new ExceptionEmploye(email+" est incorrect: Utilisez au moins 3 caractères");
    		}
    	}
    	
    	for(int i = 0; i < email.length(); i++) {
    		if((email.charAt(i) == '-' || email.charAt(i) == '.' || email.charAt(i) == '_') && (email.charAt(i+1) == '-' || email.charAt(i+1) == '.' || email.charAt(i+1) == '_')) {
    			return false;
    		}
    	}
		return true;
    }

	public String getTelephone() {
		return telephone;
	}


	protected void setTelephone(String telephone) {
		// Expression régulière pour un entier
		String pattern = "^-?\\d+$";

		// Vérification de la correspondance avec l'expression régulière
		if (Pattern.matches(pattern, telephone)) {
			if(telephone.length() == 10 || telephone.length() == 13) {
				this.telephone = telephone;
			}else {
				System.err.println("La numéro de téléphone "+telephone+" n'est pas correct !.");
			}
		} else {
				System.err.println("La numéro de téléphone "+telephone+" n'est pas un entier.");
		}
	}


	public String getAdresse() {
		return adresse;
	}


	protected void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	@Override
	public String toString() {
		return "Personne [id=" + getKey() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", email=" + getEmail() + ", telephone="
				+ getTelephone() + ", adresse=" + getAdresse() + "]";
	}
	
	
	
	

}
