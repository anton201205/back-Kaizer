package com.example.Kaizer_Back.producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Kaizer_Back.usuario.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioOrderByCreatedAtDesc(Usuario usuario);
}