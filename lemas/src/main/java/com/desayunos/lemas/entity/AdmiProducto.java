package com.desayunos.lemas.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "admi_producto")
public class AdmiProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @NotBlank(message = "El item es obligatorio")
    @Column(name = "item", nullable = false, length = 255)
    private String item;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria", length = 100)
    private String categoria;

    @Column(name = "stock_disponible")
    private Integer stockDisponible = 0;

    @Column(name = "estado", length = 1, nullable = false)
    private String estado = "A";

    @NotBlank(message = "El usuario de creación es obligatorio")
    @Column(name = "usr_creacion", nullable = false, length = 63)
    private String usrCreacion;

    @Column(name = "fe_creacion", nullable = false, updatable = false)
    private LocalDateTime feCreacion;

    @Column(name = "usr_ult_modificacion", length = 63)
    private String usrUltModificacion;

    @Column(name = "fe_ult_modificacion")
    private LocalDateTime feUltModificacion;

    @PrePersist
    protected void onCreate() {
        feCreacion = LocalDateTime.now();
        if (estado == null) {
            estado = "A";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        feUltModificacion = LocalDateTime.now();
    }

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

    public String getUsrCreacion() {
        return usrCreacion;
    }

    public void setUsrCreacion(String usrCreacion) {
        this.usrCreacion = usrCreacion;
    }

    public LocalDateTime getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(LocalDateTime feCreacion) {
        this.feCreacion = feCreacion;
    }

    public String getUsrUltModificacion() {
        return usrUltModificacion;
    }

    public void setUsrUltModificacion(String usrUltModificacion) {
        this.usrUltModificacion = usrUltModificacion;
    }

    public LocalDateTime getFeUltModificacion() {
        return feUltModificacion;
    }

    public void setFeUltModificacion(LocalDateTime feUltModificacion) {
        this.feUltModificacion = feUltModificacion;
    }

    
}
