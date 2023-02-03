package com.cristalis.app.servicio;

import com.cristalis.app.modelo.ItemImpuesto;
import java.util.List;

public interface ItemImpuestoServicio {
    
    public List<ItemImpuesto> listadoItemsImpuestos();

    public ItemImpuesto obtenerItemImpuestoPorID(Long id);

    public ItemImpuesto guardarItemImpuesto(ItemImpuesto item);

    public void eliminarItemImpuesto(Long id);
    
    // Listado de impuestos a agregar
    public List<ItemImpuesto> orden();

    public void agregarItemImpuestoOrden(ItemImpuesto impuesto);

    public void eliminarItemImpuestoOrden(ItemImpuesto impuesto);

    public void limpiarOrden();
}
