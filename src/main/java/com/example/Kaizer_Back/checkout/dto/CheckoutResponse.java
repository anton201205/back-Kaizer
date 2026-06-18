package com.example.Kaizer_Back.checkout.dto;

import java.math.BigDecimal;

public class CheckoutResponse {
    private Long orderId;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal envio;
    private BigDecimal total;

    public CheckoutResponse(Long orderId, BigDecimal subtotal, BigDecimal igv, BigDecimal envio, BigDecimal total) {
        this.orderId = orderId;
        this.subtotal = subtotal;
        this.igv = igv;
        this.envio = envio;
        this.total = total;
    }

    public Long getOrderId() { return orderId; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getIgv() { return igv; }
    public BigDecimal getEnvio() { return envio; }
    public BigDecimal getTotal() { return total; }
}

