package api;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public abstract class BaseApi {

    public BaseApi(String baseUrl) {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(baseUrl);
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    protected ValidatableResponse get(String endpoint) {
        return given()
                .when()
                .get(endpoint)
                .then();
    }

    protected ValidatableResponse getWithQueryParam(String endpoint, String paramName, String paramValue) {
        return given()
                .queryParam(paramName, paramValue)
                .when()
                .get(endpoint)
                .then();
    }

    protected ValidatableResponse post(String endpoint, Object body) {
        return given()
                .body(body)
                .when()
                .post(endpoint)
                .then();
    }

    protected ValidatableResponse getWithAuth(String endpoint, String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(endpoint)
                .then();
    }

    protected ValidatableResponse getWithHeader(String endpoint, String headerName, String headerValue) {
        return given()
                .header(headerName, headerValue)
                .when()
                .get(endpoint)
                .then();
    }

    protected String extractBodyAsString(ValidatableResponse response) {
        return response.extract().asString();
    }

    protected String extractTokenFromResponse(ValidatableResponse response) {
        String responseString = extractBodyAsString(response);
        if (responseString.contains("token : ")) {
            return responseString.split("token : ")[1].trim();
        }
        return responseString;
    }
}
