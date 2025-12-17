package api.rickAndMorty;

import api.rickAndMorty.models.CharacterResponse;
import api.rickAndMorty.models.EpisodeResponse;
import api.rickAndMorty.models.LocationResponse;
import utils.CustomProperties;

import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends BaseRickAndMortyApi {
    private static final String CHARACTER_URN = "/character";
    private static final String EPISODE_URN = "/episode";
    private static final String LOCATION_URN = "/location";
    private static final String BASE_URL = CustomProperties.getProps().getProperty("base.url");

    public CharacterResponse getCharacterByName(String name) {
        return given()
                .when()
                .queryParam("name", name)
                .get(CHARACTER_URN)
                .then()
                .extract()
                .body()
                .as(CharacterResponse.class);
    }

    public CharacterResponse.CharacterResult getCharacterByUrl(String characterUrl) {
        String endpoint = characterUrl.replace(BASE_URL, "");
        return given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .body()
                .as(CharacterResponse.CharacterResult.class);
    }

    public EpisodeResponse.EpisodeResult getEpisodeByUrl(String episodeUrl) {
        String endpoint = episodeUrl.replace(BASE_URL, "");
        return given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .body()
                .as(EpisodeResponse.EpisodeResult.class);
    }
}
