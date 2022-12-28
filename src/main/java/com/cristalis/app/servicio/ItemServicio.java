/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Producto;
import com.cristalis.app.modelo.Servicio;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface ItemServicio {
    
    public List<Item> listadoItems();
    
    public Item guardarItem(Item item);
    
    public Item agregarProducto(Producto producto);
    
    public Item agregarServicio(Servicio servicio);
    
    public void eliminarItem(Long id);
}
