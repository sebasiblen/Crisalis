/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Impuesto;
import java.util.List;

/**
 *
 * @author Educacion
 */
public interface ImpuestoServicio {
    
    public List<Impuesto> listadoImpuestos();
    
    public Impuesto obtenerImpuestoPorID(Long id);
    
    public Impuesto guardarImpuesto(Impuesto impuesto);
    
    public Impuesto actualizarImpuesto(Impuesto impuesto);
    
    public void eliminarImpuesto(Long id);
    
    // Listado de impuestos a agregar
    
    public List<Impuesto> orden();
    
    public void agregarImpuesto(Impuesto impuesto);
    
    public void eliminarImpuestoOrden(Impuesto impuesto);
    
    public void limpiarOrden();
}
