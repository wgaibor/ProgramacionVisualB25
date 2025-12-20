package com.lemas.cafeteria.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemas.cafeteria.Dto.MensajeResponse;

@RestController
public class CafeteriaController {
    @GetMapping("/hola")
    public MensajeResponse mensajeGenerico(){
        MensajeResponse mensaje = new MensajeResponse();
        mensaje.setMensaje("Hola mundo!!!");
        return mensaje;
    }

}
