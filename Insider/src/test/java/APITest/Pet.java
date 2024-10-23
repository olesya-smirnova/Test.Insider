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

public class Pet {

	// Base URL for the Pet Store API
	public static final String BASE_URL = "https://petstore.swagger.io/v2/pet";

	@DataProvider(name = "testjson")
	public static Object[][] getfile() {

		String jsontest = "C:\\jsontest.json";
		JSONObject o = null;
		try {
			String contents = new String((Files.readAllBytes(Paths.get(jsontest))));
			o = new JSONObject(contents);

			int id = o.getInt("id");

			JSONObject category = o.getJSONObject("category");

			String name = o.getString("name");

			JSONArray photoUrls = o.getJSONArray("photoUrls");

			JSONArray tags = o.getJSONArray("tags");

			String status = o.getString("status");
			System.out.println(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Object[][] { { o } };
	}

	@Test(dataProvider = "testjson", priority = 1)

	// Send POST request

	private void addPet(JSONObject o) {

		Response response = RestAssured.given().contentType("application/json").body(o.toString()).post(BASE_URL);

		// Validate response
		System.out.println("Add Pet Response: " + response.getBody().asString());
		assert response.getStatusCode() == 200;
	}

	private static final int TEST_PET_ID = 12345; // Define a test pet ID

	@Test(priority = 2) // Get a Pet by ID

	public void getPetById() {
		Response response = RestAssured.given().pathParam("petId", TEST_PET_ID).get(BASE_URL + "/{petId}");

		// Validate response
		System.out.println("Get Pet Response: " + response.getBody().asString());
		assert response.getStatusCode() == 200;
	}

	@Test(priority = 3) // Update an Existing Pet
	public void updatePet() {
		// Create JSON object for updating the pet
		JSONObject petUpdate = new JSONObject();
		petUpdate.put("id", TEST_PET_ID); // Use the test pet ID
		petUpdate.put("name", "Buddy Updated");
		petUpdate.put("status", "sold");
	    JSONArray photoUrls = new JSONArray();
	    photoUrls.put("https://example.com/photo1.jpg");
	    photoUrls.put("https://example.com/photo2.jpg");
	    photoUrls.put("https://example.com/photo3.jpg");		
		petUpdate.put("photoUrls", photoUrls);
		// Send PUT request
		Response response = RestAssured.given().contentType("application/json").body(petUpdate.toString())
				.put(BASE_URL);

		// Validate response
		System.out.println("Update Pet Response: " + response.getBody().asString());
		assert response.getStatusCode() == 200;
	}

	@Test(priority = 4) // Delete an Existing Pet
	public void deletePetById() {
		Response response = RestAssured.given().pathParam("petId", TEST_PET_ID).delete(BASE_URL + "/{petId}");

		// Validate response
		System.out.println("Get Pet Response: " + response.getBody().asString());
		assert response.getStatusCode() == 200;
	}

}