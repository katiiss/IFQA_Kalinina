package steps;

import api.rickAndMorty.RickAndMortyApi;
import api.rickAndMorty.models.CharacterResponse;
import api.rickAndMorty.models.EpisodeResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RickAndMortySteps {
    private static final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private static final String MORTY_NAME = "Morty Smith";

    public String getMortyLastEpisode() {
        CharacterResponse response = rickAndMortyApi.getCharacterByName(MORTY_NAME);
        CharacterResponse.CharacterResult morty = response.getResults().get(0);
        List<String> episodes = morty.getEpisode();

        return episodes.get(episodes.size() - 1);
    }

    public String getLastCharacterFromEpisode(String episodeUrl) {
        EpisodeResponse.EpisodeResult episode = rickAndMortyApi.getEpisodeByUrl(episodeUrl);
        List<String> characters = episode.getCharacters();

        return characters.get(characters.size() - 1);
    }

    public CharacterResponse.CharacterResult getCharacterInfo(String characterUrl) {
        return rickAndMortyApi.getCharacterByUrl(characterUrl);
    }

    public Map<String, String> getCharacterSpeciesAndLocation(String characterUrl) {
        CharacterResponse.CharacterResult character = getCharacterInfo(characterUrl);

        return Map.of(
                "name", character.getName(),
                "species", character.getSpecies(),
                "location", character.getLocation().getName()
        );
    }

    public CharacterResponse.CharacterResult getMortyInfo() {
        CharacterResponse response = rickAndMortyApi.getCharacterByName(MORTY_NAME);
        return response.getResults().get(0);
    }

    public Map<String, Object> compareCharacterWithMorty(String characterUrl) {
        CharacterResponse.CharacterResult morty = getMortyInfo();
        CharacterResponse.CharacterResult character = getCharacterInfo(characterUrl);

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
