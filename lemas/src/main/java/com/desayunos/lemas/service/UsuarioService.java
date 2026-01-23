package com.desayunos.lemas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desayunos.lemas.dto.UsuarioDTO;
import com.desayunos.lemas.entity.AdmiUsuario;
import com.desayunos.lemas.repository.AdmiUsuarioRepository;

import jakarta.transaction.Transactional;

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
}
