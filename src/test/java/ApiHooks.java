import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.CustomProperties;

public class ApiHooks {

    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
        CustomProperties.getOriginalAuthJson();
    }

    @AfterAll
    public static void restoreCredentials() {
        CustomProperties.restoreOriginalAuthJson();
    }
}
