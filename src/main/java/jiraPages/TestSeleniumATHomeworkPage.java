package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class TestSeleniumATHomeworkPage {
    private final SelenideElement taskStatus = $x("//span[@id='status-val']").as("Статус задачи");
    private final SelenideElement fixInVersions = $x("//span[@id='fixVersions-field']/a").as("Исправить в версиях");

    @Step("Получить текущий статус задачи")
    public String getTaskStatus() {
        return taskStatus
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .getText();
    }

    @Step("Получить версию для исправления")
    public String getFixInVersions() {
        return fixInVersions
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .getText();
    }

    @Step("Ожидать статус задачи: {expectedStatus}")
    public void waitForStatus(String expectedStatus) {
        taskStatus.shouldHave(Condition.text(expectedStatus), Duration.ofSeconds(10));
    }
}
