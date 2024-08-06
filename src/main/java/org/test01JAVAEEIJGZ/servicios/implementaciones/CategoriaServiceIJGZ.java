package org.test01JAVAEEIJGZ.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test01JAVAEEIJGZ.modelos.CategoriaIJGZ;
import org.test01JAVAEEIJGZ.repositorios.ICategoriaRepositoryIJGZ;
import org.test01JAVAEEIJGZ.servicios.interfaces.ICategoriaServiceIJGZ;

@Service
public class CategoriaServiceIJGZ implements ICategoriaServiceIJGZ{

      @Autowired
    private ICategoriaRepositoryIJGZ categoriaRepositoryIJGZ;

    @Override
    public Page<CategoriaIJGZ> buscarTodosPaginados(Pageable pageable) {
        return categoriaRepositoryIJGZ.findAll(pageable);
    }

    @Override
    public List<CategoriaIJGZ> obtenerTodos() {
        return categoriaRepositoryIJGZ.findAll();
    }

    @Override
    public Optional<CategoriaIJGZ> buscarPorId(Integer id) {
        return categoriaRepositoryIJGZ.findById(id);
    }

    @Override
    public CategoriaIJGZ crearOEditar(CategoriaIJGZ categoriaIJGZ) {
        return categoriaRepositoryIJGZ.save(categoriaIJGZ);
    }

    @Override
    public void eliminarPorId(Integer id) {
        categoriaRepositoryIJGZ.deleteById(id);
    }

}
