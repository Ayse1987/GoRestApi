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

public class PUT_userStepDef {

    Map<String,String> expectedData;
    Faker faker=new Faker();
    Response response;

    @And("user sets the expected data {string},{string},{string},{string}.")
    public void userSetsTheExpectedData(String name, String gender, String email, String status) {
        name=faker.name().fullName();
        email=faker.internet().emailAddress();
        expectedData=new HashMap<>();
        expectedData.put("name",name);
        expectedData.put("gender",gender);
        expectedData.put("email",email);
        expectedData.put("status",status);
    }
    @And("send put request")
    public void sendPutRequest() {
        String endpoint=ConfigurationReader.getProperty("go_rest_url")+"/"+ ReadTxt.getId();

        response=given().headers("Authorization", ConfigurationReader.getProperty("token_gorest"),
                "Content-Type", ContentType.JSON,
                "Accept",ContentType.JSON).body(expectedData).when()
                .put(endpoint);
        response.prettyPrint();
    }

    @Then("validate fully updated user")
    public void validateFullyUpdatedUser() {
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(ReadTxt.getId(),jsonPath.getString("id"));
        Assert.assertEquals(expectedData.get("name"),jsonPath.getString("name"));
        //name,email,status de eklenebilir
    }
}
