package ru.ifellow.Kalinina;

import jiraPages.CreatingTaskPage;
import jiraPages.DashboardPage;
import jiraPages.LoginJiraPage;
import jiraPages.ProjectTestPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskCounterTest extends WebHooks {
    @Test
    void testTaskCounterWithQuickCreation() {
        // 1. Авторизация
        LoginJiraPage loginPage = new LoginJiraPage();
        loginPage.loginWithConfigCredentials();

        DashboardPage dashboard = new DashboardPage();
        ProjectTestPage projectPage = dashboard.navigateToTestProject();

        // 3. ОТКРЫВАЕМ ФИЛЬТР И ВЫБИРАЕМ "ВСЕ ЗАДАЧИ" ← ЭТО ВАЖНО!
        projectPage.openFilterDropdown();
        projectPage.selectAllTasks();
        projectPage.refreshTasks();

        // 4. Получаем начальный счетчик
        int initialCount = projectPage.getTotalTasksCount();
        System.out.println("Начальное количество задач: " + initialCount);

        // 5. Создаем задачу
        CreatingTaskPage taskPage = new CreatingTaskPage();
        taskPage.createQuickTaskSimple("Test (TEST)", "Ошибка", "test");

        // 6. Ждем обновления
        projectPage.refreshTasks();

        // 7. СНОВА ОТКРЫВАЕМ ФИЛЬТР И ВЫБИРАЕМ "ВСЕ ЗАДАЧИ" ← И ТУТ ТОЖЕ!
        projectPage.openFilterDropdown();
        projectPage.selectAllTasks();

        // 8. Получаем обновленный счетчик
        int updatedCount = projectPage.getTotalTasksCount();
        System.out.println("Обновленное количество задач: " + updatedCount);

        // 9. Проверяем
        assertEquals(initialCount + 1, updatedCount,
                "Счетчик должен увеличиться на 1 после создания задачи");
    }
}
