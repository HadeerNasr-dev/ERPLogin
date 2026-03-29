package testlogin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;


public class LoginTests {
    @BeforeClass
    public void setup() {
        // Base URI for ERP
        RestAssured.baseURI = "http://69.10.56.98/backend";
    }

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginData validUser = new LoginData( "gepid57587@besenica.com", "Asd@123456", 24);

        given()
                .contentType(ContentType.JSON)
                .body(validUser, ObjectMapperType.JACKSON_2)// Jackson serializes this POJO automatically
                .log().all()
                .when()
                .post("/api/Authenticate/Login")
                .then()
                .body("Response.IsSucceeded", equalTo(true))
                .body("Value.Token",notNullValue()) // Ensure a token is returned
                .body("Value.Message", equalTo(null))
                .log().all();
    }

    @Test(description = "Verify login failure with incorrect password")
    public void testLoginWithWrongPassword() {
        LoginData invalidUser = new LoginData("gepid57587@besenica.com", "468676", 24);

        given()
                .contentType(ContentType.JSON)
                .body(invalidUser)
                .log().all()
                .when()
                .post("/api/Authenticate/Login")
                .then()
                .body("Response.ErrorDescription", equalTo("InvalidPassword")) //Check Error message
                .log().all();
    }

    @Test(description = "Verify login failure with non-existent user")
    public void testLoginWithInvalidUser() {
        LoginData ghostUser = new LoginData("gepid@besenica.com", "Asd@123456", 24);

        given()
                .contentType(ContentType.JSON)
                .body(ghostUser)
                .log().all()
                .when()
                .post("/api/Authenticate/Login")
                .then()
                .body("Response.IsSucceeded", equalTo(false)) //Check IsSucceeded key
                .log().all();
    }
}

