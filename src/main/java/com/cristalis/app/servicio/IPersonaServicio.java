/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Empresa;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.repositorio.PersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IPersonaServicio implements PersonaServicio {

    @Autowired
    private PersonaRepositorio repositorio;

    @Override
    public List<Persona> listadoPersonas() {
        return repositorio.findAll();
    }

    @Override
    public List<Persona> filtrarPersonas(String palabraClave) {
        if (palabraClave != null) {
            return repositorio.findAll(palabraClave);
        }
        return repositorio.findAll();
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        return repositorio.save(persona);
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
        Persona p = repositorio.findById(id).orElse(null);
        Empresa e = p.getEmpresa();
        if (e != null) {
            e.setPersona(null);
        }
        p.setEmpresa(null);
        repositorio.deleteById(id);
    }

    /**
     * BUSQUEDA PERSONA POR DNI O NOMBRE si el parametro es nulo devuelve todos
     * las personas.
     *
     * @param palabraClave
     * @return
     */
    @Override
    public List<Persona> buscarPorDniONombre(String palabraClave) {
        if (palabraClave != null) {
            return repositorio.findAll(palabraClave);
        }
        return repositorio.findAll();
    }

    @Override
    public List<Servicio> listadoServiciosContratoados(Long id) {
        Persona p = repositorio.findById(id).orElse(null);
        return p.ServiciosContratados();
    }

}
