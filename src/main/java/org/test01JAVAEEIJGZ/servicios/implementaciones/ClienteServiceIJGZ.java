package org.test01JAVAEEIJGZ.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test01JAVAEEIJGZ.modelos.ClienteIJGZ;
import org.test01JAVAEEIJGZ.repositorios.IClienteRepositoryIJGZ;
import org.test01JAVAEEIJGZ.servicios.interfaces.IClienteServiceIJGZ;

@Service
public class ClienteServiceIJGZ implements IClienteServiceIJGZ {

    @Autowired
    private IClienteRepositoryIJGZ clienteRepositoryIJGZ;

    @Override
    public Page<ClienteIJGZ> buscarTodosPaginados(Pageable pageable) {
  
        return clienteRepositoryIJGZ.findAll(pageable);
    }

    @Override
    public List<ClienteIJGZ> obtenerTodos() {
        return clienteRepositoryIJGZ.findAll();
    }

    @Override
    public Optional<ClienteIJGZ> buscarPorId(Integer id) {
        return clienteRepositoryIJGZ.findById(id);
    }

    @Override
    public ClienteIJGZ crearOEditar(ClienteIJGZ clienteIJGZ) {
        return clienteRepositoryIJGZ.save(clienteIJGZ);
    }

    @Override
    public void eliminarPorId(Integer id) {
        clienteRepositoryIJGZ.deleteById(id);
    }

}
