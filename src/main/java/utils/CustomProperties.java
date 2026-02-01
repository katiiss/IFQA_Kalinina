package utils;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CustomProperties {
    @Getter
    private static Properties props = new Properties();

    public static void loadProperties() {
        try {
            props.load(new FileInputStream(CustomProperties.getProps().getProperty("config.path")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
