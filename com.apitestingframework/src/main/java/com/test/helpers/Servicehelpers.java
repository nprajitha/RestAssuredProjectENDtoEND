package com.test.helpers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.test.constants.Endpoints;
import com.test.pojomodels.CreateUserDatapojo;
import com.test.pojomodels.Deletepojo;
import com.test.pojomodels.Loginpojo;
import com.test.pojomodels.UpdatePojo;
import com.test.pojomodels.Userdatapojo;
import com.test.utility.Utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;


public class Servicehelpers  {
	 private static Response response;
	  private static  String Token;
	
	public static String geturi() throws IOException
	{
		String baseuri=Utility.getpropertyvalue("uri");
		return baseuri;
		
	}
	public static Response Loginapp() throws IOException
	{
	String user=Utility.getpropertyvalue("username");
		String pswd=Utility.getpropertyvalue("password");
	  //  Loginpojo lp=new Loginpojo(user,pswd);
		Loginpojo l= new Loginpojo();
		l.setUsername(user);
		l.setPassword(pswd);
	    response=RestAssured.given()
	    		.contentType(ContentType.JSON)
   		.body(l).log().all().when().post(Endpoints.LOGIN);
		//response.then().contentType(ContentType.TEXT).log().all().extract().response();
	   
		return response;
	}
   public static String gettoken() throws IOException 
    {
    	
		response=Loginapp();
		

    	Token =response.jsonPath().get("[0].token");
    	System.out.println("token is :" +Token);
return Token;
    	
    }
    
    public  static Map<String, String> getuserdata()
    
    {
    	Header head=new Header("token",Token);
    	System.out.println("extracted token:"+Token);
    	response=RestAssured.given().contentType(ContentType.JSON)
    			.header(head).when().get(Endpoints.GETUSERDATA);    	
    	Map<String ,String > list= response.jsonPath().get("[0]");
   //	System.out.println("content of record"+list.get(0));
//    	
//    	String accno=response.jsonPath().get("[0].accountno");
//		System.out.println("first record accountno;"+accno);
//		System.out.println("userid: "+response.jsonPath().get("[0].userid"));
//		System.out.println("id: "+response.jsonPath().get("[0].id"));
		return list;
    }
    
    public static Response createuserdata()
    {
    	Header head=new Header("token",Token);
    	CreateUserDatapojo user =Resuablemethods.adduserdata();
    	
    	response= RestAssured.given().contentType(ContentType.JSON)
    			.header(head).body(user).when().post(Endpoints.ADDUSERDATA);
		return response;
    	
    }
    
    
    public static Response updateuser(String  userid, String id)
    {
    	Header head =new Header("token",Token);
    	UpdatePojo update=Resuablemethods.updatedata(userid, id);
    	response= RestAssured.given().contentType(ContentType.JSON)
    			.header(head)
    			.body(update).when().put(Endpoints.UPDATEUSER);
    	
    	 
		return response;
   	
    	
    }
    public static Response deleteuser(String  userid, String id)
    {
    	Header head =new Header("token",Token);
    	System.out.println("extracted token:"+Token);
    	Deletepojo del=Resuablemethods.deldata(userid ,id);
    	response= RestAssured.given().contentType(ContentType.JSON)
    			.header(head).body(del).when().delete(Endpoints.DELETEUSER);
    	
		return response;
    	
    }
}
