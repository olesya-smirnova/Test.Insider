package APITest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Negative {
	
	 // Base URL for the Pet Store API
    public static final String BASE_URL = "https://petstore.swagger.io/v2/pet";
   
    @DataProvider(name = "testjson")
	public static Object[][] getfile() {
		
		String jsontest="C:\\jsontest2.json";
		JSONObject o = null;
		try {
          String contents =new String((Files.readAllBytes(Paths.get(jsontest))));
         
          o=new JSONObject(contents);
          
          int id= o.getInt("id");

          JSONObject category= o.getJSONObject("category");
         
          String name= o.getString("name");
      
	        JSONArray tags= o.getJSONArray("tags");
	        
	        String status= o.getString("status");
	          System.out.println(status);
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
			return new Object[][] { { o } };
	    }
        


		   @Test (dataProvider = "testjson", priority=1)   
		  
		    // Send POST request
		  
		   private void addPet(JSONObject petJsonObject) {
			    // Check if the required 'photoUrls' field is present
			    if (!petJsonObject.has("photoUrls")) {
			        System.out.println("Error: The 'photoUrls' field is missing in the request.");
			        return; // Exit the method if the required field is missing
			    }
			    
			    // Send the POST request
			    Response response = RestAssured
			            .given()
			            .contentType("application/json") // Ensure content type is correct
			            .body(petJsonObject.toString())
			            .post(BASE_URL);
		
			    // Validate response status
			    int statusCode = response.getStatusCode();
			    if (statusCode == 405) {
			        System.out.println("Error 405: Missing photos URLs");
			    } else {
			        System.out.println("Add Pet Response: " + response.getBody().asString());
			    }
		
			    // Asserting that the status code is 415 for this case (Optional depending on use case)
			    assert statusCode == 405 : "Expected status code 415, but got: " + statusCode;
			}
		    
		 }





