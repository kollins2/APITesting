package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class readAproduct2 {

	
	
//	SoftAssert  softAssert = SoftAssert();
	
	@Test
	public void readAProduct() {
		/*
		 * given: all input details(base URI,Headers,Payload/Body,QueryParameters) when:
		 * submit api requests(Http method,Endpoint/Resource) then: validate
		 * response(status code, Headers, responseTime, Payload/Body)
		 * 
		 */
		Response response = given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json;")
				.queryParam("id", "2936")
			
				//Authorazation
				//	.auth().preemptive()
			//	.basic("username", "password")
		//	OR
				
				//	.headers("authorization","hhhhhhgghhgghhj")
				
				.when().get("/read_one.php")
				
				
				// Extracting response
				.then().extract().response();
		// .statusCode(200)
		// .header("Content-Type","application/json; charset=UTF-8" );

		// Validating Status code
		int statusCode = response.getStatusCode();
		System.out.println("status Code" + statusCode);
		Assert.assertEquals(statusCode, 200);
		// validating Status code
		long responseTime = response.getTime();
		System.out.println("responseTime" + responseTime);
    //	Assert.assertEquals(responseTime,"application/json;","not Matching");

		if (responseTime <= 3000) {
			System.out.println("Response time is within range");
		} else {
			System.out.println("Response time is out of range");

		}
		String responseBody = response.getBody().asString();
		System.out.println("Response Body" + responseBody);

		JsonPath jp = new JsonPath(responseBody);
		String productId = jp.getString("id");
		System.out.println("Product id" + productId);
		Assert.assertEquals(productId, "2936");
	//	softAssert.assertEquals(productId, "2936","not Matching");
		
		
		String productName = jp.getString("name");
		System.out.println("Product name" + productName);
		Assert.assertEquals(productName, "MG Pillow2 4.0");
	//	softAssert.assertEquals(productName, "MG Pillow2 4.0","not Matching");
		
		
		String productPrice = jp.getString("price");
		System.out.println("Product price" + productPrice);
		Assert.assertEquals(productPrice, "99");
	//	softAssert.assertEquals(productPrice, "99","not Matching");
		
	//	softAssert.assertAll();
	}
//	private SoftAssert SoftAssert() {
		// TODO Auto-generated method stub
	//	return null;
	//}

}
