/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Producto;
import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.servicio.ItemServicio;
import com.cristalis.app.servicio.ProductoServicio;
import com.cristalis.app.servicio.ServServicio;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Educacion
 */
@Controller
public class ItemControlador {

    @Autowired
    private ItemServicio itemServicios;
    @Autowired
    private ServServicio servServicios;
    @Autowired
    private ProductoServicio productoServicios;
    
    /*
        Test1-
        1-  crear lista<Item>  nueva que vaya agregando los items que selecciono
        2-  pasar la lista a pedidos. Puede ser un dto o la misma lista
        3- limpiar la lista<Item> para los proximos pedidos
    */
    
//    private List<Item> listadoCarrito = new ArrayList<>();
    @GetMapping("/carrito")
    public String Carrito(Model modelo) {
        modelo.addAttribute("productos", productoServicios.listadoProductos());
        modelo.addAttribute("servicios", servServicios.listadoServicios());
        
//        modelo.addAttribute("items", itemServicios.mostrarItemsDTO());
        modelo.addAttribute("items", itemServicios.orden());
        return "carrito";
    }

    //Agregar prooducto
    @GetMapping("/carrito/producto/{id}")
    public String ItemFormulario(Model modelo, @PathVariable Long id) {
        Item item = new Item();
        modelo.addAttribute("producto", productoServicios.obtenerProductoPorID(id));
        modelo.addAttribute("item", item);
        return "nuevo_item";
    }

    @PostMapping("/carrito/producto/{id}")
    public String NuevoItem(@ModelAttribute("item") Item item, @PathVariable Long id) {
        Producto p = productoServicios.obtenerProductoPorID(id);
        item.setProducto(p);
        item.setSubtotal(item.SubTotal());
        item.setTotal(item.Total());
//        itemServicios.pasarItemsDTO(item);
        itemServicios.agregarItemAOrden(item);
        itemServicios.guardarItem(item);
        return "redirect:/carrito";
    }

    //Agregar servicio
    @GetMapping("/carrito/servicio/{id}")
    public String ItemFormularioServ(Model modelo, @PathVariable Long id) {
        Item item = new Item();
        modelo.addAttribute("servicio", servServicios.obtenerServicioPorID(id));
        modelo.addAttribute("item", item);
        return "nuevo_item_serv";
    }

    @PostMapping("/carrito/servicio/{id}")
    public String NuevoItemServ(@ModelAttribute("item") Item item, @PathVariable Long id) {
        Servicio s = servServicios.obtenerServicioPorID(id);
        item.setServicio(s);
        item.setSubtotal(item.SubTotal());
        item.setTotal(item.Total());
//        itemServicios.pasarItemsDTO(item);
        itemServicios.agregarItemAOrden(item);
        itemServicios.guardarItem(item);
        return "redirect:/carrito";
    }

    @GetMapping("carrito/eliminar/{id}")
    public String EliminarItem(@PathVariable Long id) {
        itemServicios.eliminarItem(id);
        return "redirect:/carrito";
    }
    
    
}
