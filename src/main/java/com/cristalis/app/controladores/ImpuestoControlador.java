/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Impuesto;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.servicio.ImpuestoServicio;
import com.cristalis.app.servicio.PedidoServicio;
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
public class ImpuestoControlador {

    @Autowired
    private ImpuestoServicio impuestoServicio;

    @GetMapping("/impuestos")
    public String Impuestos(Model modelo) {
        modelo.addAttribute("impuestos", impuestoServicio.listadoImpuestos());
        return "impuestos";
    }

    @GetMapping("/impuestos/formulario")
    public String FormularioImpuestos(Model modelo) {
        Impuesto impuesto = new Impuesto();
        modelo.addAttribute("impuesto", impuesto);
        return "nuevo_impuesto";
    }

    @PostMapping("/impuestos")
    public String GuardarImpuesto(@ModelAttribute("impuesto") Impuesto impuesto) {
        impuestoServicio.guardarImpuesto(impuesto);
        return "redirect:/impuestos";
    }

    @GetMapping("/impuestos/editar/{id}")
    public String EditarImpuesto(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("impuesto", impuestoServicio.obtenerImpuestoPorID(id));
        return "editar_impuesto";
    }

    @PostMapping("/impuestos/{id}")
    public String ActualizarImpuesto(@ModelAttribute("impuesto") Impuesto impuesto, @PathVariable Long id) {
        Impuesto impuestoActualizado = impuestoServicio.obtenerImpuestoPorID(id);
        impuestoActualizado.setDescripcion(impuesto.getDescripcion());
        impuestoActualizado.setPorcentaje(impuesto.getPorcentaje());
        impuestoServicio.actualizarImpuesto(impuestoActualizado);
        return "redirect:/impuestos";
    }

    @GetMapping("/impuestos/eliminar/{id}")
    public String EliminarImpuesto(@PathVariable Long id) {
        impuestoServicio.eliminarImpuesto(id);
        return "redirect:/impuestos";
    }

}
