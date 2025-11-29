package ec.game.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ec.game.pokemon.model.PokemonAbilities;
import ec.game.pokemon.model.PokemonListItem;
import ec.game.pokemon.model.PokemonListResponse;
import ec.game.pokemon.model.PokemonResponse;
import ec.game.pokemon.model.PokemonStat;
import ec.game.pokemon.model.PokemonTypes;
import ec.game.pokemon.service.PokemonService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PokemonController {

    @FXML
    VBox pokemonContainer;

    @FXML
    TextField searchField;

    PokemonService pokemonService;

    List<PokemonListItem> lstPokemon = null;

    private final int POKEMON_LIMIT = 3;

    @FXML
    void initialize(){
        pokemonService = new PokemonService();
        lstPokemon = new ArrayList<>();
        loadInitialPokemon(POKEMON_LIMIT, lstPokemon.size());
    }

    private void loadInitialPokemon(int limite, int ultimoRegistro) {
        showLoadingIndicator("Cargando...");
        Task<PokemonListResponse> loadTask = new Task<PokemonListResponse>() {

            @Override
            protected PokemonListResponse call() throws Exception {
                return pokemonService.getPokemonList(limite, ultimoRegistro).get();
            }
            
        };

        loadTask.setOnSucceeded( e -> {
            hideLoadingIndicator();
            PokemonListResponse response = loadTask.getValue();
            if (response != null && response.getResults() != null) {
                lstPokemon.addAll(response.getResults());
                for (PokemonListItem objPokemon : response.getResults()) {
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
                System.out.println(response.getName()+"   "+response.getId());
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
        pokemonContainer.getChildren().add(createPokemonCard(response));
    }

    private void hideLoadingIndicator() {
        pokemonContainer.getChildren().removeIf(cargando ->  
            cargando instanceof Label && "Cargando...".equals(((Label) cargando).getText())
        );
    }

    private VBox createPokemonCard(PokemonResponse objPokemon) {
        VBox card = createCardContainer();
        ImageView imageView = createPokemonImage(objPokemon);
        Label namePokemon = createNamePokemon(objPokemon);
        Label idLabel = createIdLabel(objPokemon);
        HBox typesBox = createTypesBox(objPokemon);
        VBox statVBox = createStatsBox(objPokemon);
        Button detailsButton = createDetailsButton(objPokemon);
        card.getChildren().add(imageView);
        card.getChildren().add(namePokemon);
        card.getChildren().add(idLabel);
        card.getChildren().add(typesBox);
        card.getChildren().add(statVBox);
        card.getChildren().add(detailsButton);
        return card;
    }

    private Button createDetailsButton(PokemonResponse objPokemon) {
        Button buttonDetails = new Button("Ver Detalles");
        buttonDetails.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 8 16;");
        buttonDetails.setOnAction(r -> showAlertDetailsPokemon(objPokemon));
        return buttonDetails;
    }

    private void showAlertDetailsPokemon(PokemonResponse pokemon){
        String titlePokemon = "Detalles de "+ pokemon.getName();

        StringBuilder details = new StringBuilder();
        details.append("ID: #").append(String.format("%03d", pokemon.getId())).append("\n");
        details.append("Altura: ").append(pokemon.getHeight()).append("\n");
        details.append("Peso: ").append(pokemon.getWeight()).append("\n");
        details.append("EXPERIENCIA BASE: ").append(pokemon.getBase_experience()).append("\n\n");

        details.append("Tipos \n");
        for (PokemonTypes tiposPokemon : pokemon.getTypes()) {
            String nombreTipoPokemon = tiposPokemon.getType().getName().toUpperCase();
            details.append("- ").append(nombreTipoPokemon).append("\n");
        }

        details.append("\n Estadísticas: \n");
        for (PokemonStat estadisticaPokemon : pokemon.getStats()) {
            String poder = estadisticaPokemon.getStat().getName().toUpperCase();
            String nivel = estadisticaPokemon.getBase_stat()+"";
            details.append("- ").append(poder)
                                .append(" : ").append(nivel).append("\n");
        }

        details.append("\n Habilidades: \n");
        for(PokemonAbilities abilityPokemon : pokemon.getAbilities()) {
            String abiliy = abilityPokemon.getAbility().getName();
            details.append("- ").append(abiliy).append("\n");
        }
        showAlert(AlertType.INFORMATION, titlePokemon , details.toString());
    }

    private VBox createStatsBox(PokemonResponse objPokemon) {
        VBox statsBox = new VBox(3);
        statsBox.setAlignment(Pos.CENTER);
        if(objPokemon.getStats() != null) {
            for (PokemonStat estadisticaPokemon : objPokemon.getStats()) {
                String poder = estadisticaPokemon.getStat().getName().toUpperCase();
                String nivel = estadisticaPokemon.getBase_stat()+"";
                Label statLabel = new Label(poder+" : "+nivel);
                statLabel.setFont(Font.font("Arial", 10));
                statLabel.setStyle("-fx-text-fill: #34495e;");
                statsBox.getChildren().add(statLabel);
            }
        }
        return statsBox;
    }

    private HBox createTypesBox(PokemonResponse objPokemon) {
        HBox typesBox = new HBox(5);
        typesBox.setAlignment(Pos.CENTER);
        if(objPokemon.getTypes() != null){
            for (PokemonTypes tiposPokemon : objPokemon.getTypes()) {
                String nombreTipoPokemon = tiposPokemon.getType().getName().toUpperCase();
                Label typelaLabel = new Label(nombreTipoPokemon);
                typelaLabel.setStyle("-fx-background-color: "+getTypeColor(nombreTipoPokemon)+"; -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 5 10; -fx-font-size: 10;");
                typesBox.getChildren().add(typelaLabel);
            }
        }
        return typesBox;
    }

    private Label createNamePokemon(PokemonResponse pokemon) {
        Label nameLabel = new Label(pokemon.getFormattedName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameLabel.setStyle("-fx-text-fill: #2c3e50;");
        return nameLabel;
    }

    private Label createIdLabel(PokemonResponse pokemon) {
        String idPokemon = "#"+ String.format("%03d", pokemon.getId());
        Label nameLabel = new Label(idPokemon);
        nameLabel.setFont(Font.font("Arial", 12));
        nameLabel.setStyle("-fx-text-fill: #ec390cff;");
        return nameLabel;
    }

    private ImageView createPokemonImage(PokemonResponse pokemon) {
        ImageView imagenPokemon = new ImageView();
        imagenPokemon.setFitHeight(120);
        imagenPokemon.setFitWidth(120);
        imagenPokemon.setPreserveRatio(true);
        if(pokemon.getSprites() != null && pokemon.getSprites().getFront_default() != null) {
            try{
                Image imagenDescargada = new Image(pokemon.getSprites().getFront_default(), true);
                imagenPokemon.setImage(imagenDescargada);
            } catch (Exception e) {
                imagenPokemon.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 60;");
            }
        }
        return imagenPokemon;
    }

    private VBox createCardContainer() {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2); -fx-padding: 15; -fx-max-width: 300;");
        card.setAlignment(Pos.CENTER);
        return card;
    }

    
    //Paleta de colores
    private static final Map<String, String> TYPE_COLORS = new HashMap<>();

    static{
        TYPE_COLORS.put("fire", "#ff6b6b");
        TYPE_COLORS.put("water", "#4ecdc4");
        TYPE_COLORS.put("grass", "#45b7d1");
        TYPE_COLORS.put("electric", "#f9ca24");
        TYPE_COLORS.put("psychic", "#f0932b");
        TYPE_COLORS.put("ice", "#74b9ff");
        TYPE_COLORS.put("dragon", "#6c5ce7");
        TYPE_COLORS.put("fighting", "#fd79a8");
        TYPE_COLORS.put("poison", "#a29bfe");
        TYPE_COLORS.put("ground", "#fd79a8");
        TYPE_COLORS.put("flying", "#81ecec");
        TYPE_COLORS.put("bug", "#00b894");
        TYPE_COLORS.put("rock", "#636e72");
        TYPE_COLORS.put("ghost", "#a29bfe");
        TYPE_COLORS.put("dark", "#2d3436");
        TYPE_COLORS.put("steel", "#636e72");
        TYPE_COLORS.put("fairy", "#fd79a8");
        TYPE_COLORS.put("normal", "#6ea319ff");
    }

    private String getTypeColor(String typeName){
        return TYPE_COLORS.getOrDefault(typeName.toLowerCase(), "#95a5a6");
    }

    @FXML
    private void searchPokemon(){
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            showAlert(AlertType.ERROR, "ERROR", "Por favor ingresa el nombre de un Pokémon");
            return;
        }
        clearPokemonContainer();
        showLoadingIndicator("Cargando...");

        callPokemonByName(searchText);
    }

    private void callPokemonByName(String searchText){
        Task<PokemonResponse> searchTask = new Task<PokemonResponse>() {

            @Override
            protected PokemonResponse call() throws Exception {
                return pokemonService.getPokemonByName(searchText).get();
            }
            
        };

        searchTask.setOnSucceeded(g -> {
            hideLoadingIndicator();
            PokemonResponse pokemon = searchTask.getValue();
            if (pokemon != null) {
                showPokemonDetails(pokemon);
            }
        });

        searchTask.setOnFailed(y -> {
            clearPokemonContainer();
            showLoadingIndicator("La busqueda no fue exitosa");
            Throwable ex = searchTask.getException();
            System.err.println("Fallo al obtener detalle del pokemon: " + searchText + ": " + (ex != null ? ex.getMessage() : "desconocido"));
            if (ex != null) ex.printStackTrace();
        });

        new Thread(searchTask).start();
    }

    private void clearPokemonContainer() {
        pokemonContainer.getChildren().clear();
    }

    private void showAlert(AlertType typeAlert, String title, String message) {
        Alert alert = new Alert(typeAlert);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showLoadingIndicator(String mensaje){
        Label loadingLabel = new Label(mensaje);
        loadingLabel.setStyle("-fx-font-size: 16; -fx-text-fill: white; -fx-alignment: center;");
        pokemonContainer.getChildren().add(loadingLabel);
    }

    @FXML
    private void loadMorePokemon(){
        System.out.println("Tamaño del listado  "+lstPokemon.size());
        loadInitialPokemon(POKEMON_LIMIT, lstPokemon.size());
    }

    @FXML
    private void randomPokemon(){
        clearPokemonContainer();
        showLoadingIndicator("Cargando...");
        Random aleatorio = new Random();
        int numeroAleatorio = aleatorio.nextInt(1024) + 1;
        callPokemonByName(numeroAleatorio+"");
    }
}
