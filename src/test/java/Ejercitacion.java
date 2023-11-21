import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import static io.restassured.RestAssured.given;

public class Ejercitacion {

    @Test
    public void casoPruebaUno(){
        //https://reqres.in/api/users/1
        RequestSpecification requestSpecification =
                given()
                        .baseUri("https://reqres.in");

        requestSpecification.filter(new RequestLoggingFilter());
        requestSpecification.filter(new ResponseLoggingFilter());

        String id = "1";

        Response response =
                requestSpecification
                    .when()
                    .get("/api/users/" + id);

        //Validacion --> Status Code 200
        //requestSpecification
        //        .then()
        //        .statusCode(200);

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200");
    }
}
