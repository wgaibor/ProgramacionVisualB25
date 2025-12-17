package com.teclemas.usuario.controller;

import com.teclemas.usuario.dto.UsuarioDTO;
import com.teclemas.usuario.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * GET - Obtiene todos los usuarios
     * Endpoint: GET /api/usuarios
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodosLosUsuarios();
        if (usuarios.isEmpty()) {
            Map<String, String> mensaje = new HashMap<>();
            mensaje.put("mensaje", "No hay usuarios registrados en la base de datos");
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.ok(usuarios);
    }

    /**
     * GET - Obtiene todos los usuarios activos
     * Endpoint: GET /api/usuarios/activos
     */
    @GetMapping("/activos")
    public ResponseEntity<?> obtenerUsuariosActivos() {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosActivos();
        if (usuarios.isEmpty()) {
            Map<String, String> mensaje = new HashMap<>();
            mensaje.put("mensaje", "No hay usuarios activos en la base de datos");
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.ok(usuarios);
    }

    /**
     * GET - Obtiene un usuario por su ID
     * Endpoint: GET /api/usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * GET - Obtiene un usuario por su identificación
     * Endpoint: GET /api/usuarios/identificacion/{identificacion}
     */
    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorIdentificacion(@PathVariable String identificacion) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorIdentificacion(identificacion);
        return ResponseEntity.ok(usuario);
    }

    /**
     * GET - Obtiene un usuario por su correo electrónico
     * Endpoint: GET /api/usuarios/mail/{mail}
     */
    @GetMapping("/mail/{mail}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorMail(@PathVariable String mail) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorMail(mail);
        return ResponseEntity.ok(usuario);
    }

    /**
     * GET - Busca usuarios por nombre o apellido
     * Endpoint: GET /api/usuarios/buscar?q={busqueda}
     */
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarUsuarios(
            @RequestParam(name = "q", required = false) String busqueda) {
        List<UsuarioDTO> usuarios;
        if (busqueda == null || busqueda.trim().isEmpty()) {
            usuarios = usuarioService.obtenerTodosLosUsuarios();
        } else {
            usuarios = usuarioService.buscarUsuariosPorNombreOApellido(busqueda);
        }
        if (usuarios.isEmpty()) {
            Map<String, String> mensaje = new HashMap<>();
            mensaje.put("mensaje", "No se encontraron usuarios que coincidan con la búsqueda");
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.ok(usuarios);
    }

    /**
     * POST - Crea un nuevo usuario
     * Endpoint: POST /api/usuarios
     */
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, 
                                          HttpServletRequest request) {
        try {
            UsuarioDTO usuarioCreado = usuarioService.crearUsuario(usuarioDTO, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    /**
     * PUT - Actualiza un usuario existente
     * Endpoint: PUT /api/usuarios/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id,
                                                @Valid @RequestBody UsuarioDTO usuarioDTO,
                                                HttpServletRequest request) {
        try {
            UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO, request);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    /**
     * DELETE - Elimina un usuario (eliminación lógica)
     * Endpoint: DELETE /api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable Long id,
                                                               HttpServletRequest request) {
        try {
            usuarioService.eliminarUsuario(id, request);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Usuario eliminado exitosamente");
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}

