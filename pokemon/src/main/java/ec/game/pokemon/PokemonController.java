package ec.game.pokemon;

import java.io.IOException;
import javafx.fxml.FXML;

public class PokemonController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
