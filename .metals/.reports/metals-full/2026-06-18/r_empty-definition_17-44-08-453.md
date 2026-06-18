error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/UsuarioController.java:_empty_/RequestMapping#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/UsuarioController.java
empty definition using pc, found symbol in pc: _empty_/RequestMapping#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 738
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/usuario/UsuarioController.java
text:
```scala
package com.example.Kaizer_Back.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Kaizer_Back.usuario.dto.PerfilRequest;
import com.example.Kaizer_Back.usuario.dto.PerfilResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping@@("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/perfil")
    public ResponseEntity<PerfilResponse> getPerfil(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.obtenerPerfil(userDetails.getUsername());
        return ResponseEntity.ok(PerfilResponse.from(usuario));
    }

    @PutMapping("/perfil")
    public ResponseEntity<PerfilResponse> updatePerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody PerfilRequest request) {
        Usuario usuario = usuarioService.actualizarPerfil(userDetails.getUsername(), request);
        return ResponseEntity.ok(PerfilResponse.from(usuario));
    }

    
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/RequestMapping#