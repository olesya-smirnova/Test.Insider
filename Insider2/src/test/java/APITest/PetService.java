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

import static com.api.config.ConfigurationRequest.configuration;

public class PetService {
    
	public static final String BASE_URL = configuration().url();
		
	
    public Response addPet(JSONObject petData) {
        return RestAssured.given()
                .contentType("application/json")
                .body(petData.toString())
                .post(BASE_URL);
    }

    public Response getPetById(int petId) {
        return RestAssured.given()
                .pathParam("petId", petId)
                .get(BASE_URL + "/{petId}");
    }

    public Response updatePet(JSONObject petData) {
        return RestAssured.given()
                .contentType("application/json")
                .body(petData.toString())
                .put(BASE_URL);
    }

    public Response deletePetById(int petId) {
        return RestAssured.given()
                .pathParam("petId", petId)
                .delete(BASE_URL + "/{petId}");
    }
}
