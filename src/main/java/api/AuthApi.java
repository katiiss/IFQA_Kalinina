package api;

import io.restassured.response.ValidatableResponse;
import utils.CustomProperties;
import utils.JsonEdit;

import java.util.UUID;

public class AuthApi extends BaseApi {
    private static final String REGISTER = CustomProperties.getProps().getProperty("register");
    private static final String LOGIN = CustomProperties.getProps().getProperty("login");
    private static final String LOGOUT = CustomProperties.getProps().getProperty("logout");
    private static final String AUTH_URL = CustomProperties.getProps().getProperty("authUrl");
    private static String storedToken;

    public AuthApi() {
        super(AUTH_URL);
    }

    public ValidatableResponse postRegister() {
        return post(REGISTER, JsonEdit.getOriginalAuthJson());
    }

    public ValidatableResponse postLogin() {
        return post(LOGIN, JsonEdit.getOriginalAuthJson());
    }

    public ValidatableResponse postLoginWithWrongUsername() {
        return post(LOGIN, JsonEdit.modifyUsername("wrong_user"));
    }

    public ValidatableResponse postLoginWithWrongPassword() {
        return post(LOGIN, JsonEdit.modifyPassword("wrong_pass"));
    }

    public String postLoginAndSaveToken() {
        ValidatableResponse response = postLogin();
        response.statusCode(200);

        String token = extractTokenFromResponse(response);
        saveToken(token);
        return token;
    }

    public ValidatableResponse getLogoutWithInvalidToken() {
        return getWithHeader(LOGOUT, "Authorization", UUID.randomUUID().toString());
    }

    public ValidatableResponse getLogoutWithSavedToken() {
        String token = getStoredToken();
        return getWithAuth(LOGOUT, token);
    }

    protected void saveToken(String token) {
        storedToken = token;
    }

    public String getStoredToken() {
        return storedToken;
    }

    public void clearToken() {
        storedToken = null;
    }
}
