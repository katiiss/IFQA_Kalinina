package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.CustomProperties;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginJiraPage {
    private final SelenideElement usernameField =$x("//input[@id='login-form-username']").as("Поле: Имя пользователя");
    private final SelenideElement passwordField =$x("//input[@id='login-form-password']").as("Поле: Пароль");
    private final SelenideElement loginButton =$x("//input[@id='login']").as("Кнопка: Войти");

    public DashboardPage loginWithConfigCredentials() {
        String username = CustomProperties.getProps().getProperty("username");
        String password = CustomProperties.getProps().getProperty("password");

        usernameField.shouldBe(Condition.visible, Duration.ofSeconds(15));
        passwordField.shouldBe(Condition.visible, Duration.ofSeconds(15));

        usernameField.setValue(username);
        passwordField.setValue(password);

        loginButton.shouldBe(Condition.enabled, Duration.ofSeconds(10)).click();

        return Selenide.page(DashboardPage.class);
    }

}
