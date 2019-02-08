package com.validate;

import static com.jayway.restassured.RestAssured.given;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;

public class TestPost {

	 @BeforeClass
	    public static void setup() {
	       /* String port = System.getProperty("server.port");
	        if (port == null) {
	            RestAssured.port = Integer.valueOf(8080);
	        }
	        else{
	            RestAssured.port = Integer.valueOf(port);
	        }
*/

	       /* String basePath = System.getProperty("server.base");
	        if(basePath==null){
	            basePath = "/api/v1/create";
	        }
	        RestAssured.basePath = basePath;*/

	        String baseHost = System.getProperty("server.host");
	        if(baseHost==null){
	            baseHost = "http://dummy.restapiexample.com";
	        }
	        RestAssured.baseURI = baseHost;

	    }


	
	
	@Test
	
    public void aCarGoesIntoTheGarage() {
		
		Map<String,String> car = new HashMap<String,String>();
        car.put("name", "test");
        car.put("salary", "123");
        car.put("age", "23");
        ResponseBody responsebody=  given()
				.contentType(ContentType.JSON)
        .body(car)
        .when().post("/api/v1/create")
        .getBody();
         
         System.out.println(responsebody.asString());
         JSONObject obj = new JSONObject(responsebody.asString());
         //obj.get("name");
         Assert.assertEquals("test", obj.get("name"));
         
    }
	
	
}
