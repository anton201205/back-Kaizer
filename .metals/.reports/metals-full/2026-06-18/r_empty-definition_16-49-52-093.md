error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/producto/PedidoItem.java:_empty_/FetchType#LAZY#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/producto/PedidoItem.java
empty definition using pc, found symbol in pc: _empty_/FetchType#LAZY#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 373
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/producto/PedidoItem.java
text:
```scala
package com.example.Kaizer_Back.producto;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedido_items")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY@@)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    // Como Supabase (PostgreSQL) calcula este valor automáticamente (GENERATED ALWAYS AS),
    // le decimos a Hibernate que no intente insertarlo ni actualizarlo.
    @Column(nullable = false, precision = 12, scale = 2, insertable = false, updatable = false)
    private BigDecimal subtotal;
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/FetchType#LAZY#