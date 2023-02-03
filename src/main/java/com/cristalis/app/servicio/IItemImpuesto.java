package com.cristalis.app.servicio;

import com.cristalis.app.modelo.ItemImpuesto;
import com.cristalis.app.repositorio.ItemImpuestoRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IItemImpuesto implements ItemImpuestoServicio{
    
    @Autowired
    private ItemImpuestoRepositorio repositorio;
    
    private List<ItemImpuesto> ordenActual = new ArrayList<>();
    
    @Override
    public List<ItemImpuesto> listadoItemsImpuestos() {
        return repositorio.findAll();
    }

    @Override
    public ItemImpuesto obtenerItemImpuestoPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public ItemImpuesto guardarItemImpuesto(ItemImpuesto item) {
        return repositorio.save(item);
    }

    @Override
    public void eliminarItemImpuesto(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<ItemImpuesto> orden() {
        return ordenActual;
    }

    @Override
    public void agregarItemImpuestoOrden(ItemImpuesto impuesto) {
        ordenActual.add(impuesto);
    }

    @Override
    public void eliminarItemImpuestoOrden(ItemImpuesto impuesto) {
        ordenActual.remove(impuesto);
    }

    @Override
    public void limpiarOrden() {
        ordenActual.clear();
    }
    
}
