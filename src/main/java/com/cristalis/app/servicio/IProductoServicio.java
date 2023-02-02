package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Producto;
import com.cristalis.app.repositorio.ProductoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IProductoServicio implements ProductoServicio {

    @Autowired
    private ProductoRepositorio repositorio;

    @Override
    public List<Producto> listadoProductos() {
        return repositorio.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    public Producto obtenerProductoPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        repositorio.deleteById(id);
    }

}
