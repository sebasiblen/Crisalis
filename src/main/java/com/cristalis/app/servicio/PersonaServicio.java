package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Persona;
import com.cristalis.app.modelo.Servicio;
import java.util.List;

public interface PersonaServicio {

    public List<Persona> listadoPersonas();

    public List<Persona> filtrarPersonas(String palabraClave);

    public Persona guardarPersona(Persona persona);

    public Persona obtenerPersonaPorID(Long id);

    public List<Persona> buscarPorDniONombre(String palabraClave);

    public Persona actualizarPersona(Persona persona);

    public void eliminarPersona(Long id);

    public List<Servicio> listadoServiciosContratados(Persona persona);

    public List<Servicio> listadoServiciosVencidos(Persona persona);

}
