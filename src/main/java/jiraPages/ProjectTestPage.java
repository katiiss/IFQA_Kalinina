package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectTestPage {
    private final SelenideElement switchFilterButton =$x("//button[@id='subnav-trigger']").as("Кнопка: Переключить фильтр");
    private final SelenideElement allTasksButton =$x("//div[@id='subnav-opts']//a[text()='Все задачи']").as("Кнопка: Все задачи");
    private final SelenideElement numberTasks =$x("//div[@class='showing']").as("Общее количество задач");
    private final SelenideElement updatingTasks =$x("//span[contains(@class,'aui-iconfont-refresh')]").as("Обновление задач");
    private final SelenideElement searchButton =$x("//input[@id='quickSearchInput']").as("Поиск");

    public void openFilterDropdown() {
        switchFilterButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    public void refreshTasks() {
        updatingTasks
                .shouldBe(Condition.visible, Duration.ofSeconds(25))
                .click();
    }

    public void selectAllTasks() {
        allTasksButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    public void searchAndSubmit(String query) {
        searchButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(query)
                .press(Keys.ENTER);
    }

    public int getTotalTasksCount() {
        // Ждем пока элемент появится и содержит текст
        String counterText = numberTasks
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.text("из"))
                .getText();

        System.out.println("Текст счетчика: " + counterText);
        return parseCounterText(counterText);
    }

    private int parseCounterText(String text) {
        String[] parts = text.split("из");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Не найден разделитель 'из' в тексте: " + text);
        }

        String numberStr = parts[1].trim().replaceAll("[^0-9]", "");

        if (numberStr.isEmpty()) {
            throw new IllegalArgumentException("Не удалось извлечь число из текста: " + text);
        }

        return Integer.parseInt(numberStr);
    }

}
