package api.Auth;

import api.Specifications;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import utils.CustomProperties;

@Getter
public abstract class BaseAuthApi {
    protected static final String BASE_URL = CustomProperties.getProps().getProperty("baseAuth.url");
    @Setter
    @Getter
    protected static String authToken;

    public BaseAuthApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(BASE_URL);
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    @SneakyThrows
    protected String extractToken(String response) {
        if (response == null || response.isBlank()) {
            return null;
        }
        response = response.trim();
        if (response.startsWith("{")) {
            org.json.JSONObject json = new org.json.JSONObject(response);
            return json.optString("token", null);
        }
        String[] patterns = {"token :", "token:"};
        for (String pattern : patterns) {
            if (response.contains(pattern)) {
                return response.split(pattern)[1].trim();
            }
        }
        return response;
    }

    public static void clearToken() {
        authToken = null;
    }
}
