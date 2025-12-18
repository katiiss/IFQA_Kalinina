package utils;

import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class CustomProperties {
    private static final String AUTH_JSON = "src/main/resources/authorization.json";
    private static String originalAuthJson;

    @Getter
    private static Properties props = new Properties();

    public static void loadProperties() {
        try {
            props.load(new FileInputStream(new File("src/main/resources/config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static String readJson(String path) {
        return Files.readString(Paths.get(path));
    }

    public static String getAuthJson() {
        return readJson(AUTH_JSON);
    }

    public static String getOriginalAuthJson() {
        if (originalAuthJson == null) {
            originalAuthJson = getAuthJson();
        }
        return originalAuthJson;
    }

    @SneakyThrows
    public static String modifyUsername(String newUsername) {
        JSONObject json = new JSONObject(getOriginalAuthJson());
        json.put("username", newUsername);
        return json.toString();
    }

    @SneakyThrows
    public static String modifyPassword(String newPassword) {
        JSONObject json = new JSONObject(getOriginalAuthJson());
        json.put("password", newPassword);
        return json.toString();
    }

    @SneakyThrows
    public static void restoreOriginalAuthJson() {
        if (originalAuthJson != null) {
            Files.writeString(Paths.get(AUTH_JSON), originalAuthJson);
        }
    }
}
