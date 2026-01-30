package com.desayunos.lemas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desayunos.lemas.dto.InsumoDTO;
import com.desayunos.lemas.entity.AdmiInsumo;
import com.desayunos.lemas.repository.AdmiInsumoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InsumoService {

    @Autowired
    private AdmiInsumoRepository insumoRepository;

    public InsumoDTO crear(InsumoDTO insumoDTO, String usrCreacion) {
        AdmiInsumo admiInsumo = convertirAEntidad(insumoDTO);
        admiInsumo.setUsrCreacion(usrCreacion);
        AdmiInsumo objNewInsumo = insumoRepository.save(admiInsumo);
        return convertirADTO(objNewInsumo);
    }

    private InsumoDTO convertirADTO(AdmiInsumo insumo) {
        InsumoDTO dto = new InsumoDTO();
        dto.setIdInsumo(insumo.getIdInsumo());
        dto.setNombre(insumo.getNombre());
        dto.setDescripcion(insumo.getDescripcion());
        dto.setUnidadMedida(insumo.getUnidadMedida());
        dto.setCantidadDisponible(insumo.getCantidadDisponible());
        dto.setPrecioCompra(insumo.getPrecioCompra());
        dto.setCategoria(insumo.getCategoria());
        dto.setProveedor(insumo.getProveedor());
        dto.setFechaVencimiento(insumo.getFechaVencimiento());
        dto.setEstado(insumo.getEstado());
        return dto;
    }

    private AdmiInsumo convertirAEntidad(InsumoDTO insumoDTO) {
        AdmiInsumo admiInsumo = new AdmiInsumo();
        admiInsumo.setIdInsumo(insumoDTO.getIdInsumo());
        admiInsumo.setNombre(insumoDTO.getNombre());
        admiInsumo.setDescripcion(insumoDTO.getDescripcion());
        admiInsumo.setUnidadMedida(insumoDTO.getUnidadMedida());
        admiInsumo.setCantidadDisponible(insumoDTO.getCantidadDisponible() != null ? insumoDTO.getCantidadDisponible() : BigDecimal.ZERO);
        admiInsumo.setPrecioCompra(insumoDTO.getPrecioCompra());
        admiInsumo.setCategoria(insumoDTO.getCategoria());
        admiInsumo.setProveedor(insumoDTO.getProveedor());
        admiInsumo.setFechaVencimiento(insumoDTO.getFechaVencimiento());
        admiInsumo.setEstado(insumoDTO.getEstado() != null ? insumoDTO.getEstado() : "A");
        return admiInsumo;
    }

    public List<InsumoDTO> obtenerTodos() {
        return insumoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<InsumoDTO> obtenerPorId(Integer id) {
        return insumoRepository.findById(id)
                .map(this::convertirADTO);
    }

    public List<InsumoDTO> obtenerPorCategoria(String categoria) {
        return insumoRepository.findByCategoria(categoria).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<InsumoDTO> obtenerPorNombre(String nombre) {
        return insumoRepository.findByNombre(nombre).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

}
