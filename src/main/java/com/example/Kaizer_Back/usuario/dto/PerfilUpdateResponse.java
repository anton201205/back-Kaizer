package com.example.Kaizer_Back.usuario.dto;

public class PerfilUpdateResponse {
    private PerfilResponse perfil;
    private String token;

    public PerfilUpdateResponse() {
    }

    public PerfilUpdateResponse(PerfilResponse perfil, String token) {
        this.perfil = perfil;
        this.token = token;
    }

    public PerfilResponse getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilResponse perfil) {
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
