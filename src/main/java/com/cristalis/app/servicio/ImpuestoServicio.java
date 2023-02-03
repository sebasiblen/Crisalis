package com.cristalis.app.servicio;

import com.cristalis.app.modelo.Impuesto;
import java.util.List;

public interface ImpuestoServicio {

    public List<Impuesto> listadoImpuestos();

    public Impuesto obtenerImpuestoPorID(Long id);

    public Impuesto guardarImpuesto(Impuesto impuesto);

    public Impuesto actualizarImpuesto(Impuesto impuesto);

    public void eliminarImpuesto(Long id);

}
