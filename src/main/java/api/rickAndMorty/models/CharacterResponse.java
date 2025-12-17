package api.rickAndMorty.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class CharacterResponse {
    @Data
    public static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
    @Data
    public static class Location {
        private String name;
        private String url;
    }
    @Data
    public static class Origin {
        private String name;
        private String url;
    }
    @Data
    public static class CharacterResult {
        private int id;
        private String name;
        private String status;
        private String species;
        private String type;
        private String gender;
        private Origin origin;
        private Location location;
        private String image;
        private ArrayList<String> episode;
        private String url;
        private Date created;
    }

    private Info info;
    private ArrayList<CharacterResult> results;
}
