/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.ItemDTO;
import com.cristalis.app.modelo.Item;
import com.cristalis.app.repositorio.ItemRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IItemServicio implements ItemServicio {

    @Autowired
    private ItemRepositorio repositorio;

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
        dto.setSubtotal(item.getSubtotal());
        dto.setIVA(item.getIVA());
        dto.setIIBB(item.getIIBB());
        dto.setTotal(item.getTotal());
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

}
