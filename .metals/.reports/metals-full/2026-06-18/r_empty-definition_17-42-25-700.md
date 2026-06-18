error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/UsuarioService.java:_empty_/UsuarioRepository#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/UsuarioService.java
empty definition using pc, found symbol in pc: _empty_/UsuarioRepository#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 484
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/UsuarioService.java
text:
```scala
package com.example.Kaizer_Back.usuario;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.Kaizer_Back.usuario.dto.PerfilRequest;

@Service
public class UsuarioService {

    private final Us@@uarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
	

    public Usuario registrar(String email, String password) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado");
        }

        Usuario usuario = Usuario.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(password))
                .role(Role.USER)
                .createdAt(OffsetDateTime.now())
                .build();

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizarPerfil(String email, PerfilRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (request.getNombre() != null) usuario.setNombre(request.getNombre());
        if (request.getTelefono() != null) usuario.setTelefono(request.getTelefono());
        if (request.getDireccion() != null) usuario.setDireccion(request.getDireccion());
        if (request.getDistrito() != null) usuario.setDistrito(request.getDistrito());
        if (request.getDni() != null) usuario.setDni(request.getDni());

        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerPerfil(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/UsuarioRepository#