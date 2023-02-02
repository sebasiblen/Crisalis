package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Empresa;
import com.cristalis.app.modelo.Servicio;
import java.util.List;

public interface EmpresaServicio {

    public List<Empresa> listadoEmpresas();

    public List<Empresa> filtrarEmpresas(String palabraClave);

    public Empresa guardarEmpresa(Empresa empresa);

    public Empresa obtenerEmpresaPorID(Long id);

    public Empresa actualizarEmpresa(Empresa empresa);

    public void eliminarEmpresa(Long id);

    public List<Servicio> listadoServiciosContratados(Empresa empresa);

    public List<Servicio> listadoServiciosVencidos(Empresa empresa);

}
