package org.test01JAVAEEIJGZ.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test01JAVAEEIJGZ.modelos.DepartamentoTrabajoIJGZ;

public interface IDepartamentoTrabajoServiceIJGZ {

Page<DepartamentoTrabajoIJGZ> buscarTodosPaginados(Pageable pageable);

    List<DepartamentoTrabajoIJGZ> obtenerTodos();

    Optional<DepartamentoTrabajoIJGZ> buscarPorId(Integer id);

    DepartamentoTrabajoIJGZ crearOEditar(DepartamentoTrabajoIJGZ departamentoTrabajo);

    void eliminarPorId(Integer id);

}
