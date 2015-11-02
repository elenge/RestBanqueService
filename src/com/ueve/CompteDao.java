package com.ueve;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * @author arpbo
 *
 */
public class CompteDao {
	
	
	/**
	 * Le fichier comptes.dat se situe dans le répertoire tomcat une fois le war du projet déployé dans webapps
	 * @return
	 */
	public List<CompteBancaire> getAllComptes(){
		
		List<CompteBancaire> compteList = null;
		try{
			
			File file = new File("comptes.dat");
			
			if (!file.exists()) {
				
				CompteBancaire compte = new CompteBancaire("Jean-Luc P.");
				compteList = new ArrayList<CompteBancaire>();
				compteList.add(compte);
				saveCompteList(compteList);
			}
			else{

				FileInputStream fis = new FileInputStream(file);
				
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				compteList = (List<CompteBancaire>) ois.readObject();
				
				ois.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return compteList;
		
	}
	
	
	 public CompteBancaire getCompte(int idCompte){
	      List<CompteBancaire> comptesList = getAllComptes();

	      for(CompteBancaire cb: comptesList){
	         if(cb.getIdCompte() == idCompte){
	            return cb;
	         }
	      }
	      return null;
	   }
	
	 
	 /**
	 * Méthode info de retourner le solde du compte n° XXX
	 * @param numeroCompte
	 * @return
	 */
	public double getSolde(int idCompte){
		 
		 List<CompteBancaire> comptesList = getAllComptes();

	      for(CompteBancaire cb: comptesList){
	         if(cb.getIdCompte() == idCompte){
	            return cb.getSolde();
	         }
	      }
	      return 0;
	 }
	 
	 
	/**
	 * Créer un compte bancaire
	 * On vérifie que le compte n'existe pas déjà
	 * @param cb
	 * @return
	 */
	public boolean addCompte(String proprietaire){

		CompteBancaire cb = new CompteBancaire(proprietaire);
		List<CompteBancaire> compteList = getAllComptes();
		boolean compteExists = false;
		for (CompteBancaire compte : compteList){
			if(compte.getIdCompte() == cb.getIdCompte()){
				compteExists = true;
				break;
			}
		}
		if(!compteExists){
			compteList.add(cb);
			saveCompteList(compteList);
			return true;
		}
		return false;
	}
	
	/**
	 * On effectue un dépôt, donc on ajoute le montant à la somme précédente du compte et on modifie le montant total
	 * On vérifie que le montant est >= 0
	 * Si false soit le compte n'existe pas, soit le montant est inférieur à 0 ou autre
	 * @param montant
	 * @param nCpt
	 * @return
	 */
	public boolean doDepot(double montant, int nCpt){
		
		if(montant>=0){
			List<CompteBancaire> comptesList = getAllComptes();

		      for(CompteBancaire cb: comptesList){
		         if(cb.getIdCompte() == nCpt){
		            cb.setSolde(montant+cb.getSolde());
		            saveCompteList(comptesList);
		            return true;
		         }
		      }
		      return false;	
		} else {
			return false;
		}
		
	}
	
	
	/**
	 * On effectue un retrait sur le compte numéro nCpt
	 * Renvoie false si le compte n'existe pas, le montant est inférieur à 0 ou autre, ou si le compte n'a pas le solde suffisant
	 * @param montant
	 * @param nCpt
	 * @return
	 */
	public boolean doRetrait(double montant, int nCpt){
		
		if(montant>=0){
			List<CompteBancaire> comptesList = getAllComptes();

		      for(CompteBancaire cb: comptesList){
		         if(cb.getIdCompte() == nCpt){
		        	 if(cb.getSolde()>=montant){
		        		 cb.setSolde(cb.getSolde()-montant);
		        		 saveCompteList(comptesList);
		        		 return true;
		        	 } else {
		        		 return false;
		        	 }
		         }
		      }
		      return false;	
		} else {
			return false;
		}
		
	}
	
	
	 public boolean deleteCompte(int numero){
	      List<CompteBancaire> compteList = getAllComptes();

	      for(CompteBancaire compte: compteList){
	         if(compte.getIdCompte() == numero){
	            int index = compteList.indexOf(compte);			
	            compteList.remove(index);
	            saveCompteList(compteList);
	            return true;   
	         }
	      }		
	      return false;
	   }
	
	
	/**
	 * Méthode qui permet de sauvegarder la liste des comptes dans le fichier comptes.dat
	 * Indispensable pour sauvegarder une MODIFICATION sur un compte ou autre
	 * @param compteList
	 */
	private void saveCompteList(List<CompteBancaire> compteList) {
		
		try {
			File file = new File("comptes.dat");
			FileOutputStream fos;
			
			fos = new FileOutputStream(file);
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(compteList);
			oos.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
