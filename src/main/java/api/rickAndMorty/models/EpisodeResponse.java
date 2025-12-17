package api.rickAndMorty.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class EpisodeResponse {
    @Data
    public static class Info{
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
    @Data
    public static class EpisodeResult{
        private int id;
        private String name;
        private String air_date;
        private String episode;
        private ArrayList<String> characters;
        private String url;
        private Date created;
    }
    private Info info;
    private ArrayList<EpisodeResult> results;
}
