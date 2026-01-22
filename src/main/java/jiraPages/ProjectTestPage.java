package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectTestPage {
    private final SelenideElement switchFilterButton = $x("//button[@id='subnav-trigger']").as("Кнопка: Переключить фильтр");
    private final SelenideElement allTasksButton = $x("//div[@id='subnav-opts']//a[text()='Все задачи']").as("Кнопка: Все задачи");
    private final SelenideElement numberTasks = $x("//div[@class='showing']").as("Общее количество задач");
    private final SelenideElement updatingTasks = $x("//span[contains(@class,'aui-iconfont-refresh')]").as("Обновление задач");
    private final SelenideElement searchButton = $x("//input[@id='quickSearchInput']").as("Поиск");
    private final SelenideElement businessProcessButton = $x("//a[@id='opsbar-transitions_more']").as("Кнопка бизнес-процесс");
    private final SelenideElement completedButton = $x("//span [text()='Выполнено']").as("Перевод задачи в выполнено");
    private final SelenideElement requestHasBeenCreatedWindow = $x("//a[@class='issue-created-key issue-link']").as("Окно с номером бага");
    private final SelenideElement openTasks = $x("//span [@id='issues-subnavigation-title']").as("Открытые задачи");

    @Step("Переключить на все задачи")
    public void allTasks() {
        switchFilterButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        allTasksButton
                .click();
    }

    @Step("Открыть созданную задачу по ссылке")
    public void requestHasBeenCreate() {
        requestHasBeenCreatedWindow
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    @Step("Перевести задачу в статус 'Выполнено'")
    public void convertingTheTaskToCompleted() {
        businessProcessButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        completedButton
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .click();
    }

    @Step("Обновить список задач")
    public void refreshTasks() {
        updatingTasks
                .shouldBe(Condition.visible, Duration.ofSeconds(25))
                .click();
    }

    @Step("Выполнить поиск по запросу: {query}")
    public void searchAndSubmit(String query) {
        searchButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(query)
                .press(Keys.ENTER);
    }

    @Step("Получить общее количество задач")
    public int getTotalTasksCount() {
        String counterText = numberTasks
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.text("из"))
                .getText();
        return parseCounterText(counterText);
    }

    @Step("Разделить текст счетчика: {text}")
    private int parseCounterText(String text) {
        String[] parts = text.split("из");
        String numberStr = parts[1].trim();
        return Integer.parseInt(numberStr);
    }

    @Step("Получить текст заголовка открытых задач")
    public String getOpenTasksText() {
        return openTasks
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .getText();
    }
}
