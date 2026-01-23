package com.desayunos.lemas.entity;

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
@Table(name = "admi_usuario")
public class AdmiUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "tipo_usuario", length = 15)
    private String tipoUsuario="CLIENTE";

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

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
        if (tipoUsuario == null) {
            tipoUsuario = "CLIENTE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        feUltModificacion = LocalDateTime.now();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
