package ru.ifellow.Kalinina;

import jiraPages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateBugTest extends WebHooks {
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboard = new DashboardPage();
    private final ProjectTestPage projectPage = new ProjectTestPage();
    private final CreatingTaskPage taskPage = new CreatingTaskPage();
    private final TestSeleniumATHomeworkPage task = new TestSeleniumATHomeworkPage();

    private final String username = CustomProperties.getProps().getProperty("username");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String checkingTheTest = "Лента активности";
    private final String checkingTheTest2 = "Открытые задачи";
    private final String checkingTheStatus = "ГОТОВО";

    private final String project = "Test (TEST)";
    private final String subject = "testt";
    private final String description = "Описание";
    private final String priority = "Low";
    private final String labels = "12345";
    private final String environment = "Окружение";
    private final String tasks = "TEST-210873";
    private final String epicLink = "EPIC";
    private final String sprint = "2";
    private final String status = "СДЕЛАТЬ";
    private final String version = "Version 2.0";
    private final String taskATHomework = "TestSeleniumATHomework";

    @Test
    @DisplayName("Проверка создания бага и его перевод в выполнено")
    void creatingBugWithDescriptionAndStatusTranslation() {
        loginPage.loginWithConfigCredentials(username, password);
        assertEquals(dashboard.getActivityFeedText(), checkingTheTest, "После авторизации не отображается 'Лента активности");
        dashboard.navigateToTestProject();
        assertEquals(checkingTheTest2, projectPage.getOpenTasksText(), "После перехода в проект не отображается надпись 'Открытые задачи'");

        projectPage.allTasks();
        projectPage.refreshTasks();

        int initialCount = projectPage.getTotalTasksCount();
        taskPage.createQuickTaskSimple(project, subject);
        projectPage.refreshTasks();

        projectPage.allTasks();

        int updatedCount = projectPage.getTotalTasksCount();
        assertEquals(initialCount + 1, updatedCount, "После создания бага, общее количество задач не увеличилось");

        projectPage.searchAndSubmit(taskATHomework);
        assertTrue(status.equals(task.getTaskStatus()), "Статус задачи не Сделать");
        assertEquals(version, task.getFixInVersions(), "Исправить в версиях не Version 2.0");

        taskPage.createBug(project, subject, description, priority, labels, environment, tasks, epicLink, sprint);
        projectPage.requestHasBeenCreate();
        projectPage.convertingTheTaskToCompleted();
        task.waitForStatus(checkingTheStatus);
        assertEquals(checkingTheStatus, task.getTaskStatus(), "Статус задачи не переведен в Готово");
    }
}
