package com.desayunos.lemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 

import com.desayunos.lemas.entity.AdmiInsumo;

@Repository
public interface AdmiInsumoRepository extends JpaRepository<AdmiInsumo, Integer>{

    List<AdmiInsumo> findByCategoria(String categoria);

    @Query("SELECT i FROM AdmiInsumo i WHERE UPPER(i.nombre) LIKE CONCAT('%', UPPER(:nombre), '%')")
    List<AdmiInsumo> findByNombre(@Param("nombre") String nombre);

}
