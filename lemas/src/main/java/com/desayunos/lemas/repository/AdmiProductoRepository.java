package com.desayunos.lemas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desayunos.lemas.entity.AdmiProducto;
@Repository
public interface AdmiProductoRepository extends JpaRepository<AdmiProducto, Integer> {

    List<AdmiProducto> findByCategoria(String categoria);

}
