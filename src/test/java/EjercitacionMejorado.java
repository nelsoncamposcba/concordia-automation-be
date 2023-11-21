import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EjercitacionMejorado {
    RequestSpecification requestSpecification;

    @BeforeMethod
    public void setup(){
        requestSpecification =
                given()
                        .baseUri("https://reqres.in")
                        .contentType(ContentType.JSON)
                        .filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
    }

    @Test
    public void casoPruebaUno(){
        //https://reqres.in/api/users/1
        Response response =
                requestSpecification
                    .when()
                    .get("/api/users/1");

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200");
    }


    @Test
    public void casoPruebaDos(){
        Response response =
                requestSpecification
                        .when()
                        .get("/api/users/2");

        Assert.assertEquals(response.path("data.first_name"), "Janet", "El atributo data.first_name no es Janet");
    }

    @Test
    public void casoPruebaTres(){
        //https://reqres.in/api/users
        requestSpecification.
                given()
                        .body("{\n" +
                                "    \"name\": \"morpheus\",\n" +
                                "    \"job\": \"leader\"\n" +
                                "}");

        Response response =
                requestSpecification
                        .when()
                        .post("/api/users");

        Assert.assertEquals(response.statusCode(), 201, "El status code no es 201");
    }


    @Test
    public void casoPruebaCuarto(){
        //https://reqres.in/api/users/4
        requestSpecification.
                given()
                        .body("{\n" +
                                "    \"name\": \"morpheus\",\n" +
                                "    \"job\": \"zion resident\"\n" +
                                "}");

        Response response =
                requestSpecification
                        .when()
                        .put("/api/users/4");

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200");
        Assert.assertEquals(response.path("name"), "morpheus", "La propiedad name no se actualizo");
        Assert.assertEquals(response.path("job"), "zion resident", "La propiedad job no se actualizo");
    }

    @Test
    public void casoPruebaQuinto(){
        //https://reqres.in/api/users/5
        Response response =
                requestSpecification
                        .when()
                        .delete("/api/users/5");

        Assert.assertEquals(response.statusCode(), 204, "El status code no es 204");
    }

}
