/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.servicio.ItemServicio;
import com.cristalis.app.servicio.PedidoServicio;
import com.cristalis.app.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Educacion
 */
@Controller
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private ItemServicio itemServicio;

    @GetMapping("/pedidos")
    public String Pedidos(Model modelo, @Param("palabraClave") String palabraClave) {
//        personaServicio.buscarPorDniONombre(palabraClave);

        modelo.addAttribute("pedidos", pedidoServicio.listadoPedidos());
        modelo.addAttribute("items", itemServicio.orden());
        return "pedidos";
    }

    @GetMapping("/pedidos/confirmacion")
    public String ConfirmacionPedido(Model modelo) {
        modelo.addAttribute("personas", personaServicio.listadoPersonas());
        return "confirmacion_pedido";
    }

    @GetMapping("/pedidos/confirmacion/{id}")
    public String AsignarPersonaAlPedido(@PathVariable Long id) {
        Persona persona = personaServicio.obtenerPersonaPorID(id);
        Pedido nuevoPedido = new Pedido(persona, itemServicio.orden());
        persona.getPedidos().add(nuevoPedido);
        for (Item it : itemServicio.orden()) {
            if(!itemServicio.orden().isEmpty()){
                Item itBDD = itemServicio.obtenerItemPorID(it.getIdItem());
                itBDD.setPedido(nuevoPedido);
            }
        }
        pedidoServicio.guardarPedido(nuevoPedido);
        itemServicio.borrarOrdenActual();
        return "redirect:/pedidos";
    }
    
    
    @GetMapping("/pedidos/{id}")
    public String eliminarPedido(@PathVariable Long id){
        Pedido p = pedidoServicio.obtenerPedidoPorID(id);
        
        for (Item item : p.getItems()) {
            itemServicio.eliminarItem(item.getIdItem());
        }
        
        pedidoServicio.eliminarPedido(id);
        
        
        return "redirect:/pedidos";
    }
}
