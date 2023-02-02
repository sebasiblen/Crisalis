package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Servicio;
import java.util.List;

public interface ServServicio {

    public List<Servicio> listadoServicios();

    public Servicio guardarServicio(Servicio servicio);

    public Servicio obtenerServicioPorID(Long id);

    public Servicio actualizarServicio(Servicio servicio);

    public void eliminarServicio(Long id);
}
