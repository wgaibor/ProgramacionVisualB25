package com.desayunos.lemas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class InsumoDTO {

    private Integer idInsumo;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private BigDecimal cantidadDisponible;
    private BigDecimal precioCompra;
    private String categoria;
    private String proveedor;
    private LocalDate fechaVencimiento;
    private String estado = "A";

    public Integer getIdInsumo() {
        return idInsumo;
    }
    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    public BigDecimal getCantidadDisponible() {
        return cantidadDisponible;
    }
    public void setCantidadDisponible(BigDecimal cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}
