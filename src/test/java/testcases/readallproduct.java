package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class readallproduct {
 
	@Test
	public void readAllProducts() {
		/*
		 given: all input details(base URI,Headers,Payload/Body,QueryParameters)
when:  submit api requests(Http method,Endpoint/Resource)
then:  validate response(status code, Headers, responseTime, Payload/Body)
 
		 */
		Response response =
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.headers("Content-Type","application/json;charset=UTF-8").
	when()
		    .get("/read.php").
		//Extracting response
	 then()
	 .extract().response();
		//   .statusCode(200)
	//	   .header("Content-Type","application/json; charset=UTF-8" );
		int actualstatusCode = response.getStatusCode();
		System.out.println("actualstatusCode"+ actualstatusCode);
		 Assert.assertEquals(actualstatusCode, 200);
	
	String actualHeader=response.getHeader("Content-Type");
	System.out.println("actualHeader"+ actualHeader);
	Assert.assertEquals(actualHeader,"application/json; charset=UTF-8");
	
	}


}
