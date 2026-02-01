package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.SetValueOptions.withText;

public class LoginJiraPage {
    private final SelenideElement usernameField = $x("//input[@id='login-form-username']").as("Поле: Имя пользователя");
    private final SelenideElement passwordField = $x("//input[@id='login-form-password']").as("Поле: Пароль");
    private final SelenideElement loginButton = $x("//input[@id='login']").as("Кнопка: Войти");

    @Step("Авторизоваться в Jira с логином и паролем")
    public void loginWithConfigCredentials(String username, @Param(mode = Parameter.Mode.MASKED) String password) {
        usernameField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(username);
        passwordField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(withText(password).sensitive());
        loginButton.click();
        Selenide.page(DashboardPage.class);
    }
}
