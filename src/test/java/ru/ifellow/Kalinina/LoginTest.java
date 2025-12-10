package ru.ifellow.Kalinina;

import jiraPages.DashboardPage;
import jiraPages.LoginJiraPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CustomProperties;


public class LoginTest extends WebHooks {
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboard = new DashboardPage();

    private final String username = CustomProperties.getProps().getProperty("username");
    private final String password = CustomProperties.getProps().getProperty("password");

    @Test
    @DisplayName("Тест авторизации")
    void successfulLoginTest() {
        loginPage.loginWithConfigCredentials(username, password);
    }
}

