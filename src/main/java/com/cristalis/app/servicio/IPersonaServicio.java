package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Empresa;
import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.repositorio.PersonaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPersonaServicio implements PersonaServicio {

    @Autowired
    private PersonaRepositorio repositorio;

    @Autowired
    private ServServicio servicio;

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

//    @Override
//    public List<Servicio> listadoServiciosContratados(Persona p) {
//
//        List<Servicio> servicios = new ArrayList<>();
//        for (Pedido pedido : p.getPedidos()) {
//            for (Item item : pedido.getItems()) {
//                if (item.getServicio() != null) {
//                    servicios.add(item.getServicio());
//                }
//            }
//        }
//
//        return servicios;
//    }
//    @Override
//    public List<Servicio> listadoServiciosVencidos(Persona p) {
//        
//        
//        // En teoria si el pedido que tiene el servicio se realizo hace mas de
//        // un mes, se pasa el servicio a la lista de vencidos.
//        List<Servicio> serviciosVencidos = new ArrayList<>();
//        Date fechaActual = new Date();
//        for (Pedido pedido : p.getPedidos()) {
//            Date fechaPedido = pedido.getFecha();
//            long dif = fechaActual.getTime() - fechaPedido.getTime();
//            if (dif > 31) {
//                for (Item item : pedido.getItems()) {
//                    if (item.getServicio() != null) {
//                        serviciosVencidos.add(item.getServicio());
//                    }
//                }
//            }
//        }
//        
//        // Para que no figure en el listado de contratados. Dado que ya expiró.
//        for (Servicio venc : listadoServiciosVencidos(p)) {
//            for (Servicio cont : listadoServiciosContratados(p)) {
//                if (venc.equals(cont)) {
//                    listadoServiciosContratados(p).remove(cont);
//                }
//            }
//        }
//        return serviciosVencidos;
//    }
//    @Override
//    public void desactivarServicioContratado(Persona persona, Servicio servicio) {
//        
//        for (Servicio s : listadoServiciosContratados(persona)) {
//            if (s.equals(servicio)) {
//                listadoServiciosContratados(persona).remove(s);
//                listadoServiciosVencidos(persona).add(servicio);
//                guardarPersona(persona);
//            }
//        }
    @Override
    public List<Item> listadoServiciosContratados(Persona p) {
        List<Item> servicios = new ArrayList<>();
        for (Pedido pedido : p.getPedidos()) {
            for (Item item : pedido.getItems()) {
                if (item.getServicio() != null) {
                    item.setEstado("Activo");
                    servicios.add(item);
                }
            }
        }

        return servicios;
    }

    @Override
    public List<Item> listadoServiciosVencidos(Persona persona) {
        return null;
    }

    @Override
    public Item desactivarServicio(Persona persona, Item item) {
        
        for (Item sc : listadoServiciosContratados(persona)) {
            
        }
        return null;
    }

}
