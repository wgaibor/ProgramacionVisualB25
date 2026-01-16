package com.desayunos.lemas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductoDTO {

    private Integer idProducto;

    @NotBlank(message = "El item es obligatorio")
    private String item;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.01", inclusive = false, message = "El precio debe ser mayor a 0.01")
    private BigDecimal precioUnitario;

    private String descripcion;
    private String categoria;
    private Integer stockDisponible;
    private String estado;

    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Integer getStockDisponible() {
        return stockDisponible;
    }
    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
