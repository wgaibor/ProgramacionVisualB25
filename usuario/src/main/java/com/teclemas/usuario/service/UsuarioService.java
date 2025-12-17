package com.teclemas.usuario.service;

import com.teclemas.usuario.dto.UsuarioDTO;
import com.teclemas.usuario.model.Usuario;
import com.teclemas.usuario.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Crea un nuevo usuario
     */
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO, HttpServletRequest request) {
        // Validar que no exista un usuario con la misma identificación
        if (usuarioRepository.existsByIdentificacion(usuarioDTO.getIdentificacion())) {
            throw new RuntimeException("Ya existe un usuario con la identificación: " + usuarioDTO.getIdentificacion());
        }

        // Validar que no exista un usuario con el mismo correo
        if (usuarioRepository.existsByMail(usuarioDTO.getMail())) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + usuarioDTO.getMail());
        }

        // Convertir DTO a entidad
        Usuario usuario = convertirDTOAEntidad(usuarioDTO);
        
        // Establecer campos de auditoría
        usuario.setUsrCreacion(obtenerUsuarioActual(request));
        usuario.setIpCreacion(obtenerIpCliente(request));

        // Guardar usuario
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir entidad a DTO y retornar
        return convertirEntidadADTO(usuarioGuardado);
    }

    /**
     * Obtiene un usuario por su ID
     */
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return convertirEntidadADTO(usuario);
    }

    /**
     * Obtiene todos los usuarios
     */
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los usuarios activos
     */
    public List<UsuarioDTO> obtenerUsuariosActivos() {
        return usuarioRepository.findByActivoTrue().stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca usuarios por identificación
     */
    public UsuarioDTO obtenerUsuarioPorIdentificacion(String identificacion) {
        Usuario usuario = usuarioRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con identificación: " + identificacion));
        return convertirEntidadADTO(usuario);
    }

    /**
     * Busca usuarios por correo electrónico
     */
    public UsuarioDTO obtenerUsuarioPorMail(String mail) {
        Usuario usuario = usuarioRepository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + mail));
        return convertirEntidadADTO(usuario);
    }

    /**
     * Busca usuarios por nombre o apellido
     */
    public List<UsuarioDTO> buscarUsuariosPorNombreOApellido(String busqueda) {
        return usuarioRepository.buscarPorNombreOApellido(busqueda).stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un usuario existente
     */
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO, HttpServletRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Validar que si cambia la identificación, no exista otra con la misma
        if (!usuario.getIdentificacion().equals(usuarioDTO.getIdentificacion()) &&
            usuarioRepository.existsByIdentificacion(usuarioDTO.getIdentificacion())) {
            throw new RuntimeException("Ya existe un usuario con la identificación: " + usuarioDTO.getIdentificacion());
        }

        // Validar que si cambia el correo, no exista otro con el mismo
        if (!usuario.getMail().equals(usuarioDTO.getMail()) &&
            usuarioRepository.existsByMail(usuarioDTO.getMail())) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + usuarioDTO.getMail());
        }

        // Actualizar campos
        usuario.setIdentificacion(usuarioDTO.getIdentificacion());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setFeNacimiento(usuarioDTO.getFeNacimiento());
        usuario.setCelular(usuarioDTO.getCelular());
        usuario.setMail(usuarioDTO.getMail());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setCiudad(usuarioDTO.getCiudad());
        usuario.setPais(usuarioDTO.getPais());
        if (usuarioDTO.getActivo() != null) {
            usuario.setActivo(usuarioDTO.getActivo());
        }

        // Establecer campos de auditoría de modificación
        usuario.setUsrModificacion(obtenerUsuarioActual(request));
        usuario.setIpModificacion(obtenerIpCliente(request));

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return convertirEntidadADTO(usuarioActualizado);
    }

    /**
     * Elimina un usuario (eliminación lógica)
     */
    public void eliminarUsuario(Long id, HttpServletRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        
        usuario.setActivo(false);
        usuario.setUsrModificacion(obtenerUsuarioActual(request));
        usuario.setIpModificacion(obtenerIpCliente(request));
        
        usuarioRepository.save(usuario);
    }

    /**
     * Convierte un DTO a una entidad
     */
    private Usuario convertirDTOAEntidad(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setFeNacimiento(dto.getFeNacimiento());
        usuario.setCelular(dto.getCelular());
        usuario.setMail(dto.getMail());
        usuario.setDireccion(dto.getDireccion());
        usuario.setCiudad(dto.getCiudad());
        usuario.setPais(dto.getPais());
        usuario.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        return usuario;
    }

    /**
     * Convierte una entidad a un DTO
     */
    private UsuarioDTO convertirEntidadADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setIdentificacion(usuario.getIdentificacion());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setFeNacimiento(usuario.getFeNacimiento());
        dto.setCelular(usuario.getCelular());
        dto.setMail(usuario.getMail());
        dto.setDireccion(usuario.getDireccion());
        dto.setCiudad(usuario.getCiudad());
        dto.setPais(usuario.getPais());
        dto.setActivo(usuario.getActivo());
        return dto;
    }

    /**
     * Obtiene el usuario actual desde el request (puede mejorarse con Spring Security)
     */
    private String obtenerUsuarioActual(HttpServletRequest request) {
        String usuario = request.getHeader("X-Usuario");
        return usuario != null ? usuario : "ANONYMOUS";
    }

    /**
     * Obtiene la IP del cliente desde el request
     */
    private String obtenerIpCliente(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

