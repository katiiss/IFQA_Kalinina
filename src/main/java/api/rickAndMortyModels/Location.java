package api.rickAndMortyModels;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Location {
    public int id;
    public String name;
    public String type;
    public String dimension;
    public List<String> residents;
    public String url;
    public String created;
}
