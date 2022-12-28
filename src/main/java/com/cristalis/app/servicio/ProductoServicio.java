/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Producto;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface ProductoServicio {

    public List<Producto> listadoProductos();

    public Producto guardarProducto(Producto producto);

    public Producto obtenerProductoPorID(Long id);

    public Producto actualizarProducto(Producto producto);

    public void eliminarProducto(Long id);
}
