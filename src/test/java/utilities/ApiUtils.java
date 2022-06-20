package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiUtils {
    //Bu methodu kullanmadim, ama bu sekilde reusable methodlar olusturup da kullanabilirsiniz. Ornek olsun
    public static Response getRequest(String token,String endpoint){
        Response response=given().headers("Authorization",token,"Content-Type",ContentType.JSON,
                "Accept",ContentType.JSON).when().get(endpoint);
        return response;
    }
}
