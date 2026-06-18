package com.example.Kaizer_Back.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.Kaizer_Back.auth.JwtService;
import com.example.Kaizer_Back.checkout.dto.CheckoutRequest;
import com.example.Kaizer_Back.checkout.dto.CheckoutResponse;
import com.example.Kaizer_Back.producto.Pedido;
import com.example.Kaizer_Back.producto.PedidoItem;
import com.example.Kaizer_Back.producto.PedidoRepository;
import com.example.Kaizer_Back.producto.Producto;
import com.example.Kaizer_Back.producto.ProductoRepository;
import com.example.Kaizer_Back.usuario.Usuario;
import com.example.Kaizer_Back.usuario.UsuarioRepository;

@Service
public class CheckoutService {

    private static final BigDecimal IGV_RATE = new BigDecimal("0.18");

    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public CheckoutService(
            ProductoRepository productoRepository,
            PedidoRepository pedidoRepository,
            UsuarioRepository usuarioRepository,
            JwtService jwtService) {
        this.productoRepository = productoRepository;
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @Transactional(rollbackFor = Exception.class)
public CheckoutResponse checkout(CheckoutRequest request) {
        Usuario usuario = null;
        var auth = SecurityContextHolder.getContext().getAuthentication();
if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
    usuario = usuarioRepository.findByEmail(auth.getName()).orElse(null);
}

        Pedido pedido = new Pedido();
        pedido.setEstado("CREADO");
        pedido.setUsuario(usuario);
        pedido.setDistrito(request.getDistrict());
        pedido.setMetodoPago(request.getMetodoPago());

        BigDecimal totalProductos = BigDecimal.ZERO;

        for (CheckoutRequest.Item item : request.getItems()) {
            Producto producto = productoRepository.findByIdForUpdate(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Producto no existe: " + item.getProductId()));

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
            totalProductos = totalProductos.add(
                    producto.getPrecio().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        // Cálculos fiscales (IGV incluido en precio)
        BigDecimal baseImponible = totalProductos.divide(
                BigDecimal.ONE.add(IGV_RATE), 2, RoundingMode.HALF_UP);
        BigDecimal igv = totalProductos.subtract(baseImponible).setScale(2, RoundingMode.HALF_UP);
        BigDecimal costoEnvio = request.getEnvio() != null ? request.getEnvio() : BigDecimal.ZERO;
        BigDecimal totalFinal = totalProductos.add(costoEnvio).setScale(2, RoundingMode.HALF_UP);

        pedido.setSubtotal(baseImponible);
        pedido.setIgv(igv);
        pedido.setEnvio(costoEnvio);
        pedido.setCostoEnvio(costoEnvio);
        pedido.setTotal(totalFinal);

        Pedido saved = pedidoRepository.save(pedido);

        return new CheckoutResponse(saved.getId(), baseImponible, igv, costoEnvio, totalFinal);
    }
}