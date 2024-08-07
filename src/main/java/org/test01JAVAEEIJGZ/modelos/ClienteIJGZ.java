package org.test01JAVAEEIJGZ.modelos;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clienteIJGZ")
public class ClienteIJGZ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El nombre es requerido.")
    private String nombreIJGZ;

    @NotBlank(message = "La dirección es requerida.")
    private String direccionIJGZ;

    @NotNull(message = "La fecha de nacimiento es requerida")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimientoIJGZ;

    @NotNull(message = "El sueldo es requerido.")
    private Double sueldoIJGZ;

    @NotNull(message = "El estatus es requerido.")
    private Byte estatusIJGZ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreIJGZ() {
        return nombreIJGZ;
    }

    public void setNombreIJGZ(String nombreIJGZ) {
        this.nombreIJGZ = nombreIJGZ;
    }

    public String getDireccionIJGZ() {
        return direccionIJGZ;
    }

    public void setDireccionIJGZ(String direccionIJGZ) {
        this.direccionIJGZ = direccionIJGZ;
    }

    public LocalDate getFechaNacimientoIJGZ() {
        return fechaNacimientoIJGZ;
    }

    public void setFechaNacimientoIJGZ(LocalDate fechaNacimientoIJGZ) {
        this.fechaNacimientoIJGZ = fechaNacimientoIJGZ;
    }

    public Double getSueldoIJGZ() {
        return sueldoIJGZ;
    }

    public void setSueldoIJGZ(Double sueldoIJGZ) {
        this.sueldoIJGZ = sueldoIJGZ;
    }

    public Byte getEstatusIJGZ() {
        return estatusIJGZ;
    }

    public void setEstatusIJGZ(Byte estatusIJGZ) {
        this.estatusIJGZ = estatusIJGZ;
    }
}
