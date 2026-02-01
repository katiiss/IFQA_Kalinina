package utils;

import lombok.SneakyThrows;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonEdit {
    private static final String AUTH_JSON = CustomProperties.getProps().getProperty("auth.json.path");
    private static String originalAuthJson;

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

    public static String modifyUsername(String newUsername) {
        JSONObject json = new JSONObject(getOriginalAuthJson());
        json.put("username", newUsername);
        return json.toString();
    }

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
