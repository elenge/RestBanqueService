package com.ueve;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author arpbo
 *
 */
@XmlRootElement(name= "CompteBancaire")
public class CompteBancaire implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String proprietaire;
	private static int numeroCompte = 1;
	private int idCompte;
	private double solde;
	
	
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
	public CompteBancaire(String proprietaire){
		
		if(proprietaire!=null){
			this.proprietaire= proprietaire;
			this.solde= 0;
			this.idCompte = numeroCompte;
			numeroCompte++;
		}else {
			System.out.println("Un des champs est vide.");
		}
	
	}

	@XmlElement
	public String getProprietaire() {
		return proprietaire;
	}

	@XmlElement
	public int getNumeroCompte() {
		return numeroCompte;
	}


	/**
	 * Méthode afin d'afficher le montant du compte
	 * @return double montant
	 */
	public double getSolde() {
		return solde;
	}
	

	@XmlElement
	public void setSolde(double solde) {
		this.solde = solde;
	}


	public String toString(){
		return "Le compte n° : "+this.numeroCompte+" appartient à "+this.proprietaire+".\n\n"+"Le montant du compte est : "+this.solde+".";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((proprietaire == null) ? 0 : proprietaire.hashCode());
		long temp;
		temp = Double.doubleToLongBits(solde);
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
		if (proprietaire == null) {
			if (other.proprietaire != null)
				return false;
		} else if (!proprietaire.equals(other.proprietaire))
			return false;
		if (Double.doubleToLongBits(solde) != Double.doubleToLongBits(other.solde))
			return false;
		return true;
	}
}
