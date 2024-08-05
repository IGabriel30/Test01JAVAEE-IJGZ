package org.test01JAVAEEIJGZ.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test01JAVAEEIJGZ.modelos.MarcaIJGZ;
import org.test01JAVAEEIJGZ.repositorios.IMarcaRepositoryIJGZ;
import org.test01JAVAEEIJGZ.servicios.interfaces.IMarcaServiceIJGZ;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceIJGZ implements IMarcaServiceIJGZ {

    @Autowired
    private IMarcaRepositoryIJGZ marcaRepositoryIJGZ;

    @Override
    public Page<MarcaIJGZ> buscarTodosPaginados(Pageable pageable) {
        return marcaRepositoryIJGZ.findAll(pageable);
    }

    @Override
    public List<MarcaIJGZ> obtenerTodos() {
        return marcaRepositoryIJGZ.findAll();
    }

    @Override
    public Optional<MarcaIJGZ> buscarPorId(Integer id) {
        return marcaRepositoryIJGZ.findById(id);
    }

    @Override
    public MarcaIJGZ crearOEditar(MarcaIJGZ marca) {
        return marcaRepositoryIJGZ.save(marca);
    }

    @Override
    public void eliminarPorId(Integer id) {
        marcaRepositoryIJGZ.deleteById(id);
    }

}
