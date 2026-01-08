package steps;

import api.RickAndMortyApi;
import api.rickAndMortyModels.Character;
import api.rickAndMortyModels.Episode;
import io.restassured.response.ValidatableResponse;
import lombok.Data;
import utils.CustomProperties;

import java.util.List;
import java.util.Map;

@Data
public class RickAndMortySteps {
    private static final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private static final String MORTY_NAME = CustomProperties.getProps().getProperty("mortyName");

    public String getMortyLastEpisode() {
        ValidatableResponse response = rickAndMortyApi.getCharacterByName(MORTY_NAME);
        Character character = response.extract().jsonPath().getObject("results[0]", Character.class);
        List<String> episodes = character.getEpisode();
        return episodes.get(episodes.size() - 1);
    }

    public String getLastCharacterFromEpisode(String episodeUrl) {
        ValidatableResponse response = rickAndMortyApi.getResourceByUrl(episodeUrl);
        Episode episode = response.extract().as(Episode.class);
        List<String> characters = episode.getCharacters();
        return characters.get(characters.size() - 1);
    }

    public Character getCharacterInfo(String characterUrl) {
        ValidatableResponse response = rickAndMortyApi.getResourceByUrl(characterUrl);
        return response.extract().as(Character.class);
    }

    public Map<String, String> getCharacterSpeciesAndLocation(String characterUrl) {
        Character character = getCharacterInfo(characterUrl);
        return Map.of(
                "name", character.getName(),
                "species", character.getSpecies(),
                "location", character.getLocation().getName()
        );
    }

    public Character getMortyInfo() {
        ValidatableResponse response = rickAndMortyApi.getCharacterByName(MORTY_NAME);
        return response.extract().jsonPath().getObject("results[0]", Character.class);
    }

    public Map<String, Object> compareCharacterWithMorty(String characterUrl) {
        Character morty = getMortyInfo();
        Character character = getCharacterInfo(characterUrl);

        boolean sameSpecies = morty.getSpecies().equals(character.getSpecies());
        boolean sameLocation = morty.getLocation().getName().equals(character.getLocation().getName());

        return Map.of(
                "sameSpecies", sameSpecies,
                "sameLocation", sameLocation,
                "mortyName", morty.getName(),
                "characterName", character.getName(),
                "mortySpecies", morty.getSpecies(),
                "characterSpecies", character.getSpecies(),
                "mortyLocation", morty.getLocation().getName(),
                "characterLocation", character.getLocation().getName()
        );
    }
}
