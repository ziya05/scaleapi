package com.ziya05.service;

import java.sql.SQLException;
import java.util.List;

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
import com.ziya05.entities.SelectedData;
import com.ziya05.entities.UserHistoryData;
import com.ziya05.factories.ScaleBoFactory;
import com.ziya05.factories.ScaleDaoFactory;

@Path("/ScaleService") 
public class ScaleService {
	
    IScaleDao dao = ScaleDaoFactory.createScaleDao();
    
    @GET 
    @Path("/scales") 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Scale> getScales() throws ClassNotFoundException, SQLException{ 
    	
    	//Sleep();
        return dao.getAllScales(); 
    }  
    
    @GET 
    @Path("/personalInfo/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public PersonalInfo getPersonalInfoById(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	//Sleep();
    	return dao.getPersonalInfoByScaleId(id);
    }
	
    @GET 
    @Path("/scale/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
	public Scale getScaleById(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	//Sleep();
		return dao.getScaleByScaleId(id);
	}
    
    @POST 
    @Path("/scale/result/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    @Consumes(MediaType.APPLICATION_JSON)
    public Result getResult(@PathParam("id") int id, UserHistoryData data) throws ClassNotFoundException, SQLException, ScriptException {
    	Sleep();
    	IScaleBo bo = ScaleBoFactory.createScaleBo(id);
    	Result result = bo.getResult(id, data);
    	result.setData(data);
    	return result;
    }
    
    private void Sleep() {
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
