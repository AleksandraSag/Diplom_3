package user;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class UserApi {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    public static final String CREATE_USER_API = "api/auth/register";
    public static final String LOGIN_API = "api/auth/login";
    public static final String DELETE_USER_API = "api/auth/user";

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
    @Step("Авторизация пользователя")
    public static ValidatableResponse userLogin(User user) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(LOGIN_API)
                .then();
    }
    @Step("Удаление пользователя")
    public static ValidatableResponse deleteUser(String bearerToken) {
        return given()
                .spec(requestSpecification())
                .headers("Authorization", bearerToken)
                .delete(DELETE_USER_API)
                .then()
                .statusCode(SC_ACCEPTED);
    }
    @Step("Регистрация пользователя")
    public ValidatableResponse userReg(User user) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(CREATE_USER_API)
                .then();
    }
}
