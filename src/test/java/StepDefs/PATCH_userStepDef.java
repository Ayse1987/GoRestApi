package StepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigurationReader;
import utilities.ReadTxt;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PATCH_userStepDef {

    Map<String,String> expectedDataPatch;
    Faker faker=new Faker();
    Response response;

    @And("user sets the expected data {string},{string},{string}")
    public void userSetsTheExpectedData(String name, String email, String status) {
        name=faker.name().fullName();
        email=faker.internet().emailAddress();
        expectedDataPatch=new HashMap<>();
        expectedDataPatch.put("name",name);
        expectedDataPatch.put("email",email);
        expectedDataPatch.put("status",status);
    }
    @And("send patch request")
    public void sendPatchRequest() {
        String endpoint=ConfigurationReader.getProperty("go_rest_url")+"/"+ ReadTxt.getId();

        response=given().headers("Authorization", ConfigurationReader.getProperty("token_gorest"),
                "Content-Type", ContentType.JSON,
                "Accept",ContentType.JSON).body(expectedDataPatch).when()
                .patch(endpoint);
        response.prettyPrint();
    }

    @Then("validate updated user")
    public void validateUpdatedUser() {
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(ReadTxt.getId(),jsonPath.getString("id"));
        //digerlerini ekleyebilirsiniz
    }
}
