/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Cliente;
import com.cristalis.app.modelo.Persona;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Educacion
 */

public interface PersonaServicio extends UserDetailsService{
    
    public List<Persona> listadoPersonas();
    
    public Persona guardarPersona(Persona persona);
    
    public Persona obtenerPersonaPorID(Long id);
    
    public Persona actualizarPersona(Persona persona);
    
    public void eliminarPersona(Long id);
}
