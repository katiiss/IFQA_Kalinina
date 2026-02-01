package utils;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class CustomProperties {
    @Getter
    private static Properties props = new Properties();

    public static void loadProperties() {
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream("src/test/resources/config.properties"),
                StandardCharsets.UTF_8)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
