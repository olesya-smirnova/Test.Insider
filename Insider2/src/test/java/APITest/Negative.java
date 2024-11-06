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
import static org.testng.Assert.assertEquals;

public class Negative {

	// Base URL for the Pet Store API
	public static final String BASE_URL = configuration().url();
	private static final int FAKE_PET_ID = Integer.parseInt(configuration().fakeptid());

    @DataProvider(name = "testjson")
    public static Object[][] getfile() {
        String jsontest = configuration().pathnegativecase();
        JSONObject jsonObject = null;

        try {
            String contents = new String(Files.readAllBytes(Paths.get(jsontest)));
            jsonObject = new JSONObject(contents);
            System.out.println("Status: " + jsonObject.getString("status"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Object[][]{{jsonObject}};
    }

    @Test(dataProvider = "testjson", priority = 1)
    public void getPetById(JSONObject jsonObject) {
        Response response = RestAssured.given().pathParam("petId", FAKE_PET_ID).get(BASE_URL + "/{petId}");

        // Validate response
           if (response.getStatusCode() == 405) {
                System.out.println("Error 405: Missing pet's id");
            } else {
                System.out.println("Get Pet Response: " + response.getBody().asString());
            } 
    }
}