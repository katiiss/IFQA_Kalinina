package steps;

import api.AuthApi;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.apache.http.HttpStatus;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;

public class AuthSteps {
    private static final AuthApi authApi = new AuthApi();

    @Когда("очищен сохраненный токен")
    public void clearSavedToken() {
        authApi.clearToken();
    }

    @Тогда("пользователь выполняет регистрацию")
    public void executeRegistration() {
        authApi.postRegister()
                .statusCode(HttpStatus.SC_OK);
    }

    @Когда("пользователь пытается авторизоваться с неверным логином и возвращается сообщение 'not found'")
    public void executeLoginWithWrongUsername() {
        authApi.postLoginWithWrongUsername()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(equalTo("not found"));
    }

    @Когда("пользователь пытается авторизоваться с неверным паролем и возвращается сообщение 'not right pass'")
    public void executeLoginWithWrongPassword() {
        authApi.postLoginWithWrongPassword()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(IsEqual.equalTo("not right pass"));
    }

    @Когда("пользователь авторизуется с правильными данными")
    public void executeSuccessfulLogin() {
        String token = authApi.postLoginAndSaveToken();
        assertFalse(token.isEmpty(), "Токен не должен быть пустым");

        String savedToken = authApi.getStoredToken();
        assertEquals(token, savedToken, "Токен должен быть сохранен");
    }

    @Когда("пользователь пытается выйти из системы с невалидным токеном, происходит ошибка авторизации и возвращается сообщение 'not found'")
    public void executeLogoutWithInvalidToken() {
        authApi.getLogoutWithInvalidToken()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(IsEqual.equalTo("not found"));
    }

    @Когда("пользователь выходит из системы с валидным токеном")
    public void executeSuccessfulLogout() {
        authApi.getLogoutWithSavedToken()
                .statusCode(HttpStatus.SC_OK);

        authApi.clearToken();
        Assertions.assertNull(authApi.getStoredToken(),
                "Токен должен быть равен null после выхода из системы и очистки");
    }
}
