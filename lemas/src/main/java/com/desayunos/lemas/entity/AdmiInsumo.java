package com.desayunos.lemas.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "admi_insumo")
public class AdmiInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Integer idInsumo;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT", length = 255)
    private String descripcion;

    @Column(name = "unidad_medida", length = 31)
    private String unidadMedida;

    @Column(name = "cantidad_disponible", precision = 10, scale = 2)
    private BigDecimal cantidadDisponible;

    @Column(name = "precio_compra", precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "categoria", length = 127)
    private String categoria;

    @Column(name = "proveedor", length = 255)
    private String proveedor;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

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
        if (cantidadDisponible == null) {
            cantidadDisponible = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        feUltModificacion = LocalDateTime.now();
    }

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
