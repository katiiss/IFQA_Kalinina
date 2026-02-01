package hooks;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import utils.CustomProperties;
import utils.JsonEdit;

public class Hooks {

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