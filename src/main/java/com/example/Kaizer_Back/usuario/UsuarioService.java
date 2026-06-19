package com.example.Kaizer_Back.usuario;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.Kaizer_Back.producto.PedidoRepository;
import com.example.Kaizer_Back.producto.dto.PedidoResponse;
import com.example.Kaizer_Back.usuario.dto.PerfilRequest;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PedidoRepository pedidoRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            PedidoRepository pedidoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.pedidoRepository = pedidoRepository;
    }

    public Usuario registrar(String email, String password, String nombre, String telefono, String distrito, String dni) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado");
        }

        Usuario usuario = Usuario.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(password))
                .nombre(nombre)
                .telefono(telefono)
                .distrito(distrito)
                .dni(dni)
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

    public List<PedidoResponse> getOrdenesByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return pedidoRepository.findByUsuarioOrderByCreatedAtDesc(usuario)
                .stream()
                .map(PedidoResponse::from)
                .toList();
    }
}