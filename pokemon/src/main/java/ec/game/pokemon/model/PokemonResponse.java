package ec.game.pokemon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse {
    private int id;
    private String name;
    private List<PokemonTypes> types;
    private PokemonSprites sprites;
    private List<PokemonStat> stats;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<PokemonTypes> getTypes() {
        return types;
    }
    public void setTypes(List<PokemonTypes> types) {
        this.types = types;
    }
    public PokemonSprites getSprites() {
        return sprites;
    }
    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }
    public List<PokemonStat> getStats() {
        return stats;
    }
    public void setStats(List<PokemonStat> stats) {
        this.stats = stats;
    }

    
}
