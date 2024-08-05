package org.test01JAVAEEIJGZ.servicios.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test01JAVAEEIJGZ.modelos.MarcaIJGZ;

import java.util.List;
import java.util.Optional;

public interface IMarcaServiceIJGZ {

    Page<MarcaIJGZ> buscarTodosPaginados(Pageable pageable);

    List<MarcaIJGZ> obtenerTodos();

    Optional<MarcaIJGZ> buscarPorId(Integer id);

    MarcaIJGZ crearOEditar(MarcaIJGZ marca);

    void eliminarPorId(Integer id);

}
