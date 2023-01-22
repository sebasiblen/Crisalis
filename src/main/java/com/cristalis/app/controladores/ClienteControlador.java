/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores;

import com.cristalis.app.controladores.DTO.ClienteRegistroDTO;
import com.cristalis.app.modelo.Empresa;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.modelo.TipoClienteEnum;
import com.cristalis.app.servicio.EmpresaServicio;
import com.cristalis.app.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
public class ClienteControlador {

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private EmpresaServicio empresaServicio;

    @GetMapping("/clientes")
    public String VistaClientes(Model modelo, @Param("palabraClave") String palabraClave) {
        modelo.addAttribute("personas", personaServicio.filtrarPersonas(palabraClave));
        modelo.addAttribute("empresas", empresaServicio.listadoEmpresas());
        return "clientes";
    }

    @GetMapping("/clientes/formulario")
    public String FormularioDeClientes(Model modelo) {
        ClienteRegistroDTO cliente = new ClienteRegistroDTO(); // recolectar info del formulario
        modelo.addAttribute("cliente", cliente);
        return "nuevo_cliente";
    }

    /**
     * EL REGISTRO DE UN NUEVO CLIENTE REQUIERE DE LA PERSONA OBLIGATORIAMENTE
     * LA EMPRESA ES OPCIONAL.
     *
     * @param cliente
     * @return
     */
    @PostMapping("/clientes/formulario")
    public String AgregarNuevoCliente(@ModelAttribute("cliente") ClienteRegistroDTO cliente) {
        Persona p;
        Empresa e;
        
        if (cliente.getNombre() != null && cliente.getApellido() != null
                && cliente.getDni() != null) {

            p = new Persona(cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getDni(),
                    cliente.getTipo());
            personaServicio.guardarPersona(p);

            if (cliente.getCuit() != null && cliente.getRazonSocial() != null
                    && cliente.getFecha() != null) {

                e = new Empresa(cliente.getCuit(),
                        cliente.getRazonSocial(),
                        cliente.getFecha(),
                        cliente.getTipo());
                p.setTipo(TipoClienteEnum.CONSUMIDOR_FINAL);
                p.setEmpresa(e); // vincular empresa a persona
                e.setPersona(p); // viceversa
                empresaServicio.guardarEmpresa(e);
            }
        }

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/editar_persona/{id}")
    public String EditarPersona(@PathVariable Long id, Model modelo) {

        modelo.addAttribute("persona", personaServicio.obtenerPersonaPorID(id));
        return "editar_persona";
    }

    @GetMapping("/clientes/editar_empresa/{id}")
    public String EditarEmpresa(@PathVariable Long id, Model modelo) {

        modelo.addAttribute("empresa", empresaServicio.obtenerEmpresaPorID(id));
        return "editar_empresa";
    }

    @PostMapping("/clientes/persona/{id}")
    public String ActualizarPersona(@PathVariable Long id, @ModelAttribute("persona") Persona persona) {

        Persona p = personaServicio.obtenerPersonaPorID(id);
        p.setNombre(persona.getNombre());
        p.setApellido(persona.getApellido());
        p.setDni(persona.getDni());
        p.setTipo(persona.getTipo());
        personaServicio.actualizarPersona(p);
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/empresa/{id}")
    public String ActualizarEmpresa(@PathVariable Long id, @ModelAttribute("empresa") Empresa empresa) {

        Empresa e = empresaServicio.obtenerEmpresaPorID(id);
        e.setCuit(empresa.getCuit());
        e.setRazonSocial(empresa.getRazonSocial());
        e.setFecha(empresa.getFecha());
        e.setTipo(empresa.getTipo());
        empresaServicio.actualizarEmpresa(e);
        return "redirect:/clientes";
    }

    
//    @GetMapping("/clientes/vincularEmpresa/{id}")
//    public String VincularEmpresa(@PathVariable Long id, @ModelAttribute("persona")Persona persona){
//            
//        return "redirect:/clientes";
//    }
    
    @GetMapping("/clientes/eliminar_persona/{id}")
    public String EliminarPersona(@PathVariable Long id) {
        personaServicio.eliminarPersona(id);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/eliminar_empresa/{id}")
    public String EliminarEmpresa(@PathVariable Long id) {
        empresaServicio.eliminarEmpresa(id);
        return "redirect:/clientes";
    }
}
