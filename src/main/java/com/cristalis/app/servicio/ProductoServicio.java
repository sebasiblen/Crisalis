package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Producto;
import java.util.List;

public interface ProductoServicio {

    public List<Producto> listadoProductos();

    public Producto guardarProducto(Producto producto);

    public Producto obtenerProductoPorID(Long id);

    public Producto actualizarProducto(Producto producto);

    public void eliminarProducto(Long id);
}
