/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristalis.app.controladores.DTO;

import com.cristalis.app.modelo.Producto;
import com.cristalis.app.modelo.Servicio;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Educacion
 */
@Data
public class ItemDTO implements Serializable{

    private Long idItem;
    private String descripcion;
    private double precio;
    private int unidades;
    private int garantia;
    private double mantenimiento;
    private double subtotal;
    private double IVA;
    private double IIBB;
    private double total;

    private Producto producto;
    private Servicio servicio;

    public ItemDTO() {
    }

    public ItemDTO(String descripcion, double precio, int unidades, int garantia, double mantenimiento, double subtotal, double IVA, double IIBB, double total, Producto producto, Servicio servicio) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades = unidades;
        this.garantia = garantia;
        this.mantenimiento = mantenimiento;
        this.subtotal = subtotal;
        this.IVA = IVA;
        this.IIBB = IIBB;
        this.total = total;
        this.producto = producto;
        this.servicio = servicio;
    }

    public ItemDTO(Long idItem, String descripcion, double precio, int unidades, int garantia, double mantenimiento, double subtotal, double IVA, double IIBB, double total, Producto producto, Servicio servicio) {
        this.idItem = idItem;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades = unidades;
        this.garantia = garantia;
        this.mantenimiento = mantenimiento;
        this.subtotal = subtotal;
        this.IVA = IVA;
        this.IIBB = IIBB;
        this.total = total;
        this.producto = producto;
        this.servicio = servicio;
    }

}
