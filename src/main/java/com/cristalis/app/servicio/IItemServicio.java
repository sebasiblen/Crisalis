/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Producto;
import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.repositorio.ItemRepositorio;
import com.cristalis.app.repositorio.ProductoRepositorio;
import com.cristalis.app.repositorio.ServicioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IItemServicio implements ItemServicio{
    
    @Autowired
    private ItemRepositorio repositorio;
    
    @Override
    public List<Item> listadoItems() {
        return repositorio.findAll();
    }

    @Override
    public Item agregarProducto(Producto producto) {
        Item nuevoItem = new Item();
        nuevoItem.setProducto(producto);
        return nuevoItem;
    }

    @Override
    public Item agregarServicio(Servicio servicio) {
        Item nuevoItem = new Item();
        nuevoItem.setServicio(servicio);
        return nuevoItem;
    }

    @Override
    public Item guardarItem(Item item) {
        return repositorio.save(item);
    }

    @Override
    public void eliminarItem(Long id) {
        repositorio.deleteById(id);
    }
    
}
