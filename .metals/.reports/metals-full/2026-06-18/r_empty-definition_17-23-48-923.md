error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/test/java/com/example/Kaizer_Back/CheckoutServiceTest.java:com/example/Kaizer_Back/CheckoutServiceTest#any#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/test/java/com/example/Kaizer_Back/CheckoutServiceTest.java
empty definition using pc, found symbol in pc: com/example/Kaizer_Back/CheckoutServiceTest#any#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 3656
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/test/java/com/example/Kaizer_Back/CheckoutServiceTest.java
text:
```scala
package com.example.Kaizer_Back;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.Kaizer_Back.auth.JwtService;
import com.example.Kaizer_Back.checkout.CheckoutService;
import com.example.Kaizer_Back.checkout.StockInsuficienteException;
import com.example.Kaizer_Back.checkout.dto.CheckoutRequest;
import com.example.Kaizer_Back.checkout.dto.CheckoutResponse;
import com.example.Kaizer_Back.producto.Pedido;
import com.example.Kaizer_Back.producto.PedidoRepository;
import com.example.Kaizer_Back.producto.Producto;
import com.example.Kaizer_Back.producto.ProductoRepository;
import com.example.Kaizer_Back.usuario.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    ProductoRepository productoRepository;
    @Mock
    PedidoRepository pedidoRepository;
    @Mock
    UsuarioRepository usuarioRepository;
    @Mock
    JwtService jwtService;

    @InjectMocks
    CheckoutService checkoutService;

    @BeforeEach
    void setUpSecurityContext() {
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(false);
        when(auth.getPrincipal()).thenReturn("anonymousUser");
        SecurityContext ctx = mock(SecurityContext.class);
        when(ctx.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(ctx);
    }

    private Producto productoConStock(Long id, int stock) {
        Producto p = new Producto();
        p.setId(id);
        p.setNombre("Producto Test");
        p.setPrecio(new BigDecimal("100.00"));
        p.setStock(stock);
        return p;
    }

    private CheckoutRequest buildRequest(Long productId, int qty) {
        CheckoutRequest.Item item = new CheckoutRequest.Item();
        item.setProductId(productId);
        item.setQuantity(qty);

        CheckoutRequest req = new CheckoutRequest();
        req.setItems(List.of(item));
        req.setEnvio(new BigDecimal("10.00"));
        req.setDistrict("Miraflores");
        req.setMetodoPago("card");
        return req;
    }

    @Test
    void checkout_exitoso_guardaPedidoYRetornaResponse() {
        Producto producto = productoConStock(1L, 10);
        when(productoRepository.findByIdForUpdate(1L)).thenReturn(Optional.of(producto));

        Pedido pedidoGuardado = new Pedido();
        pedidoGuardado.setId(42L);
        pedidoGuardado.setTotal(new BigDecimal("210.00"));
        pedidoGuardado.setSubtotal(new BigDecimal("169.49"));
        pedidoGuardado.setIgv(new BigDecimal("30.51"));
        pedidoGuardado.setEnvio(new BigDecimal("10.00"));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoGuardado);
        when(pedidoRepository.save(a@@ny(Pedido.class))).thenReturn(pedidoGuardado);

        CheckoutResponse res = checkoutService.checkout(buildRequest(1L, 2));

        assertNotNull(res);
        assertEquals(42L, res.getOrderId());
        assertEquals(0, new BigDecimal("210.00").compareTo(res.getTotal()));
        verify(productoRepository).findByIdForUpdate(1L);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void checkout_stockInsuficiente_lanzaExcepcionHTTP400() {
        Producto producto = productoConStock(1L, 1);
        when(productoRepository.findByIdForUpdate(1L)).thenReturn(Optional.of(producto));

        StockInsuficienteException ex = assertThrows(
                StockInsuficienteException.class,
                () -> checkoutService.checkout(buildRequest(1L, 5))
        );

        assertEquals(1L, ex.getProductoId());
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    void checkout_productoNoExiste_lanzaNotFound() {
        when(productoRepository.findByIdForUpdate(99L)).thenReturn(Optional.empty());

        assertThrows(
                org.springframework.web.server.ResponseStatusException.class,
                () -> checkoutService.checkout(buildRequest(99L, 1))
        );

        verify(pedidoRepository, never()).save(any());
    }

    @Test
    void checkout_decrementaStockCorrectamente() {
        Producto producto = productoConStock(1L, 10);
        when(productoRepository.findByIdForUpdate(1L)).thenReturn(Optional.of(producto));
        when(pedidoRepository.save(any())).thenAnswer(inv -> {
            Pedido p = inv.getArgument(0);
            p.setId(1L);
            return p;
        });

        checkoutService.checkout(buildRequest(1L, 3));

        assertEquals(7, producto.getStock());
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: com/example/Kaizer_Back/CheckoutServiceTest#any#