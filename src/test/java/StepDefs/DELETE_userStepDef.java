package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationReader;
import utilities.ReadTxt;

import static io.restassured.RestAssured.given;

public class DELETE_userStepDef {

    Response response;

    @And("send delete request")
    public void sendDeleteRequest() {
        String endpoint=ConfigurationReader.getProperty("go_rest_url")+"/"+ ReadTxt.getId();

        response=given().headers("Authorization", ConfigurationReader.getProperty("token_gorest"),
                "Content-Type", ContentType.JSON,
                "Accept",ContentType.JSON).when()
                .delete(endpoint);
    }

    @Then("validate status Code is {int}")
    public void validateStatusCodeIs(int statusCode) {
        response.then().statusCode(statusCode);
    }
}
