package api.rickAndMortyModels;

import lombok.Data;
import java.util.List;

@Data
public class Character {
    public int id;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public Origin origin;
    public Location location;
    public String image;
    public List<String> episode;
    public String url;
    public String created;
}
