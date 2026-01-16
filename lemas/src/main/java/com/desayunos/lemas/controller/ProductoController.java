package com.desayunos.lemas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desayunos.lemas.dto.ProductoDTO;
import com.desayunos.lemas.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO productoDTO){
        ProductoDTO objetoCreado = productoService.crear(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(objetoCreado);
    }
}
