package api.rickAndMorty.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class LocationResponse {
    @Data
    public static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
    @Data
    public static class LocationResult {
        private int id;
        private String name;
        private String type;
        private String dimension;
        private ArrayList<String> residents;
        private String url;
        private Date created;
    }

    private Info info;
    private ArrayList<LocationResult> results;
}
