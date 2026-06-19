package com.example.Kaizer_Back.usuario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Kaizer_Back.auth.JwtService;
import com.example.Kaizer_Back.producto.dto.PedidoResponse;
import com.example.Kaizer_Back.usuario.dto.PerfilRequest;
import com.example.Kaizer_Back.usuario.dto.PerfilResponse;
import com.example.Kaizer_Back.usuario.dto.PerfilUpdateResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    public UsuarioController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @GetMapping("/perfil")
    public ResponseEntity<PerfilResponse> getPerfil(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.obtenerPerfil(userDetails.getUsername());
        return ResponseEntity.ok(PerfilResponse.from(usuario));
    }

    @PutMapping("/perfil")
    public ResponseEntity<PerfilUpdateResponse> updatePerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody PerfilRequest request) {
        Usuario usuario = usuarioService.actualizarPerfil(userDetails.getUsername(), request);

        PerfilResponse perfilResponse = PerfilResponse.from(usuario);
        String token = null;

        if (request.getEmail() != null && !request.getEmail().isBlank()
                && !request.getEmail().equalsIgnoreCase(userDetails.getUsername())) {
            UserDetails newUserDetails = new User(
                    usuario.getEmail(),
                    userDetails.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()))
            );
            token = jwtService.generateToken(newUserDetails);
        }

        return ResponseEntity.ok(new PerfilUpdateResponse(perfilResponse, token));
    }

    @GetMapping("/ordenes")
    public ResponseEntity<List<PedidoResponse>> getMisOrdenes(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<PedidoResponse> ordenes = usuarioService.getOrdenesByEmail(userDetails.getUsername());
        return ResponseEntity.ok(ordenes);
    }
}