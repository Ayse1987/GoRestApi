package StepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationReader;
import utilities.ReadTxt;

import static io.restassured.RestAssured.given;

public class GET_userStepDef {
    Response response;



    @Given("set up get endpoint")
    public void set_up_get_endpoint() {
        //spec kullanilabilir
       // String id= ReadTxt.getId().substring(4);


    }
    @Given("send get request")
    public void send_get_request() {

        String endpoint=ConfigurationReader.getProperty("go_rest_url")+"/"+ReadTxt.getId();

        response=given().headers("Authorization", ConfigurationReader.getProperty("token_gorest"),
                "Content-Type", ContentType.JSON,
                "Accept",ContentType.JSON).when()
                .get(endpoint);
        response.prettyPrint();
    }
    @Then("validate the user")
    public void validate_the_user() {
        response.then().statusCode(200);

    }

}
