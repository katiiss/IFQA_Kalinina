package steps;

import api.AuthApi;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

public class AuthSteps {
    private static final AuthApi authApi = new AuthApi();

    public void executeRegistration() {
        authApi.postRegister()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse executeLoginWithWrongUsername() {
        return authApi.postLoginWithWrongUsername()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    public ValidatableResponse executeLoginWithWrongPassword() {
        return authApi.postLoginWithWrongPassword()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    public String executeSuccessfulLogin() {
        return authApi.postLoginAndSaveToken();
    }

    public ValidatableResponse executeLogoutWithInvalidToken() {
        return authApi.getLogoutWithInvalidToken()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    public void executeSuccessfulLogout() {
        authApi.getLogoutWithSavedToken()
                .statusCode(HttpStatus.SC_OK);
    }

    public String getSavedToken() {
        return authApi.getStoredToken();
    }

    public void clearSavedToken() {
        authApi.clearToken();
    }
}
