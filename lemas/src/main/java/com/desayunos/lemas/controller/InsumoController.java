package com.desayunos.lemas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.desayunos.lemas.dto.InsumoDTO;
import com.desayunos.lemas.service.InsumoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/insumos")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @PostMapping
    public ResponseEntity<InsumoDTO> crear(@Valid @RequestBody InsumoDTO insumoDTO,
                                        @RequestHeader(value = "usuario", defaultValue = "SYSTEM") String usuario){
        InsumoDTO objInsumo = insumoService.crear(insumoDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(objInsumo);
    } 

    @GetMapping
    public ResponseEntity<List<InsumoDTO>> obtenerTodosLosInsumos() {
        List<InsumoDTO> lstInsumos = insumoService.obtenerTodos();
        return ResponseEntity.ok(lstInsumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsumoDTO> obtenerPorId(@PathVariable Integer id){
        Optional<InsumoDTO> objInsumo = insumoService.obtenerPorId(id);
        return objInsumo.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<InsumoDTO>> obtenerPorCategoria(@PathVariable String categoria){
        List<InsumoDTO> lstInsumos = insumoService.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(lstInsumos);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<InsumoDTO>> obtenerPorNombre(@PathVariable String nombre){
        List<InsumoDTO> lstInsumos = insumoService.obtenerPorNombre(nombre);
        return ResponseEntity.ok(lstInsumos);
    }
}
