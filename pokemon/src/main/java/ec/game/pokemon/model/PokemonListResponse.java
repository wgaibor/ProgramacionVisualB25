package ec.game.pokemon.model;

import java.util.List;

public class PokemonListResponse {
    private int count;
    private String next;
    private int previous;
    private List<PokemonListItem> results;

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getNext() {
        return next;
    }
    public void setNext(String next) {
        this.next = next;
    }
    public int getPrevious() {
        return previous;
    }
    public void setPrevious(int previous) {
        this.previous = previous;
    }
    public List<PokemonListItem> getResults() {
        return results;
    }
    public void setResults(List<PokemonListItem> results) {
        this.results = results;
    }

    
}
