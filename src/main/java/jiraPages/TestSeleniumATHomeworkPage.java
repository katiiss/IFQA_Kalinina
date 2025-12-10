package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class TestSeleniumATHomeworkPage {
    private final SelenideElement taskStatus = $x("//span[@id='status-val']").as("Статус задачи");
    private final SelenideElement fixInVersions = $x("//span[@id='fixfor-val']").as("Исправить в версиях");

    public String getTaskStatus() {
        return taskStatus
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .getText();
    }

    public String getFixInVersions() {
        return fixInVersions
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .getText();
    }
}
