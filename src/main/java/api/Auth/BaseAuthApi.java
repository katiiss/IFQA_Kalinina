package api.Auth;

import api.Specifications;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import utils.CustomProperties;

public abstract class BaseAuthApi {
    protected static String authToken;
    protected static final String BASE_URL = CustomProperties.getProps().getProperty("baseAuth.url");

    public BaseAuthApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(BASE_URL);
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    protected void setupWithAuth() {
        if (authToken != null && !authToken.isEmpty()) {
            RestAssured.requestSpecification = Specifications.requestWithAuthSpec(BASE_URL, authToken);
        }
    }

    /**
     * Метод для сброса настройки к базовой (без авторизации)
     */
    protected void resetToBaseAuth() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(BASE_URL);
    }

    /**
     * Получение файла с учетными данными
     */
    protected File getCredentialsFile() {
        return new File(CREDENTIALS_FILE_PATH);
    }

    /**
     * Сохранение токена
     */
    protected void setAuthToken(String token) {
        authToken = token;
    }

    /**
     * Получение токена
     */
    protected String getAuthToken() {
        return authToken;
    }

    /**
     * Очистка токена
     */
    protected void clearAuthToken() {
        authToken = null;
    }
}
