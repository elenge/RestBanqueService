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
	@Path("/comptes/{numeroCompte}")
	@Produces(MediaType.APPLICATION_XML)
	public CompteBancaire getCompte(@PathParam("numeroCompte") int numeroCompte){
		return cDAO.getCompte(numeroCompte);
	}
	
	@GET
	@Path("/comptes/{numeroCompte}/solde")
	@Produces(MediaType.APPLICATION_XML)
	public double getSolde(@PathParam("numeroCompte") int numeroCompte){
		return cDAO.getSolde(numeroCompte);
	}
	
	@PUT
	   @Path("/comptes/add")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String createCompte(
	      @FormParam("proprietaire") String proprietaire,
	      @Context HttpServletResponse servletResponse) throws IOException{
	      int result = cDAO.addCompte(proprietaire);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }
	
	@POST
	   @Path("/comptes/depot")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public boolean depotCompte(@FormParam("nCpt") int nCpt,
	      @FormParam("montant") double montant,
	      @Context HttpServletResponse servletResponse) throws IOException{
		
			return cDAO.doDepot(montant, nCpt);
		
	      
	   }
	
	@POST
	   @Path("/comptes/retrait")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public boolean retraitCompte(@FormParam("nCpt") int nCpt,
	      @FormParam("montant") double montant,
	      @Context HttpServletResponse servletResponse) throws IOException{
		
			return cDAO.doRetrait(montant, nCpt);
		
	      
	   }
	
	
	
	 @DELETE
	   @Path("/comptes/{numeroCompte}")
	   @Produces(MediaType.APPLICATION_XML)
	   public String deleteCompte(@PathParam("numeroCompte") int numeroCompte){
	      int result = cDAO.deleteCompte(numeroCompte);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }

	   @OPTIONS
	   @Path("/comptes")
	   @Produces(MediaType.APPLICATION_XML)
	   public String getSupportedOperations(){
	      return "<operations>GET, PUT, POST, DELETE</operations>";
	   }
}
