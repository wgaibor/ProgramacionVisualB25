package com.desayunos.lemas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desayunos.lemas.dto.UsuarioDTO;
import com.desayunos.lemas.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@Valid @RequestBody UsuarioDTO usuarioDTO,
                                            @RequestHeader(value = "usuario", defaultValue = "SYSTEM") String usuario){
        UsuarioDTO creado = usuarioService.crear(usuarioDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
