package com.ziya05.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.script.ScriptException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ziya05.bo.IScaleBo;
import com.ziya05.dao.IScaleDao;
import com.ziya05.entities.OptionSelected;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Result;
import com.ziya05.entities.Scale;
import com.ziya05.entities.ScaleException;
import com.ziya05.entities.SelectedData;
import com.ziya05.entities.TesteeData;
import com.ziya05.factories.ScaleBoFactory;
import com.ziya05.factories.ScaleDaoFactory;

@Path("/ScaleService") 
public class ScaleService {
	
    IScaleDao dao = null;
    
    public ScaleService() throws NamingException {
    	super();
    	
    	dao = ScaleDaoFactory.createScaleDao();
    }
    
    @GET 
    @Path("/scales") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Scale> getScales() throws ClassNotFoundException, SQLException{ 
    	Sleep();
        return dao.getAllScales(); 
    }  
    
    @GET 
    @Path("/personalInfo/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public PersonalInfo getPersonalInfoById(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	Sleep();
    	return dao.getPersonalInfoByScaleId(id);
    }
	
    @GET 
    @Path("/scale/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
	public Scale getScaleById(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	Sleep();
		return dao.getScaleByScaleId(id);
	}
    
    @POST 
    @Path("/scale/result/{id}") 
    @Produces(MediaType.TEXT_PLAIN) 
    @Consumes(MediaType.APPLICATION_JSON)
    public String saveResult(@PathParam("id") int id, TesteeData data) throws ClassNotFoundException, SQLException, ScriptException, NamingException {
    	String strReturn = "";
    	
    	Sleep();
    	IScaleBo bo = ScaleBoFactory.createScaleBo(id);
    	int baseId = bo.saveTesteeData(id, data);
    	
    	try
    	{
	    	Result result = bo.getResult(id, data);
	    	bo.saveResult(id, baseId, result);
    	} catch (ScaleException e) {
    		strReturn = e.getMessage();
    	}
    	
    	return strReturn;
    }
    
    private void Sleep() {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
