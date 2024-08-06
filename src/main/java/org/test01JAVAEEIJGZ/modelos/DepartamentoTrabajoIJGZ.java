package org.test01JAVAEEIJGZ.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "departamentroTrabajoIJGZ")
public class DepartamentoTrabajoIJGZ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El nombre es requerido.")
    private String nombreIJGZ;

    @NotBlank(message = "El teléfono es requerido.")
    private String telefonoIJGZ;

    @NotBlank(message = "El nombre del jefe es requerido.")
    private String jefeIJGZ;

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

    public String getTelefonoIJGZ() {
        return telefonoIJGZ;
    }

    public void setTelefonoIJGZ(String telefonoIJGZ) {
        this.telefonoIJGZ = telefonoIJGZ;
    }

    public String getJefeIJGZ() {
        return jefeIJGZ;
    }

    public void setJefeIJGZ(String jefeIJGZ) {
        this.jefeIJGZ = jefeIJGZ;
    }

}
