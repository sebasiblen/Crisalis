/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.servicio.ServServicio;
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
public class ServicioController {
    
     @Autowired
    private ServServicio servicioServicio;

    @GetMapping("/servicios")
    public String ListadoDeServicios(Model modelo) {
        modelo.addAttribute("servicios", servicioServicio.listadoServicios());
        return "servicios";
    }

    @GetMapping("/servicios/nuevo")
    public String FormularioServicios(Model modelo) {
        Servicio servicio = new Servicio();
        modelo.addAttribute("servicio", servicio);
        return "nuevo_servicio";
    }

    @PostMapping("/servicios")
    public String AgregarServicio(@ModelAttribute("servicio") Servicio servicio) {
        servicioServicio.guardarServicio(servicio);
        return "redirect:servicios";
    }

    @GetMapping("/servicios/editar/{id}")
    public String EditarServicioFormulario(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("servicio", servicioServicio.obtenerServicioPorID(id));
        return "editar_servicio";
    }

    @PostMapping("/servicios/{id}")
    public String ActualizarServicio(@PathVariable Long id, @ModelAttribute Servicio servicio) {
        Servicio servActual = servicioServicio.obtenerServicioPorID(id);
        servActual.setDescripcion(servicio.getDescripcion());
        servActual.setPrecio(servicio.getPrecio());
        servActual.setMantenimiento(servicio.getMantenimiento());
        servActual.setTipo_impuesto(servicio.getTipo_impuesto());

        servicioServicio.guardarServicio(servActual);

        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}")
    public String EliminarServicio(@PathVariable Long id) {
        servicioServicio.eliminarServicio(id);
        return "redirect:/servicios";
    }
}
