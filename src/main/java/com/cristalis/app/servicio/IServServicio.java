/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.repositorio.ServicioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IServServicio implements ServServicio{
    
    @Autowired
    private ServicioRepositorio repositorio;

    @Override
    public List<Servicio> listadoServicios() {
        return repositorio.findAll();
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return repositorio.save(servicio);
    }

    @Override
    public Servicio obtenerServicioPorID(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio) {
        return repositorio.save(servicio);
    }

    @Override
    public void eliminarServicio(Long id) {
        repositorio.deleteById(id);
    }
    
    
}
