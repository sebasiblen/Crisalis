package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.ItemDTO;
import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Producto;
import com.cristalis.app.repositorio.ItemRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IItemServicio implements ItemServicio {

    @Autowired
    private ItemRepositorio repositorio;
    @Autowired
    private ProductoServicio productoServicio;

    private List<ItemDTO> lista = new ArrayList<>();

    private List<Item> ordenActual = new ArrayList<>();

    @Override
    public List<Item> listadoItems() {
        return repositorio.findAll();
    }

    @Override
    public Item guardarItem(Item item) {
        return repositorio.save(item);
    }

    @Override
    public Item obtenerItemPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarItem(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<ItemDTO> pasarItemsDTO(Item item) {

        List<ItemDTO> listaDTO = new ArrayList<>();

        ItemDTO dto = new ItemDTO();

        dto.setIdItem(item.getIdItem());
        dto.setDescripcion(item.getDescripcion());
        dto.setPrecio(item.getPrecio());
        dto.setUnidades(item.getUnidades());
        dto.setGarantia(item.getGarantia());
        dto.setMantenimiento(item.getMantenimiento());
        dto.setProducto(item.getProducto());
        dto.setServicio(item.getServicio());

        listaDTO.add(dto);
        lista.add(dto);
        return listaDTO;
    }

    @Override
    public List<ItemDTO> mostrarItemsDTO() {
        return lista;
    }

    @Override
    public List<Item> orden() {
        return ordenActual;
    }

    @Override
    public void agregarItemAOrden(Item item) {
        ordenActual.add(item);
    }

    @Override
    public void borrarOrdenActual() {
        ordenActual.clear();
    }

    @Override
    public void eliminarItemDeOrden(Item item) {
        ordenActual.remove(item);
    }

    @Override
    public void Subtotal(Item item) {
        double subtotal = 0.0;
        if (item.getProducto() != null) {
            subtotal += (item.getProducto().getPrecio() * item.getUnidades());
            item.setSubtotal(subtotal);
        }
        if (item.getServicio() != null) {
            subtotal += (item.getServicio().getPrecio()
                    + item.getServicio().getMantenimiento())
                    * item.getUnidades();
            item.setSubtotal(subtotal);
        }
    }

    @Override
    public void DescontarItemDeStock(Item item) {

        if (item.getProducto() != null) {
            int unidades = item.getUnidades();
            Producto p = productoServicio.obtenerProductoPorID(item.getProducto().getIdBien());
            int stockActual = p.getStock();
            stockActual -= unidades;
            p.setStock(stockActual);
            productoServicio.guardarProducto(p);
        }

    }

    @Override
    public void AdicionarItemDeStock(Item item) {
        if (item.getProducto() != null) {
            int unidades = item.getUnidades();
            Producto p = productoServicio.obtenerProductoPorID(item.getProducto().getIdBien());
            int stockActual = p.getStock();
            stockActual += unidades;
            p.setStock(stockActual);
            productoServicio.guardarProducto(p);
        }
    }
}
