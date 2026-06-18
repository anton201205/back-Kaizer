package com.example.Kaizer_Back.usuario.dto;

import com.example.Kaizer_Back.usuario.Usuario;

public class PerfilResponse {
    private Long id;
    private String email;
    private String nombre;
    private String telefono;
    private String direccion;
    private String distrito;
    private String dni;
    private String role;

    public static PerfilResponse from(Usuario u) {
        PerfilResponse r = new PerfilResponse();
        r.id = u.getId();
        r.email = u.getEmail();
        r.nombre = u.getNombre();
        r.telefono = u.getTelefono();
        r.direccion = u.getDireccion();
        r.distrito = u.getDistrito();
        r.dni = u.getDni();
        r.role = u.getRole().name();
        return r;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getDistrito() { return distrito; }
    public String getDni() { return dni; }
    public String getRole() { return role; }
}