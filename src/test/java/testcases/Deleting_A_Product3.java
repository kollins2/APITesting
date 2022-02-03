package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Deleting_A_Product3 {

	

	@Test(priority=1)
	public void Delete_A_Product() {

		
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
						.body(new File(".\\src\\main\\java\\data\\DeletingPayload3.json"))
			    //        .body(payload)
		    .when()
			            .delete("/delete.php")
			.then()
						.extract().response();
		// .statusCode(200)
		// .header("Content-Type", "application/json; charset=UTF-8" );
		int actualstatusCode = response.getStatusCode();
		System.out.println("actualstatusCode" + actualstatusCode);
		Assert.assertEquals(actualstatusCode, 200);

		String actualResponseHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader" + actualResponseHeader);
		Assert.assertEquals(actualResponseHeader, "application/json; charset=UTF-8");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body" + responseBody);

		JsonPath jp = new JsonPath(responseBody);
		String actualresponseMessage = jp.get("message");
		System.out.println("actualresponseMessage" + actualresponseMessage);
		Assert.assertEquals(actualresponseMessage, "Product was deleted.");

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

	@Test(priority=2)
	public void readAProduct() {
//		SoftAssert  softAssert = SoftAssert();
		Response response = given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json;")
				.queryParam("id", "2978")
			
				.when().get("/read_one.php")
				
				
				// Extracting response
				.then().extract().response();
		

		// Validating Status code
		int statusCode = response.getStatusCode();
		System.out.println("status Code" + statusCode);
		Assert.assertEquals(statusCode, 404);
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

		
		//Product does not exist.
		JsonPath jp = new JsonPath(responseBody);
		String productMassage = jp.getString("message");
		System.out.println("Product Message" + productMassage);
	
	//	softAssert.assertEquals(productMassage., "Product does not exist.","not Matching");
		Assert.assertEquals(productMassage, "Product does not exist.");
//		softAssert.assertAll();
	
	}
//	private SoftAssert SoftAssert() {
		// TODO Auto-generated method stub
	//	return null;
	//}	
	}






