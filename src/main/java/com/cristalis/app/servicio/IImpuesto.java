package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Impuesto;
import com.cristalis.app.repositorio.ImpuestoRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IImpuesto implements ImpuestoServicio {

    @Autowired
    private ImpuestoRepositorio repositorio;

    public List<Impuesto> listaImpuestos = new ArrayList<>();

    @Override
    public List<Impuesto> listadoImpuestos() {
        return repositorio.findAll();
    }

    @Override
    public Impuesto obtenerImpuestoPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Impuesto guardarImpuesto(Impuesto impuesto) {
        return repositorio.save(impuesto);
    }

    @Override
    public void eliminarImpuesto(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public Impuesto actualizarImpuesto(Impuesto impuesto) {
        return repositorio.save(impuesto);
    }

    @Override
    public List<Impuesto> orden() {
        return listaImpuestos;
    }

    @Override
    public void limpiarOrden() {
        listaImpuestos.clear();
    }

    @Override
    public void agregarImpuesto(Impuesto impuesto) {
        listaImpuestos.add(impuesto);
    }

    @Override
    public void eliminarImpuestoOrden(Impuesto impuesto) {
        listaImpuestos.remove(impuesto);
    }

}
