package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Creating_A_Product {

	@Test
	public void creat_A_Product() {
		/*
		 * given: all input details(base URI,Headers,Payload/Body,QueryParameters,authentication if needed) when:
		 * submit api requests(Http method,Endpoint/Resource) then: validate
		 * response(status code, Headers, responseTime, Payload/Body)*
		 * 
		 * Creating Method POST https://techfios.com/api-prod/api/product/create.php
		 * Content-Type:application/json; charset=UTF-8 Status Code :201
		 * 
		 * 
		 * { "name": "Amazing Pillow 2.0", "price": "199", "description":
		 * "The best pillow for amazing programmers.", "category_id": 2, } We do not
		 * need parameter for creaitng a product
		 */
		//pulling data using HashMap
	//	HashMap<String,String> payload = new HashMap<String,String>();
	//	payload.put("name", "sansung");
	//	payload.put("price","987");
	//	payload.put("description", "Magical relief.");
	//	payload.put("category_name", "Electronics");
		
		
		Response response =

			given()    
			            .baseUri("https://techfios.com/api-prod/api/product")
						.headers("Content-Type", "application/json;charset=UTF-8")
						//pulling from file
						.body(new File(".\\src\\main\\java\\data\\CreatPayload.json"))
			    //        .body(payload)
		   .when()
			            .post("/create.php")
			.then()
						.extract().response();
		// .statusCode(200)
		// .header("Content-Type", "application/json; charset=UTF-8" );
		int actualstatusCode = response.getStatusCode();
		System.out.println("actualstatusCode" + actualstatusCode);
		Assert.assertEquals(actualstatusCode, 201);

		String actualResponseHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader" + actualResponseHeader);
		Assert.assertEquals(actualResponseHeader, "application/json; charset=UTF-8");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body" + responseBody);

		JsonPath jp = new JsonPath(responseBody);
		String actualresponseMessage = jp.get("message");
		System.out.println("actualresponseMessage" + actualresponseMessage);
		Assert.assertEquals(actualresponseMessage, "Product was created.");

		long actualresponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("responseTime" + actualresponseTime);

		// response time varies

		if (actualresponseTime <= 2000) {
			System.out.println("Response time is within range");
		} else {
			System.out.println("Response time is out of range");
          // if you want assertion to failed
			
		//	Assert.fail("Assert Failed!");
		}
	}
}
