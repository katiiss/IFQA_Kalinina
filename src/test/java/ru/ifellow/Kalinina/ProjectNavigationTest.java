package ru.ifellow.Kalinina;

import jiraPages.DashboardPage;
import jiraPages.LoginJiraPage;
import jiraPages.ProjectTestPage;
import org.junit.jupiter.api.Test;

public class ProjectNavigationTest extends WebHooks {

    @Test
    void testNavigateToTestProjectPage() {
        LoginJiraPage loginPage = new LoginJiraPage();
        loginPage.loginWithConfigCredentials();

        DashboardPage dashboard = new DashboardPage();

        ProjectTestPage testProject = dashboard.navigateToTestProject();
        System.out.println("✅ Успешно перешли на страницу проекта: ");

    }
}
