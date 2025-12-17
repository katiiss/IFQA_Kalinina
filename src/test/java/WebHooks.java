import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.CustomProperties;

public class WebHooks {
    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
    }
}
