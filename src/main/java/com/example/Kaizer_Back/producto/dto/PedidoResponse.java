package com.example.Kaizer_Back.producto.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.example.Kaizer_Back.producto.Pedido;

public class PedidoResponse {

    private Long id;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal envio;
    private BigDecimal total;
    private String distrito;
    private String metodoPago;
    private OffsetDateTime createdAt;
    private List<ItemResponse> items;

    public static PedidoResponse from(Pedido p) {
        PedidoResponse r = new PedidoResponse();
        r.id = p.getId();
        r.estado = p.getEstado();
        r.subtotal = p.getSubtotal();
        r.igv = p.getIgv();
        r.envio = p.getEnvio();
        r.total = p.getTotal();
        r.distrito = p.getDistrito();
        r.metodoPago = p.getMetodoPago();
        r.createdAt = p.getCreatedAt();
        r.items = p.getItems().stream().map(item -> {
            ItemResponse ir = new ItemResponse();
            ir.productoNombre = item.getProducto().getNombre();
            ir.cantidad = item.getCantidad();
            ir.precioUnitario = item.getPrecioUnitario();
            ir.subtotal = item.getPrecioUnitario()
                    .multiply(java.math.BigDecimal.valueOf(item.getCantidad()));
            return ir;
        }).toList();
        return r;
    }

    public static class ItemResponse {
        private String productoNombre;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;

        public String getProductoNombre() { return productoNombre; }
        public Integer getCantidad() { return cantidad; }
        public BigDecimal getPrecioUnitario() { return precioUnitario; }
        public BigDecimal getSubtotal() { return subtotal; }
    }

    public Long getId() { return id; }
    public String getEstado() { return estado; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getIgv() { return igv; }
    public BigDecimal getEnvio() { return envio; }
    public BigDecimal getTotal() { return total; }
    public String getDistrito() { return distrito; }
    public String getMetodoPago() { return metodoPago; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public List<ItemResponse> getItems() { return items; }
}