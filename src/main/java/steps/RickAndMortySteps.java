package steps;

import api.RickAndMortyApi;
import api.rickAndMortyModels.Character;
import api.rickAndMortyModels.Episode;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import utils.CustomProperties;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RickAndMortySteps {
    private static final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private static final String MORTY_NAME = CustomProperties.getProps().getProperty("mortyName");
    private String lastEpisodeUrl;
    private String lastCharacterUrl;
    private Character characterInfo;
    private Character mortyInfo;

    @Step("Получение последнего эпизода Морти Смита")
    @Когда("получаем последний эпизод Морти Смита")
    public void getMortyLastEpisode() {
        ValidatableResponse response = rickAndMortyApi.getCharacterByName(MORTY_NAME);
        Character character = response.extract().jsonPath().getObject("results[0]", Character.class);
        List<String> episodes = character.getEpisode();
        lastEpisodeUrl = episodes.get(episodes.size() - 1);
        assertTrue(lastEpisodeUrl.contains("/episode/"), "URL эпизода должен содержать /episode/");
    }

    @Step("Получение последнего персонажа из эпизода")
    @Тогда("получаем последнего персонажа из последнего эпизода")
    public void getLastCharacterFromEpisode() {
        ValidatableResponse response = rickAndMortyApi.getResourceByUrl(lastEpisodeUrl);
        Episode episode = response.extract().as(Episode.class);
        List<String> characters = episode.getCharacters();
        lastCharacterUrl = characters.get(characters.size() - 1);
        assertTrue(lastCharacterUrl.contains("/character/"), "URL персонажа должен содержать /character/");
    }

    @Step("Получение полной информации о персонаже")
    @Когда("получаем информацию о персонаже")
    public void getCharacterInfo() {
        ValidatableResponse response = rickAndMortyApi.getResourceByUrl(lastCharacterUrl);
        characterInfo = response.extract().as(Character.class);
    }

    @Step("Получение расы и локации персонажа")
    @И("получаем данные о расе и локации персонажа")
    public void getCharacterSpeciesAndLocation() {
        ValidatableResponse response = rickAndMortyApi.getResourceByUrl(lastCharacterUrl);
        Character character = response.extract().as(Character.class);
        Map<String, String> characterData;
        characterData = Map.of(
                "name", character.getName(),
                "species", character.getSpecies(),
                "location", character.getLocation().getName()
        );
        assertNotNull(characterData.get("species"), "Раса персонажа не должна быть null");
    }

    @Step("Получение информации о Морти")
    @И("получаем информацию о Морти")
    public void getMortyInfo() {
        ValidatableResponse response = rickAndMortyApi.getCharacterByName(MORTY_NAME);
        mortyInfo = response.extract().jsonPath().getObject("results[0]", Character.class);
    }

    @Step("Сравнение персонажа с Морти")
    @Тогда("сравниваем персонажа с Морти")
    public void compareCharacterWithMorty() {
        Character morty = mortyInfo;
        Character character = characterInfo;
        boolean sameSpecies = morty.getSpecies().equals(character.getSpecies());
        boolean sameLocation = morty.getLocation().getName().equals(character.getLocation().getName());
        Map<String, Object> comparisonResult;
        comparisonResult = Map.of(
                "sameSpecies", sameSpecies,
                "sameLocation", sameLocation,
                "mortyName", morty.getName(),
                "characterName", character.getName(),
                "mortySpecies", morty.getSpecies(),
                "characterSpecies", character.getSpecies(),
                "mortyLocation", morty.getLocation().getName(),
                "characterLocation", character.getLocation().getName()
        );
        assertNotEquals(comparisonResult.get("characterName"), comparisonResult.get("mortyName"),
                "Нужно сравнивать Морти с другим персонажем");
        assertEquals(comparisonResult.get("mortySpecies"), comparisonResult.get("characterSpecies"),
                "Раса персонажа должна совпадать с расой Морти");
        assertNotEquals(comparisonResult.get("mortyLocation"), comparisonResult.get("characterLocation"),
                "Локация персонажа не должна совпадать с локацией Морти");
    }
}
