package com.example.Kaizer_Back.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PerfilRequest {

    @Email
    @Size(max = 190)
    private String email;

    @Size(max = 100)
    private String nombre;

    @Pattern(regexp = "^\\d{9}$", message = "Teléfono debe tener 9 dígitos")
    private String telefono;

    @Size(max = 200)
    private String direccion;

    @Size(max = 100)
    private String distrito;

    @Pattern(regexp = "^\\d{8}$", message = "DNI debe tener 8 dígitos")
    private String dni;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}