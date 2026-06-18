error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/checkout/CheckoutService.java:_empty_/Producto#getPrecio#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/checkout/CheckoutService.java
empty definition using pc, found symbol in pc: _empty_/Producto#getPrecio#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2101
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/checkout/CheckoutService.java
text:
```scala
package com.example.Kaizer_Back.checkout;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.Kaizer_Back.checkout.dto.CheckoutRequest;
import com.example.Kaizer_Back.checkout.dto.CheckoutResponse;
import com.example.Kaizer_Back.producto.Pedido;
import com.example.Kaizer_Back.producto.PedidoItem;
import com.example.Kaizer_Back.producto.PedidoRepository;
import com.example.Kaizer_Back.producto.Producto;
import com.example.Kaizer_Back.producto.ProductoRepository;

@Service
public class CheckoutService {

	private final ProductoRepository productoRepository;
	private final PedidoRepository pedidoRepository;

	public CheckoutService(ProductoRepository productoRepository, PedidoRepository pedidoRepository) {
		this.productoRepository = productoRepository;
		this.pedidoRepository = pedidoRepository;
	}

	@Transactional
public CheckoutResponse checkout(CheckoutRequest request) {
    Pedido pedido = Pedido.builder().estado("CREADO").build();
    BigDecimal totalProductos = BigDecimal.ZERO;

    for (CheckoutRequest.Item item : request.getItems()) {
        Producto producto = productoRepository.findByIdForUpdate(item.getProductId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no existe: " + item.getProductId()));

        int actual = producto.getStock() == null ? 0 : producto.getStock();
        if (actual < item.getQuantity()) {
            throw new StockInsuficienteException(item.getProductId(), actual, item.getQuantity());
        }

        producto.setStock(actual - item.getQuantity());

        PedidoItem pedidoItem = PedidoItem.builder()
            .pedido(pedido)
            .producto(producto)
            .cantidad(item.getQuantity())
            .precioUnitario(producto.getPrecio())
            .build();

        pedido.getItems().add(pedidoItem);
        totalProductos = totalProductos.add(producto.@@getPrecio().multiply(BigDecimal.valueOf(item.getQuantity())));
    }

    // Cálculos fiscales (IGV incluido en precio)
    BigDecimal IGV_RATE = new BigDecimal("0.18");
    BigDecimal baseImponible = totalProductos.divide(BigDecimal.ONE.add(IGV_RATE), 2, java.math.RoundingMode.HALF_UP);
    BigDecimal igv = totalProductos.subtract(baseImponible);
    BigDecimal envio = request.getEnvio() != null ? request.getEnvio() : BigDecimal.ZERO;
    BigDecimal totalFinal = totalProductos.add(envio);

    pedido.setTotal(totalFinal);
    Pedido saved = pedidoRepository.save(pedido);

    return new CheckoutResponse(saved.getId(), baseImponible, igv, envio, totalFinal);
}
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Producto#getPrecio#