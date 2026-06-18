package com.example.Kaizer_Back.checkout.dto;
import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CheckoutRequest {

    @NotEmpty
    @Valid
    private List<Item> items;

    private BigDecimal envio;

    @NotBlank
    @Size(max = 100)
    private String district;

    @NotBlank
    @Pattern(regexp = "^(card|qr)$", message = "Método de pago inválido")
    private String metodoPago;

    public BigDecimal getEnvio() { return envio; }
    public void setEnvio(BigDecimal envio) { this.envio = envio; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public static class Item {
        @NotNull
        private Long productId;

        @NotNull
        @Positive
        private Integer quantity;

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }
}