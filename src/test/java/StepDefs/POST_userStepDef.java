package StepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigurationReader;
import utilities.ReadTxt;
import utilities.WriteToTxt;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class POST_userStepDef {
    Faker faker=new Faker();
    Map<String,String> expectedData;
    HashMap<String,String> actualData;
    Response response;

    @Given("user sets up endpoint.")
    public void userSetsUpEndpoint() {
        //spec de kullanilabilir

    }
    @Given("user sets the expected data {string},{string},{string},{string}")
    public void user_sets_the_expected_data(String name, String gender, String email, String status) {
        name=faker.name().fullName();
        email=faker.internet().emailAddress();
        expectedData=new HashMap<>();
        expectedData.put("name",name);
        expectedData.put("gender",gender);
        expectedData.put("email",email);
        expectedData.put("status",status);
    }
    @And("user send the request and get the response.")
    public void userSendTheRequestAndGetTheResponse() {
        response=given().headers("Authorization",ConfigurationReader.getProperty("token_gorest"),
                "Content-Type",ContentType.JSON,
                "Accept",ContentType.JSON).body(expectedData).when()
                .post(ConfigurationReader.getProperty("go_rest_url"));
        response.prettyPrint();

//
    }
    @When("user saves the new user's record to correspondent files")
    public void userSavesTheNewUserSRecordToCorrespondentFiles() throws IOException {
        ObjectMapper obj = new ObjectMapper();
//
         actualData= obj.readValue(response.asString(), HashMap.class);
         WriteToTxt.saveRegistrantData(actualData);
         System.out.println(ReadTxt.getId());

    }
    @Then("user validates new user created")
    public void user_validates_new_user_created() {
        response.then().statusCode(201);
        Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
        //digerlrini de ekleyebilirsiniz


    }













}
