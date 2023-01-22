/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Cliente;
import com.cristalis.app.modelo.Empresa;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface EmpresaServicio {

    public List<Empresa> listadoEmpresas();

    public Empresa guardarEmpresa(Empresa empresa);

    public Empresa obtenerEmpresaPorID(Long id);
    
    public Empresa actualizarEmpresa(Empresa empresa);
    
    public void eliminarEmpresa(Long id);
    
}
