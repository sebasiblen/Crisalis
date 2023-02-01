/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Empresa;
import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.modelo.Persona;
import com.cristalis.app.modelo.Servicio;
import com.cristalis.app.repositorio.EmpresaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Educacion
 */
@Service
public class IEmpresaServicio implements EmpresaServicio {

    @Autowired
    private EmpresaRepositorio repositorio;

    @Override
    public List<Empresa> listadoEmpresas() {
        return repositorio.findAll();
    }

    @Override
    public List<Empresa> filtrarEmpresas(String palabraClave) {
        if (palabraClave != null) {
            return repositorio.findAll(palabraClave);
        }
        return repositorio.findAll();
    }

    @Override
    public Empresa guardarEmpresa(Empresa empresa) {
        return repositorio.save(empresa);
    }

    @Override
    public Empresa obtenerEmpresaPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Empresa actualizarEmpresa(Empresa empresa) {
        return repositorio.save(empresa);
    }

    @Override
    public void eliminarEmpresa(Long id) {
        Empresa e = repositorio.findById(id).orElse(null);
        Persona p = e.getPersona();
        if (p != null) {
            p.setEmpresa(null);
        }

        e.setPersona(null);
        repositorio.deleteById(id);
    }

    @Override
    public List<Servicio> listadoServiciosContratados(Long id) {

        Empresa e = repositorio.findById(id).orElse(null);

        List<Servicio> servicios = new ArrayList<>();

        for (Pedido pedido : e.getPedidos()) {

            for (Item item : pedido.getItems()) {
                if (item.getServicio() != null) {
                    servicios.add(item.getServicio());
                }
            }

        }

        return servicios;
    }

    @Override
    public List<Servicio> listadoServiciosVencidos(Long id) {

        Empresa e = repositorio.findById(id).orElse(null);

        List<Servicio> serviciosVencidos = new ArrayList<>();

        Date fechaActual = new Date();

        for (Pedido pedido : e.getPedidos()) {
            Date fechaPedido = pedido.getFecha();

            long dif = fechaActual.getTime() - fechaPedido.getTime();

            if (dif > 31) {

                for (Item item : pedido.getItems()) {
                    if (item.getServicio() != null) {

                        serviciosVencidos.add(item.getServicio());
                    }
                }

            }
        }

        return serviciosVencidos;
    }

}
