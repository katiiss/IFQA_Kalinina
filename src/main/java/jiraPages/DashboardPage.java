package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement projectsButton = $x("//a[@id='browse_link']").as("Кнопка: Проекты");
    private final SelenideElement testButton = $x("//a[@id='admin_main_proj_link_lnk']").as("Кнопка: Test");
    private final SelenideElement activityFeedText = $x("//h3[@id='gadget-10003-title']").as("Лента активности");

    public ProjectTestPage navigateToTestProject() {
        projectsButton
                .shouldBe(visible, Duration.ofSeconds(10))
                .shouldBe(enabled, Duration.ofSeconds(5))
                .click();

        testButton
                .click();

        return Selenide.page(ProjectTestPage.class);
    }

    public String getActivityFeedText() {
        return activityFeedText
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .getText();
    }
}
