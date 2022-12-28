package com.cristalis.app.controladores;

import com.cristalis.app.controladores.DTO.UsuarioRegistroDTO;
import com.cristalis.app.servicio.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author seba
 */
@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    
    private UsuarioServicio usuarioServicio;
    
 
    public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO RetornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String MostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String RegistrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        usuarioServicio.guardarUsuario(registroDTO);
        return "redirect:/registro?exito";
    }
}
