package com.test.automationtest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.helpers.Resuablemethods;
import com.test.helpers.Servicehelpers;
import com.test.pojomodels.Userdatapojo;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class EndtoendAPItesting extends Servicehelpers{
	
	static String  userid ;
	static String id;
	@BeforeMethod
	public static void baseuri() throws IOException
	{
		RestAssured.baseURI=geturi();
	}
	@Test(priority=1)
	public static void logindetails() throws IOException 
	{
		String token=gettoken();
		System.out.println("token "+token);
	}
	@Test(priority=3)
	public static void userdata()
	{
		Map<String, String> users= getuserdata();
	System.out.println("first users:"+users.get("accountno"));
	System.out.println("userid:"+users.get("userid"));
	System.out.println("id:"+users.get("id"));
	 userid=users.get("userid");
	 id=users.get("id");
		
	}
	@Test(priority=2)
	public static void addinguser()
	{
		Response res= createuserdata();
		Resuablemethods.checkingstatus(res, 201);
		String msg=Resuablemethods.getjsonpath(res, "status");
		System.out.println(msg);
		Assert.assertEquals(msg, "success");
		res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addresponseschema.json"));
	}
	@Test(priority=4)
	public static void updatingdata()
	{
		Response res=updateuser(userid,id);
		 int code=Resuablemethods.statuscode(res);
		 System.out.println("update status code:"+code);
		 Resuablemethods.checkingstatus(res, code);
		 String updatemsg=Resuablemethods.getjsonpath(res, "status");
		 System.out.println("update status:"+updatemsg);
	}
	@Test(priority=5)
	public static void deluser()
	{
		Response res=deleteuser(userid,id);
		 int code=Resuablemethods.statuscode(res);
		 System.out.println("delete status code:"+code);
		 Resuablemethods.checkingstatus(res, code);
		 String deletemsg=Resuablemethods.getjsonpath(res, "status");
		 System.out.println("delete status:"+deletemsg);
	}

}
