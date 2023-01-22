/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Impuesto;
import com.cristalis.app.repositorio.ImpuestoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IImpuesto implements ImpuestoServicio {

    @Autowired
    private ImpuestoRepositorio repositorio;

    private List<Impuesto> impuestos = new ArrayList<>();

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
    public List<Impuesto> filtrarImpuestosPorDescripcion(String descripcion) {
        return repositorio.sp_impuesto_descripcion(descripcion);
    }

    @Override
    public Impuesto actualizarImpuesto(Impuesto impuesto) {
        return repositorio.save(impuesto);
    }

    @Override
    public List<Impuesto> orden_impuestos() {
        return this.impuestos;
    }

    @Override
    public void agregarImpuesto(Impuesto impuesto) {
        impuestos.add(impuesto);
    }

    @Override
    public void borrarOrdenImpuesto() {
        impuestos.clear();
    }

    @Override
    public void borrarImpuestoDeOrden(Impuesto impuesto) {
        impuestos.remove(impuesto);
    }

}
