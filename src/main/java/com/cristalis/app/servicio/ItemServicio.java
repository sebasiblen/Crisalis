/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.ItemDTO;
import com.cristalis.app.modelo.Item;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface ItemServicio {
    
    public List<Item> listadoItems();
    
    // FUNCIONA SIN USAR DTO
    public List<Item> orden();
    
    public void agregarItemAOrden(Item item);
    
    public void borrarOrdenActual();
    
    //
    public List<ItemDTO> pasarItemsDTO(Item item);
    
    public List<ItemDTO> mostrarItemsDTO();
    
    public Item guardarItem(Item item);
    
    public void eliminarItem(Long id);
    
}
