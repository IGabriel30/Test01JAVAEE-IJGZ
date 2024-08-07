package org.test01JAVAEEIJGZ.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test01JAVAEEIJGZ.modelos.ClienteIJGZ;

public interface IClienteServiceIJGZ {


    Page<ClienteIJGZ> buscarTodosPaginados(Pageable pageable);

    List<ClienteIJGZ> obtenerTodos();

    Optional<ClienteIJGZ> buscarPorId(Integer id);

    ClienteIJGZ crearOEditar(ClienteIJGZ clienteIJGZ);

    void eliminarPorId(Integer id);
}
