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

public class Pet {

	private PetService petService = new PetService();  // Instantiate PetService
	
    @DataProvider(name = "testjson")
    public static Object[][] getFile() {
        String jsontest = configuration().path();
        JSONObject jsonObject = null;
        try {
            String contents = new String((Files.readAllBytes(Paths.get(jsontest))));
            jsonObject = new JSONObject(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Object[][]{{jsonObject}};
    }
    private static final int TEST_PET_ID = Integer.parseInt(configuration().petid());  // Define a test pet ID
    public static final String petnewname = configuration().petnewname();
    public static final String petstatus = configuration().petstatus();
        

    @Test(dataProvider = "testjson", priority = 1)
    void addPet(JSONObject petData) {
        Response response = petService.addPet(petData);
        System.out.println("Add Pet Response: " + response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    void getPetById() {
        Response response = petService.getPetById(TEST_PET_ID);
        System.out.println("Get Pet Response: " + response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    void updatePet() {
        JSONObject petUpdate = new JSONObject();
        petUpdate.put("id", TEST_PET_ID);
        petUpdate.put("name", petnewname);
        petUpdate.put("status", petstatus);

        JSONArray photoUrls = new JSONArray();
        photoUrls.put("https://example.com/photo1.jpg");
        photoUrls.put("https://example.com/photo2.jpg");
        photoUrls.put("https://example.com/photo3.jpg");
        petUpdate.put("photoUrls", photoUrls);

        Response response = petService.updatePet(petUpdate);
        System.out.println("Update Pet Response: " + response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    void deletePetById() {
        Response response = petService.deletePetById(TEST_PET_ID);
        System.out.println("Delete Pet Response: " + response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
    }
}