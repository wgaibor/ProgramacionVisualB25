package com.desayunos.lemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desayunos.lemas.entity.AdmiUsuario;

@Repository
public interface AdmiUsuarioRepository extends JpaRepository<AdmiUsuario, Integer>{

}
