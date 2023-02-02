package com.cristalis.app.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@DiscriminatorValue("Empresa")
public class Empresa extends Cliente implements Serializable {

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "iniActividad")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @Column(name = "tipo_comprador", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoClienteEnum tipo;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;

    public Empresa() {
        super();
    }

    public Empresa(String cuit, String razonSocial, Date fecha) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
    }

    public Empresa(String cuit, String razonSocial, Date fecha, TipoClienteEnum tipo) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
        this.tipo = tipo;
    }
}
