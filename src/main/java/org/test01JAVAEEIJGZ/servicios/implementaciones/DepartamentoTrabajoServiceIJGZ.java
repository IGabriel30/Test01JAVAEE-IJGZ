package org.test01JAVAEEIJGZ.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test01JAVAEEIJGZ.modelos.DepartamentoTrabajoIJGZ;
import org.test01JAVAEEIJGZ.repositorios.IDepartamentoTrabajoRepositoryIJGZ;
import org.test01JAVAEEIJGZ.servicios.interfaces.IDepartamentoTrabajoServiceIJGZ;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoTrabajoServiceIJGZ implements IDepartamentoTrabajoServiceIJGZ {

  @Autowired
    private IDepartamentoTrabajoRepositoryIJGZ departamentoTrabajoRepositoryIJGZ;

    @Override
    public Optional<DepartamentoTrabajoIJGZ> buscarPorId(Integer id) {
        return departamentoTrabajoRepositoryIJGZ.findById(id);
    }

    @Override
    public Page<DepartamentoTrabajoIJGZ> buscarTodosPaginados(Pageable pageable) {
        return departamentoTrabajoRepositoryIJGZ.findAll(pageable);
    }

    @Override
    public DepartamentoTrabajoIJGZ crearOEditar(DepartamentoTrabajoIJGZ departamentoTrabajo) {
        return departamentoTrabajoRepositoryIJGZ.save(departamentoTrabajo);
    }

    @Override
    public void eliminarPorId(Integer id) {
        departamentoTrabajoRepositoryIJGZ.deleteById(id);
    }

    @Override
    public List<DepartamentoTrabajoIJGZ> obtenerTodos() {
        return departamentoTrabajoRepositoryIJGZ.findAll();
    }



}
