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

public class CompteDao {

	public List<CompteBancaire> getAllComptes(){
		
		List<CompteBancaire> compteList = null;
		try{
			
			File file = new File("comptes.dat");
			
			if (!file.exists()) {
				
				CompteBancaire compte = new CompteBancaire("Jean-Luc P.", "euros");
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
	
	 public CompteBancaire getCompte(int numeroCompte){
	      List<CompteBancaire> comptesList = getAllComptes();

	      for(CompteBancaire cb: comptesList){
	         if(cb.getNumeroCompte() == numeroCompte){
	            return cb;
	         }
	      }
	      return null;
	   }
	
	public int addCompte(CompteBancaire cb){
		
		List<CompteBancaire> compteList = getAllComptes();
		boolean compteExists = false;
		for (CompteBancaire compte : compteList){
			if(compte.getNumeroCompte() == cb.getNumeroCompte()){
				compteExists = true;
				break;
			}
		}
		if(!compteExists){
			compteList.add(cb);
			saveCompteList(compteList);
			return 1;
		}
		return 0;
	}
	
	 public int deleteCompte(int numero){
	      List<CompteBancaire> compteList = getAllComptes();

	      for(CompteBancaire compte: compteList){
	         if(compte.getNumeroCompte() == numero){
	            int index = compteList.indexOf(compte);			
	            compteList.remove(index);
	            saveCompteList(compteList);
	            return 1;   
	         }
	      }		
	      return 0;
	   }
	
	
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
