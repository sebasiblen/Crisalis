package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Item;
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

//    public List<Servicio> listadoServiciosContratados(Persona persona);
    
    public List<Item> listadoServiciosContratados(Persona persona);

//    public List<Servicio> listadoServiciosVencidos(Persona persona);
    
    public List<Item> listadoServiciosVencidos(Persona persona);
    
    public Item desactivarServicio(Persona persona, Item item);
    
//    public void desactivarServicioContratado(Persona persona, Servicio servicio);
    
//    public void reactivarServicioContratado(Persona persona, Servicio servicio);
}


/**
 *  YA que lo de los servicios del cliente no me esta funcionando.
 *  Otra estrategia que talvez funcione sería trabajar con el item directamente.
 *  en lugar de filtrar hasta el servicio en si. Directamente copiar los items
 *  y pasarlos a la lista de serviciosContratados y jugar desde ahí.
 * 
 *  Lo de activar y desactivar no debería tener ninguna funcionalidad aparte de
 * mostrar que está activo o desactivado. Si lo hago así es mas una modificacion
 * en el front que reaccione dependiendo si apreto o no el boton. 
 * 
 * 
 * [](O_O)[].
 * 
 * 
 */