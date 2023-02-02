package com.cristalis.app.servicio;

import com.cristalis.app.controladores.DTO.ImpuestoDTO;
import com.cristalis.app.controladores.DTO.ItemDTO;
import com.cristalis.app.modelo.Impuesto;
import com.cristalis.app.modelo.Item;
import com.cristalis.app.modelo.Pedido;
import com.cristalis.app.modelo.Persona;
import static com.cristalis.app.modelo.TipoClienteEnum.CONSUMIDOR_FINAL;
import static com.cristalis.app.modelo.TipoClienteEnum.EXENTO;
import static com.cristalis.app.modelo.TipoClienteEnum.EXPORTADOR;
import static com.cristalis.app.modelo.TipoClienteEnum.HOTEL_SERVICIO_ALOJAMIENTO_TURISTA;
import static com.cristalis.app.modelo.TipoClienteEnum.MONOTRIBUTISTA;
import com.cristalis.app.modelo.TipoImpuestoEnum;
import com.cristalis.app.repositorio.PedidoRepositorio;
import com.cristalis.app.repositorio.PersonaRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPedidoServicio implements PedidoServicio {

    @Autowired
    private PedidoRepositorio repositorio;

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private EmpresaServicio empresaServicio;

    @Override
    public List<Pedido> listadoPedidos() {
        return repositorio.findAll();
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return repositorio.save(pedido);
    }

    @Override
    public Pedido obtenerPedidoPorID(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarPedido(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Pedido> pedidoDiscriminadoPorCliente(String cliente) {
        return repositorio.sp_pedidos_disc_cliente(cliente);
    }

    @Override
    public List<Pedido> pedidoDiscriminadoPorServicio(String servicio) {
        return repositorio.sp_pedidos_disc_servicio(servicio);
    }

    @Override
    public List<Pedido> pedidoDiscriminadoPorProducto(String producto) {
        return repositorio.sp_pedidos_disc_producto(producto);
    }

    @Override
    public List<Pedido> pedidoAsociadoAlCliente(String cliente) {
        return repositorio.sp_pedidos_asociados(cliente);
    }

    @Override
    public List<ItemDTO> crearDTOdeLosItems(Pedido p) {

        List<ItemDTO> listaDTO = new ArrayList<>();

        if (!p.getItems().isEmpty()) {
            for (Item item : p.getItems()) {

                ItemDTO dto = new ItemDTO();

                dto.setIdItem(item.getIdItem());
                dto.setDescripcion(item.getDescripcion());
                dto.setPrecio(item.getPrecio());
                dto.setUnidades(item.getUnidades());
                dto.setGarantia(item.getGarantia());
                dto.setMantenimiento(item.getMantenimiento());
                dto.setSubtotal(item.getSubtotal());
                dto.setProducto(item.getProducto());
                dto.setServicio(item.getServicio());

                listaDTO.add(dto);
            }
        }

        return listaDTO;
    }

    @Override
    public List<ImpuestoDTO> crearDTOImpuestosExtra(Pedido p) {

        List<ImpuestoDTO> listaDto = new ArrayList<>();

        if (!p.getImpuestos().isEmpty()) {

            for (Impuesto imp : p.getImpuestos()) {

                ImpuestoDTO dto = new ImpuestoDTO();

                dto.setIdImpuesto(imp.getIdImpuesto());
                dto.setDescripcion(imp.getDescripcion());
                dto.setPorcentaje(imp.getPorcentaje());

                listaDto.add(dto);
            }

        }
        return listaDto;
    }

    @Override
    public List<ItemDTO> productosEnPedidoActual(Pedido p) {

        List<ItemDTO> productos = new ArrayList<>();
        for (ItemDTO item : this.crearDTOdeLosItems(p)) {
            if (item.getProducto() != null) {
                productos.add(item);
            }
        }

        return productos;
    }

    @Override
    public void subtotalDelPedido(Pedido p) {
        double subtotalPedido = 0.0;
        for (ItemDTO item : crearDTOdeLosItems(p)) {
            subtotalPedido += item.getSubtotal();
        }
        p.setSubtotal(subtotalPedido);
    }

    @Override
    public void actualizarSubtotalDelPedido(Pedido p) {

        p.setSubtotal(0);
        double subtotal = 0.0;
        for (Item item : p.getItems()) {
            subtotal += item.getSubtotal();
        }
        p.setSubtotal(subtotal);
    }

    @Override
    public double calcularTopeDescuento(Pedido p) {
        double descuentos = 0.0;
        List<ItemDTO> productos = productosEnPedidoActual(p);

        //SI TIENE SERVICIOS CONTRATADOS PREVIAMENTE
        // Se calcula el descuento que se va a aplicar a los productos.
        if (p.getPersona() != null) {
            if (!personaServicio.listadoServiciosContratados(p.getPersona())
                    .isEmpty() || estaAlgunServicioEnOrden(p)) {
                for (ItemDTO producto : productos) {
                    descuentos += (producto.getSubtotal() * 0.10);
                }
            }
        }

        if (p.getEmpresa() != null) {
            if (!empresaServicio.listadoServiciosContratados(p.getEmpresa())
                    .isEmpty() || estaAlgunServicioEnOrden(p)) {
                for (ItemDTO producto : productos) {
                    descuentos += (producto.getSubtotal() * 0.10);
                }
            }
        }
        return descuentos;
    }

    @Override
    public void actualizarDescuentoDelPedido(Pedido p) {
        p.setDescuento(0.0);
        double topeDesc = calcularTopeDescuento(p);
        if (topeDesc <= 2500) {
            p.setTotal(p.getTotal() - topeDesc);
        } else {
            p.setTotal(p.getTotal() - 2500);
            topeDesc = 2500;
        }
        p.setDescuento(topeDesc);
    }

    @Override
    public boolean estaAlgunServicioEnOrden(Pedido p) {
        for (ItemDTO serv : crearDTOdeLosItems(p)) {
            if (serv.getServicio() != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void aplicarDescuentos(Pedido p) {
        double descuentoA = calcularTopeDescuento(p);
        double total = p.getTotal();

        if (descuentoA <= 2500) {
            total -= descuentoA;
            p.setTotal(total);
        } else {
            total -= 2500;
            descuentoA = 2500;
            p.setTotal(total);
        }

        p.setDescuento(descuentoA);
    }

    @Override
    public void CalcularImpuestosSegunElTipoDelCliente(Pedido p) {
        if (p.getEmpresa() != null) {
            switch (p.getEmpresa().getTipo()) {
                case EXPORTADOR:
                    p.setIVA(0.37);
                    p.setIIBB(0.035);
                    break;
                case HOTEL_SERVICIO_ALOJAMIENTO_TURISTA:
                    p.setIVA(0.35);
                    p.setIIBB(0.035);
                    break;
                case EXENTO:
                    break;
                default:
                    p.setIVA(0.0);
                    p.setIIBB(0.0);
            }
        } else {
            switch (p.getPersona().getTipo()) {
                case MONOTRIBUTISTA:
                    p.setIVA(0.27);
                    p.setIIBB(0.035);
                    break;
                case CONSUMIDOR_FINAL:
                    p.setIVA(0.21);
                    p.setIIBB(0.035);
                    break;
                default:
                    p.setIVA(0.0);
                    p.setIIBB(0.0);
            }
        }
    }

    @Override
    public void AgregarImpuestosExtras(Pedido p) {
        double total = p.getTotal();
        for (Impuesto impuesto : p.getImpuestos()) {
            total = total + (total * impuesto.getPorcentaje());
        }
        p.setTotal(total);
    }

    @Override
    public void Total(Pedido p) {
        p.setTotal(0.0);
        double total = 0.0;
        for (Item item : p.getItems()) {

            if (item.getProducto() != null) {
                if (item.getProducto().getTipo_impuesto() == TipoImpuestoEnum.GRAVADO) {
                    total += ((item.getProducto().getPrecio()
                            + (item.getProducto().getPrecio() * p.getIVA())
                            + (item.getProducto().getPrecio() * p.getIIBB())) * item.getUnidades());
                    p.setTotal(total);
                } else {
                    total += item.getProducto().getPrecio() * item.getUnidades();
                    p.setTotal(total);
                }

                if (item.getGarantia() > 0) {
                    total += (item.getProducto().getPrecio() * 0.02) * item.getGarantia();
                    p.setTotal(total);
                }
            }

            if (item.getServicio() != null) {
                if (item.getServicio().getTipo_impuesto() == TipoImpuestoEnum.GRAVADO) {
                    total += ((item.getServicio().getPrecio()
                            + (item.getServicio().getPrecio() * p.getIVA())
                            + (item.getServicio().getPrecio() * p.getIIBB())
                            + item.getServicio().getMantenimiento()));
                    p.setTotal(total);
                } else {
                    total += ((item.getServicio().getPrecio() + item.getServicio().getMantenimiento())
                            * item.getUnidades());
                    p.setTotal(total);
                }
            }
        }
    }
}
