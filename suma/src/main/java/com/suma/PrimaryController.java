package com.suma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;

public class PrimaryController {

    @FXML
    TextField txtNumero1;

    @FXML
    TextField txtNumero2;

    @FXML
    void initialize(){
        // Expresión regular para números enteros (permitir negativos)
        String regexEntero = "^-?\\d{0,9}$"; // hasta 9 dígitos

        // Aplicar TextFormatter a ambos campos
        txtNumero1.setTextFormatter(new TextFormatter<>(change ->
            change.getControlNewText().matches(regexEntero) ? change : null
        ));

        txtNumero2.setTextFormatter(new TextFormatter<>(change ->
            change.getControlNewText().matches(regexEntero) ? change : null
        ));
    }

    @FXML
    public void sumar(ActionEvent event) {
        int numero1 = Integer.valueOf(txtNumero1.getText());
        int numero2 = Integer.valueOf(txtNumero2.getText());
        int resultado = numero1 + numero2;

        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(getClass().getSimpleName());
        alerta.setHeaderText(null);
        alerta.setContentText("El resultado de la suma es   "+resultado);
        alerta.show();
    }
}
