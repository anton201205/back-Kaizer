error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/producto/Pedido.java:jakarta/persistence/GenerationType#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/producto/Pedido.java
empty definition using pc, found symbol in pc: jakarta/persistence/GenerationType#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 438
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/producto/Pedido.java
text:
```scala
package com.example.Kaizer_Back.producto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Kaizer_Back.usuario.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType@@;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "direccion_envio", columnDefinition = "TEXT")
    private String direccionEnvio;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 12, scale = 2)
    private BigDecimal igv;

    @Column(precision = 12, scale = 2)
    private BigDecimal envio;

    @Column(name = "costo_envio", precision = 12, scale = 2)
    private BigDecimal costoEnvio;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(length = 100)
    private String distrito;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PedidoItem> items = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        if (this.estado == null) this.estado = "CREADO";
        if (this.total == null) this.total = BigDecimal.ZERO;
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: jakarta/persistence/GenerationType#