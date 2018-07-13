package com.ziya05.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.script.ScriptException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ziya05.bo.IScaleBo;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Result;
import com.ziya05.entities.Scale;
import com.ziya05.entities.ScaleException;
import com.ziya05.entities.TesteeData;
import com.ziya05.factories.ScaleBoFactory;

@Path("/ScaleService") 
public class ScaleService {
	
    IScaleBo bo = null;
    
    public ScaleService() throws NamingException {
    	super();

    	bo = ScaleBoFactory.createScaleBo();
    }
    
    @GET 
    @Path("/scales") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Scale> getScales() throws ClassNotFoundException, SQLException{ 
        return bo.getScales(); 
    }  
    
    @GET 
    @Path("/personalInfo/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public PersonalInfo getPersonalInfoById(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	return bo.getPersonalInfoById(id);
    }
	
    @GET 
    @Path("/scale/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
	public Scale getScaleById(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		return bo.getScaleById(id);
	}
    
    @POST 
    @Path("/scale/result/{id}") 
    @Produces(MediaType.TEXT_PLAIN) 
    @Consumes(MediaType.APPLICATION_JSON)
    public String saveResult(@PathParam("id") int id, TesteeData data) throws ClassNotFoundException, SQLException, ScriptException, NamingException {
    	String strReturn = "";
    	
    	int baseId = bo.saveTesteeData(id, data);
    	
    	try
    	{

	    	Result result = bo.getResult(id, data);

	    	bo.saveResult(id, baseId, result);   	
	    	
    	} catch (ScaleException e) {
    		strReturn = e.getMessage();
    	}
    	
    	System.gc();
    	
    	return strReturn;
    }
    
}
