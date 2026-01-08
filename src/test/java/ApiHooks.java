import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.CustomProperties;
import utils.JsonEdit;

public class ApiHooks {

    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
        JsonEdit.getOriginalAuthJson();
    }

    @AfterAll
    public static void restoreCredentials() {
        JsonEdit.restoreOriginalAuthJson();
    }
}
