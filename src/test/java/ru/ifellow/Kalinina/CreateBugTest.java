package ru.ifellow.Kalinina;

import jiraPages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateBugTest extends WebHooks{
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboard = new DashboardPage();
    private final ProjectTestPage projectPage = new ProjectTestPage();
    private final CreatingTaskPage taskPage = new CreatingTaskPage();
    private final TestSeleniumATHomeworkPage task = new TestSeleniumATHomeworkPage();

    private final String username = CustomProperties.getProps().getProperty("username");
    private final String password = CustomProperties.getProps().getProperty("password");

    private final String project = "Test (TEST)";
    private final String subject = "testsss";
    private final String description = "Описание";
    private final String priority = "Low";
    private final String labels = "12345";
    private final String environment = "Окружение";
    private final String tasks = "test";
    private final String epicLink = "Epic";
    private final String sprint = "2";
    private final String status = "СДЕЛАТЬ";
    private final String version = "Version 2.0";

    @Test
    @DisplayName("Проверка создания бага и его перевод в выполнено")
    void creatingBugWithDescriptionAndStatusTranslation(){
        loginPage.loginWithConfigCredentials(username, password);
        dashboard.navigateToTestProject();

        projectPage.allTasks();
        projectPage.refreshTasks();

        int initialCount = projectPage.getTotalTasksCount();
        taskPage.createQuickTaskSimple(project, subject);
        projectPage.refreshTasks();

        projectPage.allTasks();

        int updatedCount = projectPage.getTotalTasksCount();
        assertEquals(initialCount + 1, updatedCount);

        assertEquals(status, task.getTaskStatus());
        assertTrue(task.getFixInVersions().contains(version));

        taskPage.createBug(project, subject, description, priority, labels, environment, tasks, epicLink, sprint);
        projectPage.requestHasBeenCreate();
        projectPage.convertingTheTaskToCompleted();
    }
}
