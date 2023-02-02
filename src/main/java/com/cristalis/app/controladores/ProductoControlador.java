package com.cristalis.app.controladores;

import com.cristalis.app.modelo.Producto;
import com.cristalis.app.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    @GetMapping("/productos")
    public String ListadoDeProductos(Model modelo) {
        modelo.addAttribute("productos", servicio.listadoProductos());
        return "/productos";
    }

    @GetMapping("/productos/formulario")
    public String FormularioProductos(Model modelo) {
        Producto producto = new Producto();
        modelo.addAttribute("producto", producto);
        return "nuevo_producto";
    }

    @PostMapping("/productos")
    public String GuardarNuevoProducto(@ModelAttribute("producto") Producto producto) {
        servicio.guardarProducto(producto);
        return "redirect:productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String EditarProductoFormulario(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("producto", servicio.obtenerProductoPorID(id));
        return "editar_producto";
    }

    @PostMapping("productos/{id}")
    public String ActualizarProducto(@PathVariable Long id, @ModelAttribute Producto producto) {
        Producto prodActual = servicio.obtenerProductoPorID(id);
        prodActual.setDescripcion(producto.getDescripcion());
        prodActual.setPrecio(producto.getPrecio());
        prodActual.setStock(producto.getStock());
        prodActual.setTipo_impuesto(producto.getTipo_impuesto());
        
        servicio.guardarProducto(prodActual);
        return "redirect:/productos";
    }

    @GetMapping("productos/{id}")
    public String EliminarProducto(@PathVariable Long id) {
        servicio.eliminarProducto(id);
        return "redirect:/productos";
    }
}
