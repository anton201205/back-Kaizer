package com.example.Kaizer_Back.checkout;

public class StockInsuficienteException extends RuntimeException {
    private final Long productoId;

    public StockInsuficienteException(Long productoId, int actual, int solicitado) {
        super(String.format("Stock insuficiente para producto %d: disponible=%d, solicitado=%d",
                productoId, actual, solicitado));
        this.productoId = productoId;
    }

    public Long getProductoId() { return productoId; }
}
