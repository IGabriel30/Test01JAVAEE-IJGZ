package org.test01JAVAEEIJGZ.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test01JAVAEEIJGZ.modelos.CategoriaIJGZ;

public interface ICategoriaServiceIJGZ {

    Page<CategoriaIJGZ> buscarTodosPaginados(Pageable pageable);

    List<CategoriaIJGZ> obtenerTodos();

    Optional<CategoriaIJGZ> buscarPorId(Integer id);

    CategoriaIJGZ crearOEditar(CategoriaIJGZ categoriaIJGZ);

    void eliminarPorId(Integer id);

}
