package org.test01JAVAEEIJGZ.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categoriaIJGZ")
public class CategoriaIJGZ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El nombre es requerido.")
    private String nombreIJGZ;

    @NotBlank(message = "La descripción es requerida.")
    private String descripcionIJGZ;

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

    public String getDescripcionIJGZ() {
        return descripcionIJGZ;
    }

    public void setDescripcionIJGZ(String descripcionIJGZ) {
        this.descripcionIJGZ = descripcionIJGZ;
    }
}
