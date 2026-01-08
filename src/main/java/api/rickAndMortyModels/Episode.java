package api.rickAndMortyModels;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Episode {
    public int id;
    public String name;
    public String air_date;
    public String episode;
    public List<String> characters;
    public String url;
    public String created;
}
