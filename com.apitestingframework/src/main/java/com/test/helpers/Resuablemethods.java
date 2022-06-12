package com.test.helpers;

import com.test.pojomodels.CreateUserDatapojo;
import com.test.pojomodels.Deletepojo;
import com.test.pojomodels.UpdatePojo;

import io.restassured.response.Response;

public class Resuablemethods {
	public static CreateUserDatapojo user;
	public static  UpdatePojo update;
	public static Deletepojo del;
	
	public static CreateUserDatapojo adduserdata()
	{
		user=new CreateUserDatapojo();
		user.setAccountno("TA-123457z");
    	user.setDepartmentno("3");
    	user.setPincode("222222");
    	user.setSalary("25000");
		return user;
		
	}
	public static  UpdatePojo updatedata(String userid ,String id)
	{
		UpdatePojo update= new UpdatePojo();
		
		                     //pojo class for update user
//		 String id=update.getId();
//		 String userid=update.getUserid();
//		 String salary=update.getSalary();
//		 String pincode=update.getPincode();
//	
//		 String accountno=update.getAccountno();
	    update.setAccountno("TA-123457z");
		update.setDepartmentno("4");
		update.setPincode("222222");
	    update.setSalary("25000");
		update.setId(id);
		update.setUserid(userid);
		
		
		return update;
		
	}
	public static Deletepojo deldata(String userid ,String id)
	{
		 Deletepojo del= new Deletepojo();
	del.setUserid(userid);
	del.setId(id);
		return del;
		
	}
	public static int  statuscode(Response res)// getting the status
	{
		
		return res.getStatusCode();	
	}
	public String getcontenttype(Response res)
	{
		return res.getContentType();
		
	}
	public static void checkingstatus(Response res,int scode)
	{
		res.then().statusCode(scode);
	}
	public static String getjsonpath(Response res,String path)
	{
		return res.jsonPath().get(path);
		
	}
	

}
