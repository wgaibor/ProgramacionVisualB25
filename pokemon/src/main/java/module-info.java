module ec.game.pokemon {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.game.pokemon to javafx.fxml;
    exports ec.game.pokemon;
}
