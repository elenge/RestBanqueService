package com.ueve;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/CompteService")
public class CompteService {

	CompteDao cDAO = new CompteDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Path("/comptes")
	@Produces(MediaType.APPLICATION_XML)
	public List<CompteBancaire> getComptes(){
		return cDAO.getAllComptes();
	}
	
	@GET
	@Path("/comptes/{idCompte}")
	@Produces(MediaType.APPLICATION_XML)
	public CompteBancaire getCompte(@PathParam("idCompte") int idCompte){
		return cDAO.getCompte(idCompte);
	}
	
	@GET
	@Path("/comptes/solde/{idCompte}")
	@Produces(MediaType.APPLICATION_XML)
	public String getSolde(@PathParam("idCompte") int idCompte){
		return Double.toString(cDAO.getSolde(idCompte));
	}
	
	@PUT
	   @Path("/comptes/add")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String createCompte(
	      @FormParam("proprietaire") String proprietaire,
	      @Context HttpServletResponse servletResponse) throws IOException{
	      boolean result = cDAO.addCompte(proprietaire);
	      if(result == true){
	         return "Le compte a bien été ajouté";
	      }
	      return "Le compte n'a pas été ajouté, but why !?";
	   }
	
	@POST
	   @Path("/comptes/depot")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String depotCompte(@FormParam("idCompte") int idCompte,
	      @FormParam("montant") double montant,
	      @Context HttpServletResponse servletResponse) throws IOException{
		
			return Boolean.toString(cDAO.doDepot(montant, idCompte));
		
	      
	   }
	
	@POST
	   @Path("/comptes/retrait")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String retraitCompte(@FormParam("idCompte") int idCompte,
	      @FormParam("montant") double montant,
	      @Context HttpServletResponse servletResponse) throws IOException{
			return Boolean.toString(cDAO.doRetrait(montant, idCompte));
	   }
	
	
	
	 @DELETE
	   @Path("/comptes/{idCompte}")
	   @Produces(MediaType.APPLICATION_XML)
	   public String deleteCompte(@PathParam("idCompte") int idCompte){
	      boolean result = cDAO.deleteCompte(idCompte);
	      if(result == true){
	    	  return "Suppression effectuée avec succès";
	      } else{
	    	  return "Suppression échouée";
	      }
	     
	   }

	   @OPTIONS
	   @Path("/comptes")
	   @Produces(MediaType.APPLICATION_XML)
	   public String getSupportedOperations(){
	      return "Voici la liste des opérations possibles : <operations>GET /comptes | /comptes/{id} | /comptes/solde/{id} | , PUT /comptes/add?proprietaire=xxx, POST /comptes/retrait?idCompte=xx&montant=xx | /comptes/depot?.., DELETE /comptes/{id}</operations>";
	   }
}
