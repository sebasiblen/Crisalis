package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Producto extends Bien implements Serializable {

    private int stock;
    // Un producto se corresponde con un unico item

    public Producto() {
    }

    public Producto(String descripcion, double precio, int stock) {
        super(descripcion, precio);
        this.stock = stock;
    }

    public Producto(int stock, String descripcion, Double precio, TipoImpuestoEnum tipo_impuesto) {
        super(descripcion, precio, tipo_impuesto);
        this.stock = stock;
    }

}
