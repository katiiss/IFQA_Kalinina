package api.rickAndMorty;

import api.Specifications;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import utils.CustomProperties;

public abstract class BaseRickAndMortyApi {
    public BaseRickAndMortyApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(CustomProperties.getProps().getProperty("base.url"));
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
