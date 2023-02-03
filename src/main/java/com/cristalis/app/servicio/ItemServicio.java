package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.ItemDTO;
import com.cristalis.app.modelo.Item;
import java.util.List;

public interface ItemServicio {

    public List<Item> listadoItems();

    public Item obtenerItemPorID(Long id);

    public Item guardarItem(Item item);

    public void eliminarItem(Long id);

    // FUNCIONA SIN USAR DTO
    public List<Item> orden();

    public void agregarItemAOrden(Item item);

    public void borrarOrdenActual();

    public void eliminarItemDeOrden(Item item);

    public List<ItemDTO> pasarItemsDTO(Item item);

    public List<ItemDTO> mostrarItemsDTO();

    public void Subtotal(Item item);
    
    public void DescontarItemDeStock(Item item);

    public void AdicionarItemDeStock(Item item);
    
    public void desactivarServicio( Item item);
    
}
