package ec.capitales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.capitales.dto.ProvinciaRequest;
import ec.capitales.dto.ProvinciaResponse;
import ec.capitales.service.ProvinciaService;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    ProvinciaService provinciaService;

    @GetMapping("/traerTodos")
    public List<ProvinciaResponse> provinciaEcuador(){
        return provinciaService.traerProvincia();
    }

    @PostMapping("/extraerProvincia")
    public List<ProvinciaResponse> ingresarProvincia(@RequestBody ProvinciaRequest nuevaProvincia) {
        return provinciaService.ingresarProvincia(nuevaProvincia);
    }


}
