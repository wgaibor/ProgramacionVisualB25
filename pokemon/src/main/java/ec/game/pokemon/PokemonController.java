package ec.game.pokemon;

import ec.game.pokemon.model.PokemonListItem;
import ec.game.pokemon.model.PokemonListResponse;
import ec.game.pokemon.model.PokemonResponse;
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
        Task<PokemonListResponse> loadTask = new Task<PokemonListResponse>() {

            @Override
            protected PokemonListResponse call() throws Exception {
                return pokemonService.getPokemonList(1, 0).get();
            }
            
        };

        loadTask.setOnSucceeded( e -> {
            hideLoadingIndicator();
            PokemonListResponse response = loadTask.getValue();
            if (response != null && response.getResults() != null) {
                for (PokemonListItem objPokemon : response.getResults()) {
                    pokemonContainer.getChildren().add(createPokemonCard(objPokemon));
                    callDetailsPokemon(objPokemon.getUrl());
                }
            }
        });

        loadTask.setOnFailed( y -> {
            Throwable ex = loadTask.getException();
            System.err.println("Fallo al cargar lista: " + (ex != null ? ex.getMessage() : "desconocido"));
            if (ex != null) ex.printStackTrace();
        });

        new Thread(loadTask).start();
    }

    private void callDetailsPokemon(String url) {
        Task<PokemonResponse> detailPokemonTask = new Task<PokemonResponse>() {

            @Override
            protected PokemonResponse call() throws Exception {
                return pokemonService.getPokemonByURL(url).get();
            }
            
        };

        detailPokemonTask.setOnSucceeded( e -> {
            PokemonResponse response = detailPokemonTask.getValue();
            if (response != null) {
                showPokemonDetails(response);
            }
        });

        detailPokemonTask.setOnFailed( y -> {
            Throwable ex = detailPokemonTask.getException();
            System.err.println("Fallo al obtener detalle de " + url + ": " + (ex != null ? ex.getMessage() : "desconocido"));
            if (ex != null) ex.printStackTrace();
        });
        new Thread(detailPokemonTask).start();
    }

    private void showPokemonDetails(PokemonResponse response) {
        // Ejemplo mínimo: usa response para actualizar UI o depurar
        System.out.println("Detalles recibidos: " + response.getName());
    }

    private void hideLoadingIndicator() {
        pokemonContainer.getChildren().removeIf(cargando ->  
            cargando instanceof Label && "Cargando...".equals(((Label) cargando).getText())
        );
    }

    private VBox createPokemonCard(PokemonListItem objPokemon) {
        VBox card = createCardContainer();
        Label namePokemon = createNamePokemon(objPokemon);
        card.getChildren().add(namePokemon);
        return card;
    }

    private Label createNamePokemon(PokemonListItem pokemon) {
        Label nameLabel = new Label(pokemon.getName());
        return nameLabel;
    }

    private VBox createCardContainer() {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2); -fx-padding: 15; -fx-max-width: 300;");
        return card;
    }

    
    
}
