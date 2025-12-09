package ru.ifellow.Kalinina;

import jiraPages.DashboardPage;
import jiraPages.LoginJiraPage;
import org.junit.jupiter.api.Test;


public class LoginTest extends WebHooks {

    @Test
    void successfulLoginTest() {
        LoginJiraPage loginPage = new LoginJiraPage();
        DashboardPage dashboard = loginPage.loginWithConfigCredentials();
        System.out.println("✅ Тест авторизации пройден успешно!");
    }
}

