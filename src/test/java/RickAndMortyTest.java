import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.RickAndMortySteps;

import java.util.Map;

public class RickAndMortyTest extends ApiHooks {
    private static final RickAndMortySteps rickAndMortySteps = new RickAndMortySteps();

    @Test
    @DisplayName("Информация по персонажу Морти Смит и последний эпизод, где он появлялся")
    public void findMortyAndGetMortyLastEpisodeTest() {
        String lastEpisodeUrl = rickAndMortySteps.getMortyLastEpisode();
        Assertions.assertTrue(lastEpisodeUrl.contains("/episode/"), "URL эпизода должен содержать /episode/");
    }

    @Test
    @DisplayName("Получить из списка последнего эпизода последнего персонажа")
    public void lastCharacterFromEpisodeTest() {
        String lastEpisodeUrl = rickAndMortySteps.getMortyLastEpisode();
        Assertions.assertTrue(lastEpisodeUrl.contains("/episode/"), "URL эпизода должен содержать /episode/");

        String lastCharacterUrl = rickAndMortySteps.getLastCharacterFromEpisode(lastEpisodeUrl);
        Assertions.assertTrue(lastCharacterUrl.contains("/character/"), "URL персонажа должен содержать /character/");
    }

    @Test
    @DisplayName("Получить данные по местонахождению и расе последнего персонажа")
    public void speciesAndLocationCharacterTest() {
        String lastEpisodeUrl = rickAndMortySteps.getMortyLastEpisode();
        Assertions.assertTrue(lastEpisodeUrl.contains("/episode/"), "URL эпизода должен содержать /episode/");

        String lastCharacterUrl = rickAndMortySteps.getLastCharacterFromEpisode(lastEpisodeUrl);
        Assertions.assertTrue(lastCharacterUrl.contains("/character/"), "URL персонажа должен содержать /character/");

        Map<String, String> characterData = rickAndMortySteps.getCharacterSpeciesAndLocation(lastCharacterUrl);
        Assertions.assertNotNull(characterData.get("species"), "Раса персонажа не должна быть null");
    }

    @Test
    @DisplayName("Проверить, этот персонаж той же расы и находится там же где и Морти")
    public void compareWithMortyTest() {
        String lastEpisodeUrl = rickAndMortySteps.getMortyLastEpisode();
        Assertions.assertTrue(lastEpisodeUrl.contains("/episode/"), "URL эпизода должен содержать /episode/");

        String lastCharacterUrl = rickAndMortySteps.getLastCharacterFromEpisode(lastEpisodeUrl);
        Assertions.assertTrue(lastCharacterUrl.contains("/character/"), "URL персонажа должен содержать /character/");

        Map<String, String> characterData = rickAndMortySteps.getCharacterSpeciesAndLocation(lastCharacterUrl);
        Assertions.assertNotNull(characterData.get("species"), "Раса персонажа не должна быть null");

        Map<String, Object> comparison = rickAndMortySteps.compareCharacterWithMorty(lastCharacterUrl);
        Assertions.assertNotEquals(comparison.get("characterName"), comparison.get("mortyName"),
                "Нужно сравнивать Морти с другим персонажем");
    }
}
