package org.test01JAVAEEIJGZ.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "marcaIJGZ")
public class MarcaIJGZ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El nombre es requerido.")
    private String nombreIJGZ;


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

}
