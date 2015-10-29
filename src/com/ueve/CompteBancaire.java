package com.ueve;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author arpbo
 *
 */
@XmlRootElement(name= "CompteBancaire")
public class CompteBancaire {
	
	private String client;
	private static int numeroCompte = 0;
	private String devise;
	private double montant;
	
	
	/**
	 * Constructeur vide
	 */
	public CompteBancaire(){
		
		super();
		
	}
	
	
	/**
	 * Constructeur avec un client
	 * Initialisation du montant à 0
	 * @param nomClient nom du client auquel sera rattaché le compte
	 */
	public CompteBancaire(String nomClient, String deviseCompte){
		
		if(nomClient!=null && deviseCompte!=null){
			//vérifier que le compte n'existe pas déjà
			this.client= nomClient;
			this.montant= 0;
			this.devise= deviseCompte;
			this.numeroCompte+=1;
			//ajout du compte à la liste
		}else {
			System.out.println("Un des champs est vide.");
		}
	
	}


	public String getClient() {
		return client;
	}


	public static int getNumeroCompte() {
		return numeroCompte;
	}


	public String getDevise() {
		return devise;
	}


	/**
	 * Méthode afin d'afficher le montant du compte
	 * @return double montant
	 */
	public double getMontant() {
		return montant;
	}
	
	@XmlElement
	public void setDevise(String devise) {
		this.devise = devise;
	}

	@XmlElement
	public void setMontant(double montant) {
		this.montant = montant;
	}


	public String toString(){
		return "Le compte n° : "+this.numeroCompte+" appartient au client "+this.client+".\n\n"+"Le montant du compte est : "+this.montant+" "+this.devise+".";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((devise == null) ? 0 : devise.hashCode());
		long temp;
		temp = Double.doubleToLongBits(montant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompteBancaire other = (CompteBancaire) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (devise == null) {
			if (other.devise != null)
				return false;
		} else if (!devise.equals(other.devise))
			return false;
		if (Double.doubleToLongBits(montant) != Double.doubleToLongBits(other.montant))
			return false;
		return true;
	}
}
