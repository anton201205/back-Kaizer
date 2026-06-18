error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/Usuario.java:_empty_/Column#nullable#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/Usuario.java
empty definition using pc, found symbol in pc: _empty_/Column#nullable#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 942
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/Usuario.java
text:
```scala
package com.example.Kaizer_Back.usuario;

import java.time.OffsetDateTime;

import jakarta.persistence.Column; // Simplificamos los imports
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "usuarios")
public class Usuario {

public Long getId() { return id; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 190)
    private String email;

    @Column(name = "password_hash", @@nullable = false, length = 100)
    private String passwordHash;

    @Column(length = 100)
    private String nombre;

    @Column(length = 20)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    // Esto asigna la fecha automáticamente antes de insertar en la DB
    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        if (this.role == null) {
            this.role = Role.USER; // Valor por defecto si viene nulo
        }
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Column#nullable#