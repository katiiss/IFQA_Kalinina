import org.junit.jupiter.api.*;
import steps.AuthSteps;

import static org.hamcrest.core.IsEqual.equalTo;

public class AuthTest extends ApiHooks{
    private static final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    public void clearTokenBeforeTest() {
        authSteps.clearSavedToken();
    }

    @Test
    @DisplayName("Тест регистрации")
    void RegistrationTest() {
        authSteps.executeRegistration();
    }

    @Test
    @DisplayName("Авторизация:Не найден пользователь")
    void LoginUserNotFoundTest() {
        authSteps.executeRegistration();
        authSteps.executeLoginWithWrongUsername()
                .body(equalTo("not found"));
    }

    @Test
    @DisplayName("Авторизация:Пароль не верный")
    void LoginWrongPasswordTest() {
        authSteps.executeRegistration();
        authSteps.executeLoginWithWrongPassword()
                .body(equalTo("not right pass"));
    }

    @Test
    @DisplayName("Авторизация:Успешный сценарий")
    void LoginSuccessTest() {
        authSteps.executeRegistration();
        String token = authSteps.executeSuccessfulLogin();
        Assertions.assertFalse(token.isEmpty(), "Токен не должен быть пустым");

        String savedToken = authSteps.getSavedToken();
        Assertions.assertEquals(token, savedToken, "Токен должен быть сохранен");
    }

    @Test
    @DisplayName("Выход из учетки:Неуспешный сценарий")
    void LogoutUnauthorizedTest() {
        authSteps.executeRegistration();
        authSteps.executeLoginWithWrongPassword()
                .body(equalTo("not right pass"));
        authSteps.executeLogoutWithInvalidToken().body(equalTo("not found"));
    }

    @Test
    @DisplayName("Выход из учетки:Успешный сценарий")
    void LogoutSuccessTest() {
        authSteps.executeRegistration();
        String token = authSteps.executeSuccessfulLogin();
        Assertions.assertFalse(token.isEmpty(), "Токен не должен быть пустым");

        String savedToken = authSteps.getSavedToken();
        Assertions.assertEquals(token, savedToken, "Токен должен быть сохранен");

        authSteps.executeSuccessfulLogout();
        authSteps.clearSavedToken();
        Assertions.assertNull(authSteps.getSavedToken(),
                "Токен должен быть равен null после выхода из системы и очистки");
    }

}
