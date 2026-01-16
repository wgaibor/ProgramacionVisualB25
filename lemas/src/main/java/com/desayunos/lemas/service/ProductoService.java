package com.desayunos.lemas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desayunos.lemas.dto.ProductoDTO;
import com.desayunos.lemas.entity.AdmiProducto;
import com.desayunos.lemas.repository.AdmiProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private AdmiProductoRepository productoRepository;

    public ProductoDTO crear (ProductoDTO dto){
        AdmiProducto producto = convertirAEntidad(dto);
        producto.setUsrCreacion("wgaibor");
        AdmiProducto objetoGuardado = productoRepository.save(producto);
        return convertirADTO(objetoGuardado);
    }


    private AdmiProducto convertirAEntidad(ProductoDTO dto) {
        AdmiProducto producto = new AdmiProducto();
        producto.setItem(dto.getItem());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(dto.getCategoria());
        producto.setStockDisponible(dto.getStockDisponible() != null ? dto.getStockDisponible() : 0);
        producto.setEstado(dto.getEstado() != null ? dto.getEstado() : "A");
        return producto;
    }

    private ProductoDTO convertirADTO(AdmiProducto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setItem(producto.getItem());
        dto.setPrecioUnitario(producto.getPrecioUnitario());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCategoria(producto.getCategoria());
        dto.setStockDisponible(producto.getStockDisponible());
        dto.setEstado(producto.getEstado());
        return dto;
    }
}
