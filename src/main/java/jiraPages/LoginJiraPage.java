package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginJiraPage {
    private final SelenideElement usernameField = $x("//input[@id='login-form-username']").as("Поле: Имя пользователя");
    private final SelenideElement passwordField = $x("//input[@id='login-form-password']").as("Поле: Пароль");
    private final SelenideElement loginButton = $x("//input[@id='login']").as("Кнопка: Войти");

    public DashboardPage loginWithConfigCredentials(String username, String password) {

        usernameField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(username);
        passwordField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(password);
        loginButton.click();

        return Selenide.page(DashboardPage.class);
    }
}
