package jiraSteps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import jiraPages.DashboardPage;
import jiraPages.LoginJiraPage;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginJiraSteps {
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    @Когда("пользователь авторизуется с логином и паролем")
    public void userLoginsWithConfigCredentials() {
        String username = CustomProperties.getProps().getProperty("username");
        String password = CustomProperties.getProps().getProperty("password");
        loginPage.loginWithConfigCredentials(username, password);
    }

    @Тогда("открывается главная страница")
    public void userSuccessfullyLoggedIn() {
        String checkingTheTest = CustomProperties.getProps().getProperty("checkingTheTest");
        String actualText = dashboardPage.getActivityFeedText();
        assertEquals(actualText, checkingTheTest, "После авторизации не отображается 'Лента активности");
    }
}
