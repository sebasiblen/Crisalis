/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Servicio;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface ServServicio {
    
    public List<Servicio> listadoServicios();

    public Servicio guardarServicio(Servicio servicio);

    public Servicio obtenerServicioPorID(Long id);

    public Servicio actualizarServicio(Servicio servicio);

    public void eliminarServicio(Long id);
}
