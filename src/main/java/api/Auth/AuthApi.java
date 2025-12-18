package api.Auth;

import io.restassured.response.ValidatableResponse;
import utils.CustomProperties;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseAuthApi {
    private static final String REGISTER = "/register";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";

    public ValidatableResponse postRegister() {
        return given()
                .body(CustomProperties.getOriginalAuthJson())
                .post(REGISTER)
                .then();
    }

    public ValidatableResponse postLogin() {
        return given()
                .body(CustomProperties.getOriginalAuthJson())
                .post(LOGIN)
                .then();
    }

    public ValidatableResponse postLoginWithWrongUsername() {
        return given()
                .body(CustomProperties.modifyUsername("wrong_user"))
                .post(LOGIN)
                .then();
    }

    public ValidatableResponse postLoginWithWrongPassword() {
        return given()
                .body(CustomProperties.modifyPassword("wrong_pass"))
                .post(LOGIN)
                .then();
    }

    public String postLoginAndSaveToken() {
        String response = postLogin()
                .statusCode(200)
                .extract()
                .asString();

        String token = extractToken(response);
        setAuthToken(token);
        return token;
    }

    public ValidatableResponse getLogoutWithInvalidToken() {
        UUID randomUuid = UUID.randomUUID();
        return given()
                .header("Authorization", randomUuid.toString())
                .get(LOGOUT)
                .then();
    }

    public ValidatableResponse getLogoutWithSavedToken() {
        String token = getAuthToken();
        return given()
                .header("Authorization", token)
                .get(LOGOUT)
                .then();
    }
}
