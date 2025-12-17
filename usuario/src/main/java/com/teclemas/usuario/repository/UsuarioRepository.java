package com.teclemas.usuario.repository;

import com.teclemas.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su identificación
     */
    Optional<Usuario> findByIdentificacion(String identificacion);

    /**
     * Busca un usuario por su correo electrónico
     */
    Optional<Usuario> findByMail(String mail);

    /**
     * Verifica si existe un usuario con la identificación dada
     */
    boolean existsByIdentificacion(String identificacion);

    /**
     * Verifica si existe un usuario con el correo electrónico dado
     */
    boolean existsByMail(String mail);

    /**
     * Busca usuarios activos
     */
    List<Usuario> findByActivoTrue();

    /**
     * Busca usuarios por nombre o apellido (búsqueda parcial)
     */
    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:busqueda% OR u.apellido LIKE %:busqueda%")
    List<Usuario> buscarPorNombreOApellido(@Param("busqueda") String busqueda);

    /**
     * Busca usuarios activos por ciudad
     */
    List<Usuario> findByCiudadAndActivoTrue(String ciudad);
}

