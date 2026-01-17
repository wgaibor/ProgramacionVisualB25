package com.desayunos.lemas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
        List<ProductoDTO> lstProductos = productoService.traerTodosLosProductos();
        return ResponseEntity.ok(lstProductos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<ProductoDTO> productos = productoService.obtenerPorId(id);
        return productos.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("buscarCategoria/{categoria}")
    public ResponseEntity<List<ProductoDTO>> obtenerPorCategoria(@PathVariable String categoria) {
        List<ProductoDTO> productos = productoService.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO productoDTO){
        ProductoDTO objetoCreado = productoService.crear(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(objetoCreado);
    }
}
