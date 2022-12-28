/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Cliente;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.repositorio.PersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IPersonaServicio implements PersonaServicio{
    
    @Autowired
    private PersonaRepositorio repositorio;
    
    @Override
    public List<Persona> listadoPersonas() {
        return repositorio.findAll();
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        return repositorio.save(persona);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Persona obtenerPersonaPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        return repositorio.save(persona);
    }

    @Override
    public void eliminarPersona(Long id) {
        repositorio.deleteById(id);
    }
        
}
