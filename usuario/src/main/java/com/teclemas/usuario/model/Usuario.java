package com.teclemas.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank(message = "La identificación es obligatoria")
    @Size(min = 5, max = 20, message = "La identificación debe tener entre 5 y 20 caracteres")
    @Column(name = "identificacion", unique = true, nullable = false, length = 20)
    private String identificacion;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Column(name = "fe_nacimiento", nullable = false)
    private LocalDate feNacimiento;

    @Pattern(regexp = "^[0-9]{10}$", message = "El celular debe tener 10 dígitos numéricos")
    @Column(name = "celular", length = 10)
    private String celular;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Column(name = "mail", unique = true, nullable = false, length = 150)
    private String mail;

    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    @Column(name = "direccion", length = 200)
    private String direccion;

    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    @Column(name = "ciudad", length = 50)
    private String ciudad;

    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    @Column(name = "pais", length = 50)
    private String pais;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    // Campos de auditoría
    @Column(name = "fe_creacion", nullable = false, updatable = false)
    private LocalDateTime feCreacion;

    @Column(name = "usr_creacion", nullable = false, updatable = false, length = 50)
    private String usrCreacion;

    @Column(name = "ip_creacion", length = 45)
    private String ipCreacion;

    @Column(name = "fe_modificacion")
    private LocalDateTime feModificacion;

    @Column(name = "usr_modificacion", length = 50)
    private String usrModificacion;

    @Column(name = "ip_modificacion", length = 45)
    private String ipModificacion;

    // Constructor por defecto
    public Usuario() {
    }

    // Constructor con campos principales
    public Usuario(String identificacion, String nombre, String apellido, 
                   LocalDate feNacimiento, String celular, String mail) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.feNacimiento = feNacimiento;
        this.celular = celular;
        this.mail = mail;
        this.activo = true;
    }

    // Métodos de ciclo de vida de JPA
    @PrePersist
    protected void onCreate() {
        feCreacion = LocalDateTime.now();
        if (usrCreacion == null) {
            usrCreacion = "SYSTEM";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        feModificacion = LocalDateTime.now();
        if (usrModificacion == null) {
            usrModificacion = "SYSTEM";
        }
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFeNacimiento() {
        return feNacimiento;
    }

    public void setFeNacimiento(LocalDate feNacimiento) {
        this.feNacimiento = feNacimiento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(LocalDateTime feCreacion) {
        this.feCreacion = feCreacion;
    }

    public String getUsrCreacion() {
        return usrCreacion;
    }

    public void setUsrCreacion(String usrCreacion) {
        this.usrCreacion = usrCreacion;
    }

    public String getIpCreacion() {
        return ipCreacion;
    }

    public void setIpCreacion(String ipCreacion) {
        this.ipCreacion = ipCreacion;
    }

    public LocalDateTime getFeModificacion() {
        return feModificacion;
    }

    public void setFeModificacion(LocalDateTime feModificacion) {
        this.feModificacion = feModificacion;
    }

    public String getUsrModificacion() {
        return usrModificacion;
    }

    public void setUsrModificacion(String usrModificacion) {
        this.usrModificacion = usrModificacion;
    }

    public String getIpModificacion() {
        return ipModificacion;
    }

    public void setIpModificacion(String ipModificacion) {
        this.ipModificacion = ipModificacion;
    }
}

