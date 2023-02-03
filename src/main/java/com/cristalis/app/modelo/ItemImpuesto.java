package com.cristalis.app.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author Educacion
 */
@Data
@Entity
public class ItemImpuesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idItemImpuesto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "porcentaje")
    private double porcentaje;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_impuesto", referencedColumnName = "idImpuesto")
    private Impuesto impuesto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemImpuesto() {
    }

    public ItemImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

}
