package ec.game.pokemon;

import ec.game.pokemon.model.PokemonListResponse;
import ec.game.pokemon.service.PokemonService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PokemonController {

    @FXML
    VBox pokemonContainer;

    PokemonService pokemonService;

    @FXML
    void initialize(){
        pokemonService = new PokemonService();
        loadInitialPokemon();
    }

    private void loadInitialPokemon() {
        Label loadingLabel = new Label("Cargando...");
        loadingLabel.setStyle("-fx-font-size: 16; -fx-text-fill: white; -fx-alignment: center;");
        pokemonContainer.getChildren().add(loadingLabel);
        System.out.println("Previo a ejecutarse");

        Task<PokemonListResponse> loadTask = new Task<PokemonListResponse>() {

            @Override
            protected PokemonListResponse call() throws Exception {
                return pokemonService.getPokemonList(5, 0).get();
            }
            
        };

        loadTask.setOnSucceeded( e -> {
            PokemonListResponse response = loadTask.getValue();
            if (response != null && response.getResults() != null) {
                System.out.println("Count  "+response.getCount());
                System.out.println(response.getResults().get(0).getName());
            }
        });

        loadTask.setOnFailed( y -> {
            System.out.println("Fallo");
        });

        new Thread(loadTask).start();
    }

    
}
