package com.desayunos.lemas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desayunos.lemas.dto.UsuarioDTO;
import com.desayunos.lemas.entity.AdmiUsuario;
import com.desayunos.lemas.repository.AdmiUsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    AdmiUsuarioRepository usuarioRepository;

    public UsuarioDTO crear(UsuarioDTO usuario, String usrCreacion) {
        AdmiUsuario objUsuario = convertirAEntidad(usuario);
        objUsuario.setUsrCreacion(usrCreacion);
        AdmiUsuario newUsuario = usuarioRepository.save(objUsuario);
        return convertirADTO(newUsuario);
    }

    private UsuarioDTO convertirADTO(AdmiUsuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccion(usuario.getDireccion());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        dto.setFechaNacimiento(usuario.getFechaNacimiento());
        dto.setEstado(usuario.getEstado());
        return dto;
    }

    private AdmiUsuario convertirAEntidad(UsuarioDTO usuario) {
        AdmiUsuario user = new AdmiUsuario();
        user.setIdUsuario(usuario.getIdUsuario());
        user.setNombre(usuario.getNombre());
        user.setEmail(usuario.getEmail());
        user.setTelefono(usuario.getTelefono());
        user.setDireccion(usuario.getDireccion());
        user.setTipoUsuario(usuario.getTipoUsuario() != null ? usuario.getTipoUsuario() : "CLIENTE");
        user.setFechaNacimiento(usuario.getFechaNacimiento());
        user.setEstado(usuario.getEstado() != null ? usuario.getEstado() : "A");
        return user;
    }

    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADTO);
    }

    public Optional<UsuarioDTO> actualizar(Integer id, UsuarioDTO usuarioDTO, String usuarioUltMod) {
        return usuarioRepository.findById(id).map( nuevoUsuario -> {
            nuevoUsuario.setNombre(usuarioDTO.getNombre());
            nuevoUsuario.setEmail(usuarioDTO.getEmail());
            nuevoUsuario.setTelefono(usuarioDTO.getTelefono());
            nuevoUsuario.setDireccion(usuarioDTO.getDireccion());
            nuevoUsuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
            nuevoUsuario.setUsrUltModificacion(usuarioUltMod);
            if (usuarioDTO.getEstado() != null) {
                nuevoUsuario.setEstado(usuarioDTO.getEstado());
            }
            if (usuarioDTO.getTipoUsuario() != null) {
                nuevoUsuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
            }
            AdmiUsuario usuarioActualizado = usuarioRepository.save(nuevoUsuario);
            return convertirADTO(usuarioActualizado);
        });
    }
}
