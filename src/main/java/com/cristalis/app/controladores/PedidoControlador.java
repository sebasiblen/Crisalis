/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Empresa;
import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.servicio.EmpresaServicio;
import com.cristalis.app.servicio.ItemServicio;
import com.cristalis.app.servicio.PedidoServicio;
import com.cristalis.app.servicio.PersonaServicio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private EmpresaServicio empresaServicio;
    @Autowired
    private ItemServicio itemServicio;

    /*
        Pedido para actualizar
     */
    Pedido pedidoTemp = null;
    Persona personaTemp = null;
    Empresa empresaTemp = null;

    @GetMapping("/pedidos")
    @Transactional
    public String Pedidos(Model modelo,
            @RequestParam(name = "palabra", required = false) String palabraClave,
            @RequestParam(name = "palabraProducto", required = false) String palabraProducto,
            @RequestParam(name = "palabraServicio", required = false) String palabraServicio) {

        if (palabraClave != null && palabraClave != "") {
            modelo.addAttribute("pedidos", pedidoServicio.pedidoDiscriminadoPorCliente(palabraClave));
        } else if (palabraProducto != "" && palabraProducto != null) {
            modelo.addAttribute("pedidos", pedidoServicio.pedidoDiscriminadoPorProducto(palabraProducto));
        } else if (palabraServicio != "" && palabraServicio != null) {
            modelo.addAttribute("pedidos", pedidoServicio.pedidoDiscriminadoPorServicio(palabraServicio));
        } else {
            modelo.addAttribute("pedidos", pedidoServicio.listadoPedidos());
        }

        return "pedidos";
    }

    @GetMapping("/pedidos/confirmacion")
    public String ConfirmacionPedido(Model modelo) {
        modelo.addAttribute("personas", personaServicio.listadoPersonas());
        modelo.addAttribute("empresas", empresaServicio.listadoEmpresas());
        modelo.addAttribute("items", itemServicio.orden());
        return "confirmacion_pedido";
    }

    @GetMapping("/pedidos/confirmacion/{id}")
    public String AsignarClienteAlPedido(@PathVariable Long id) {
        Empresa empresa = null;
        Persona persona = null;
        Pedido nuevoPedido = null;

        if (empresaServicio.obtenerEmpresaPorID(id) != null) {
            empresa = empresaServicio.obtenerEmpresaPorID(id);
            nuevoPedido = new Pedido(empresa, itemServicio.orden());
            //Se agrega el pedido a la empresa
            empresa.getPedidos().add(nuevoPedido);
            for (Item it : itemServicio.orden()) {
                if (!itemServicio.orden().isEmpty()) {
                    // Vincular items con el pedido
                    Item itBDD = itemServicio.obtenerItemPorID(it.getIdItem());
                    itBDD.setPedido(nuevoPedido);
                }
            }
            pedidoServicio.guardarPedido(nuevoPedido);
        } else {
            persona = personaServicio.obtenerPersonaPorID(id);
            nuevoPedido = new Pedido(persona, itemServicio.orden());
            // Se agrega el pedido a la persona
            persona.getPedidos().add(nuevoPedido);
            for (Item it : itemServicio.orden()) {
                if (!itemServicio.orden().isEmpty()) {
                    // Vincular items con el pedido
                    Item itBDD = itemServicio.obtenerItemPorID(it.getIdItem());
                    itBDD.setPedido(nuevoPedido);
                }
            }
            pedidoServicio.guardarPedido(nuevoPedido);
        }
        // Limpia la orden para realizar un nuevo pedido
        itemServicio.borrarOrdenActual();
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        Pedido p = pedidoServicio.obtenerPedidoPorID(id);
        for (Item item : p.getItems()) {
            item.setProducto(null);
            itemServicio.eliminarItem(item.getIdItem());
        }
        pedidoServicio.eliminarPedido(id);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/ver/{id}")
    @Transactional
    public String PedidosAsociadosAlCliente(@PathVariable Long id, Model modelo) {
        Pedido p = pedidoServicio.obtenerPedidoPorID(id);
        String cliente = null;
        if (p.getEmpresa() != null) {
            cliente = p.getEmpresa().getRazonSocial();
        }
        if (p.getPersona() != null) {
            cliente = p.getPersona().getApellido();
        }
        List<Pedido> pedidos = pedidoServicio.pedidoDiscriminadoPorCliente(cliente);
        modelo.addAttribute("pedidos", pedidos);
        modelo.addAttribute("pedido", p);
        return "pedidos_disc_cliente";
    }

    @GetMapping("/pedidos/detalles/{id}")
    public String DetallesPedido(@PathVariable Long id, Model modelo) {
        Pedido pedidoSeleccionado = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("pedido", pedidoSeleccionado);
        modelo.addAttribute("itemsPedido", pedidoSeleccionado.CrearDTOdeLosItems());
        return "detalles_pedido";
    }

    @GetMapping("/pedidos/editar_pedido/{id}")
    public String EditarPedidoFormulario(@PathVariable Long id, Model modelo) {
        Pedido pedido = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("pedido", pedido);
        return "editar_pedido";
    }
    
    /**
     * EDITAR ITEMS DEL PEDIDO
     * @param id
     * @param modelo
     * @return 
     */
    @GetMapping("/pedidos/editar_pedido/editar_items/{id}")
    public String EditarItemsPedidoFormulario(@PathVariable Long id,Model modelo){
        pedidoTemp = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("pedido", pedidoTemp);
        modelo.addAttribute("items", pedidoTemp.getItems());
        return "editar_items_pedido";
    }
    
    @GetMapping("/pedidos/editar_pedido/editar_items/item/{id}")
    public String EditarItemSeleccionado(@PathVariable Long id, Model modelo){
        Item item = itemServicio.obtenerItemPorID(id);
        modelo.addAttribute("item", item);
        return "editar_item_seleccionado_pedido";
    }
    
    @PostMapping("/pedidos/editar_pedido/editar_items/item/{id}")
    public String ActualizarItemSeleccionado(@PathVariable Long id, @ModelAttribute Item item){
        Item itemActualizado = itemServicio.obtenerItemPorID(id);
        itemActualizado.setMantenimiento(item.getMantenimiento());
        itemActualizado.setGarantia(item.getGarantia());
        itemActualizado.setUnidades(item.getUnidades());
        
        itemServicio.guardarItem(itemActualizado);
        
        // falta actualiar los valores del pedido - tras la edicion del item
        
        var v = pedidoTemp.getIdPedido();
        return "redirect:/pedidos/editar_pedido/editar_items/"+v;
    }
    
    /**
     * EDITAR EL CLIENTE DEL PEDIDO
     *
     * @param id
     * @param modelo
     * @return
     */
    @GetMapping("/pedidos/editar_pedido/editar_cliente/{id}")
    public String EditarClientePedidoFormulario(@PathVariable Long id, Model modelo) {
        pedidoTemp = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("pedido", pedidoTemp);
        modelo.addAttribute("personas", personaServicio.listadoPersonas());
        modelo.addAttribute("empresas", empresaServicio.listadoEmpresas());
        return "editar_cliente_pedido";
    }

    @GetMapping("/pedidos/editar_pedido/editar_cliente/persona/{id}")
    public String ActualizarPersonaPedido(@PathVariable Long id) {

        pedidoTemp.setEmpresa(null);
        pedidoTemp.setPersona(null);

        Persona persona = personaServicio.obtenerPersonaPorID(id);
        pedidoTemp.setPersona(persona);
        pedidoServicio.guardarPedido(pedidoTemp);
        pedidoTemp = null;
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/editar_pedido/editar_cliente/empresa/{id}")
    public String ActualizarEmpresaPedido(@PathVariable Long id) {

        Empresa empresa = empresaServicio.obtenerEmpresaPorID(id);
        if (empresa.getPersona() != null) {
            pedidoTemp.setPersona(null);
            pedidoTemp.setEmpresa(null);
            
            pedidoTemp.setEmpresa(empresa);
            pedidoTemp.setPersona(empresa.getPersona());
            pedidoServicio.guardarPedido(pedidoTemp);
        }
        pedidoTemp = null;
        return "redirect:/pedidos";
    }

}
