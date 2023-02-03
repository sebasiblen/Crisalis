package com.cristalis.app.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @javax.persistence.Id
    private Long idPedido;

    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "IVA")
    private double IVA;
    @Column(name = "IIBB")
    private double IIBB;
    @Column(name = "descuento")
    private double descuento;
    @Column(name = "total")
    private double total;
    @Column(name = "fecha_pedido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    // Muchos pedidos corresponden a un cliente (Persona)
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Un pedido le corresponden muchos items
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Item> items;

    @OneToMany(mappedBy = "pedido")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ItemImpuesto> ItemImpuestos;

    public Pedido() {

    }

    public Pedido(Persona persona, List<Item> items) {
        this.fecha = new Date();
        this.persona = persona;
        this.items = items;
    }

    public Pedido(Empresa empresa, List<Item> items) {
        this.fecha = new Date();
        this.empresa = empresa;
        this.items = items;
    }
}
