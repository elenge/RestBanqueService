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
	@Produces(MediaType.APPLICATION_JSON)
	public List<CompteBancaire> getComptes(){
		return cDAO.getAllComptes();
	}
	
	@GET
	@Path("/comptes/{numeroCompte}")
	@Produces(MediaType.APPLICATION_JSON)
	public CompteBancaire getCompte(@PathParam("numeroCompte") int numeroCompte){
		return cDAO.getCompte(numeroCompte);
	}
	
	@PUT
	   @Path("/comptes")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String createCompte(
	      @FormParam("nomClient") String nomClient,
	      @FormParam("devise") String devise,
	      @Context HttpServletResponse servletResponse) throws IOException{
	      CompteBancaire newCompte = new CompteBancaire(nomClient, devise);
	      int result = cDAO.addCompte(newCompte);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }
	
	 @DELETE
	   @Path("/comptes/{numeroCompte}")
	   @Produces(MediaType.APPLICATION_JSON)
	   public String deleteCompte(@PathParam("numeroCompte") int numeroCompte){
	      int result = cDAO.deleteCompte(numeroCompte);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }

	   @OPTIONS
	   @Path("/comptes")
	   @Produces(MediaType.APPLICATION_JSON)
	   public String getSupportedOperations(){
	      return "<operations>GET, PUT, DELETE</operations>";
	   }
}
