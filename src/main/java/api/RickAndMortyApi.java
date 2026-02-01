package api;

import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import utils.CustomProperties;

@Getter
public class RickAndMortyApi extends BaseApi {
    private static final String CHARACTER_URL = CustomProperties.getProps().getProperty("character");
    private static final String RICK_AND_MORTY_URL = CustomProperties.getProps().getProperty("rickAndMortyURL");

    public RickAndMortyApi() {
        super(RICK_AND_MORTY_URL);
    }

    protected ValidatableResponse getByFullUrl(String fullUrl) {
        return get(fullUrl.replace(RICK_AND_MORTY_URL, ""));
    }

    public ValidatableResponse getCharacterByName(String name) {
        return getWithQueryParam(CHARACTER_URL, "name", name);
    }

    public ValidatableResponse getResourceByUrl(String resourceUrl) {
        return getByFullUrl(resourceUrl);
    }
}
