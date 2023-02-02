package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Empresa;
import com.cristalis.app.modelo.Impuesto;
import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.servicio.EmpresaServicio;
import com.cristalis.app.servicio.ImpuestoServicio;
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
    @Autowired
    private ImpuestoServicio impuestoServicio;
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
    
    /**
     *  #### DETALLITO, EN EL CASO DE EXENTO, MUESTRA EL DESCUENTO QUE SE APLICARIA, [PENDIENTE]
     * @param id
     * @return 
     */
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
            pedidoServicio.CalcularImpuestosSegunElTipoDelCliente(nuevoPedido);
            pedidoServicio.aplicarDescuentos(nuevoPedido);
            pedidoServicio.subtotalDelPedido(nuevoPedido);
            pedidoServicio.Total(nuevoPedido);
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
            pedidoServicio.CalcularImpuestosSegunElTipoDelCliente(nuevoPedido);
            pedidoServicio.aplicarDescuentos(nuevoPedido);
            pedidoServicio.subtotalDelPedido(nuevoPedido);
            pedidoServicio.Total(nuevoPedido);
            pedidoServicio.guardarPedido(nuevoPedido);
        }
        // Limpia la orden para realizar un nuevo pedido
        itemServicio.borrarOrdenActual();
        return "redirect:/pedidos";
    }
    
    /**
     * Elimina un pedido
     * @param id
     * @return 
     */
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
    
    /**
     * En la vista de "VER" de pedidos.
     * Me devuelve una lista con todos los pedidos asociados al id del cliente.
     * @param id
     * @param modelo
     * @return 
     */
    @GetMapping("/pedidos/ver/{id}")
    @Transactional
    public String PedidosAsociadosAlCliente(@PathVariable Long id, Model modelo) {
        Pedido p = pedidoServicio.obtenerPedidoPorID(id);
        String cliente = null;
        Long identificador = null;

        if (p.getEmpresa() != null) {
            cliente = p.getEmpresa().getRazonSocial();
            identificador = p.getEmpresa().getIdCliente();
        }
        if (p.getPersona() != null) {
            cliente = p.getPersona().getApellido();
            identificador = p.getPersona().getIdCliente();
        }

//        List<Pedido> pedidos = pedidoServicio.pedidoDiscriminadoPorCliente(cliente);
//        List<Pedido> pedidosAsociados = pedidoServicio.pedidoAsociadoAlCliente(String.valueOf(identificador));
        modelo.addAttribute("pedidos", 
                pedidoServicio.pedidoDiscriminadoPorCliente(cliente));
        modelo.addAttribute("pedidosAsociados", 
                pedidoServicio.pedidoAsociadoAlCliente(String.valueOf(identificador)));
        modelo.addAttribute("pedido", p);
        return "pedidos_disc_cliente";
    }
    
    /**
     * Muestra informacion detallada de los items, el cliente y datos del pedido.
     * @param id
     * @param modelo
     * @return 
     */
    @GetMapping("/pedidos/detalles/{id}")
    public String DetallesPedido(@PathVariable Long id, Model modelo) {
        Pedido pedidoSeleccionado = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("pedido", pedidoSeleccionado);
        modelo.addAttribute("itemsPedido",
                pedidoServicio.crearDTOdeLosItems(pedidoSeleccionado));
        modelo.addAttribute("impuestos",
                pedidoServicio.crearDTOImpuestosExtra(pedidoSeleccionado));
        return "detalles_pedido";
    }
    
    /**
     * Vista de las posibles modificaciones que se pueden aplicar a un pedido.
     * @param id
     * @param modelo
     * @return 
     */
    @GetMapping("/pedidos/editar_pedido/{id}")
    public String EditarPedidoFormulario(@PathVariable Long id, Model modelo) {
//        Pedido pedido = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("pedido", pedidoServicio.obtenerPedidoPorID(id));
        return "editar_pedido";
    }

    /**
     * Editar informacion de los items asociados al pedido seleccionado
     *
     * @param id
     * @param modelo
     * @return
     */
    @GetMapping("/pedidos/editar_pedido/editar_items/{id}")
    public String EditarItemsPedidoPanel(@PathVariable Long id, Model modelo) {
        pedidoTemp = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("pedido", pedidoServicio.obtenerPedidoPorID(id));
        modelo.addAttribute("items", pedidoTemp.getItems());
        return "editar_items_pedido";
    }
    
    /**
     * Modificar datos específicos de los items del pedido
     * @param id
     * @param modelo
     * @return 
     */
    @GetMapping("/pedidos/editar_pedido/editar_items/item/{id}")
    public String EditarItemSeleccionado(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("item", itemServicio.obtenerItemPorID(id));
        return "editar_item_seleccionado_pedido";
    }

    /**
     * Guardar los cambios realizados en el item seleccionado.
     * Actualizar los calculos del pedido luego de modificar algun item.
     * 
     * @param id
     * @param item
     * @return
     */
    @PostMapping("/pedidos/editar_pedido/editar_items/item/{id}")
    public String ActualizarItemSeleccionado(@PathVariable Long id, @ModelAttribute Item item) {
        // Actualizar valores del item modificado
        Item itemActualizado = itemServicio.obtenerItemPorID(id);
        itemActualizado.setMantenimiento(item.getMantenimiento());
        itemActualizado.setGarantia(item.getGarantia());
        itemActualizado.setUnidades(item.getUnidades());
        itemServicio.Subtotal(itemActualizado);
        itemServicio.guardarItem(itemActualizado);
        
        // Acutalizar valores del pedido.
        Pedido p = pedidoServicio.obtenerPedidoPorID(pedidoTemp.getIdPedido());
        pedidoServicio.subtotalDelPedido(p);
        pedidoServicio.Total(p);
        pedidoServicio.aplicarDescuentos(p);
        pedidoServicio.guardarPedido(p);

        var v = p.getIdPedido();
        pedidoTemp = null;
        return "redirect:/pedidos/editar_pedido/editar_items/" + v;
    }

    /**
     * Editar el cliente que esta asociado al pedido.
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
    
    /**
     * Reasignar un nuevo cliente de tipo PERSONA al pedido seleccionado.
     * y acutalizar los valores del pedido, ya que puede cambiar el impeusto
     * dependiendo del tipo de cliente que sea la PERSONA.
     * 
     * @param id
     * @return 
     */
    @GetMapping("/pedidos/editar_pedido/editar_cliente/persona/{id}")
    public String ActualizarPersonaPedido(@PathVariable Long id) {
        Pedido p = pedidoServicio.obtenerPedidoPorID(pedidoTemp.getIdPedido());
        p.setEmpresa(null);
        p.setPersona(null);
        p.setTotal(0.0);
        Persona persona = personaServicio.obtenerPersonaPorID(id);
        p.setPersona(persona);
        pedidoServicio.CalcularImpuestosSegunElTipoDelCliente(p);
        pedidoServicio.subtotalDelPedido(p);
        pedidoServicio.Total(p);
        pedidoServicio.aplicarDescuentos(p);
        pedidoServicio.guardarPedido(p);
        pedidoTemp = null;
        return "redirect:/pedidos";
    }
    
    /**
     * Reasignar un nuevo cliente de tipo EMPRESA al pedido seleccionado.
     * y acutalizar los valores del pedido, ya que puede cambiar el impeusto
     * dependiendo del tipo de cliente que sea la EMPRESA.
     * @param id
     * @return 
     */
    @GetMapping("/pedidos/editar_pedido/editar_cliente/empresa/{id}")
    public String ActualizarEmpresaPedido(@PathVariable Long id) {
        
        Empresa empresa = empresaServicio.obtenerEmpresaPorID(id);
        if (empresa.getPersona() != null) {
            pedidoTemp.setPersona(null);
            pedidoTemp.setEmpresa(null);
            pedidoTemp.setTotal(0.0);
            pedidoTemp.setEmpresa(empresa);
            pedidoTemp.setPersona(empresa.getPersona());
            pedidoServicio.CalcularImpuestosSegunElTipoDelCliente(pedidoTemp);
            pedidoServicio.Total(pedidoTemp);
            pedidoServicio.aplicarDescuentos(pedidoTemp);
            pedidoServicio.guardarPedido(pedidoTemp);
        }
        pedidoTemp = null;
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/impuestos_extras/{id}")
    public String ImpuestosExtra(@PathVariable Long id, Model modelo) {
        pedidoTemp = pedidoServicio.obtenerPedidoPorID(id);
        modelo.addAttribute("impuestos", impuestoServicio.listadoImpuestos());
        modelo.addAttribute("orden", impuestoServicio.orden());
        return "impuesto_extra";
    }

    // Agregar Impuesto
    @GetMapping("/pedidos/impuestos_extras/agregar_impuesto/{id}")
    public String AgregarImpuestosExtraOrden(@PathVariable Long id,
            Model modelo) {
        modelo.addAttribute("impuesto", impuestoServicio.obtenerImpuestoPorID(id));
        return "nuevo_impuesto_extra";
    }

    // Agregar a la orden de impuestos
    @PostMapping("/pedidos/impuestos_extras/agregar_impuesto/{id}")
    public String OrdenImpuestos(@PathVariable Long id) {
        Impuesto i = impuestoServicio.obtenerImpuestoPorID(id);
        impuestoServicio.agregarImpuesto(i);
        var v = pedidoTemp.getIdPedido();
        return "redirect:/pedidos/impuestos_extras/" + v;
    }

    @GetMapping("/pedidos/eliminar_orden/{id}")
    public String EliminarImpuestoOrden(@PathVariable Long id, Model modelo) {
        Impuesto i = impuestoServicio.obtenerImpuestoPorID(id);
        impuestoServicio.eliminarImpuestoOrden(i);
        var v = pedidoTemp.getIdPedido();
        return "redirect:/pedidos/impuestos_extras/" + v;
    }

    @GetMapping("/pedidos/confirmar_impuestos")
    public String ConfirmarImpuestosExtras() {

        /**
         *
         * EL METODO LIMPIAR ORDEN ME BORRA LA LISTA Y PORMAS QUE GUARDE EN UNA
         * NUEVA LISTA NO SE ME ACTUALIZA EL PEDIDO
         */
        // Es necesario pasarlo a una lista local para que no me borre
        // la lista de impuestos, ya que se eliminan al limpiar la orden.
        List<Impuesto> impuestosLista = impuestoServicio.orden();
        impuestoServicio.limpiarOrden();
        Pedido p = pedidoServicio.obtenerPedidoPorID(pedidoTemp.getIdPedido());
        p.setImpuestos(impuestosLista);
        pedidoServicio.guardarPedido(p);
        System.out.println("IMPOUESOTS : " + p.getImpuestos().toString());

        return "redirect:/pedidos";
    }
    
    /**
     * Permite eliminar la lista de impuestos que se iban a adicionar sobre un
     * pedido.
     * @return 
     */
    @GetMapping("/pedidos/cancelar_orden")
    public String CancelarOrdenImpuestosExtras() {
        impuestoServicio.limpiarOrden();
        return "redirect:/pedidos";
    }
}
