package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.repositorio.ServicioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IServServicio implements ServServicio {

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
