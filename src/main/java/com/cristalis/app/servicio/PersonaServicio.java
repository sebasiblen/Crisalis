/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Persona;
import com.cristalis.app.modelo.Servicio;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface PersonaServicio {

    public List<Persona> listadoPersonas();
    
    public List<Persona> filtrarPersonas(String palabraClave);
    
    public Persona guardarPersona(Persona persona);

    public Persona obtenerPersonaPorID(Long id);

    public List<Persona> buscarPorDniONombre(String palabraClave);

    public Persona actualizarPersona(Persona persona);

    public void eliminarPersona(Long id);
    
    public List<Servicio> listadoServiciosContratoados(Long id);

}
