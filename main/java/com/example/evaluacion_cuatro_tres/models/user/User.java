package com.example.evaluacion_cuatro_tres.models.user;

public class User {

    private Long id_user;
    private String correo, contraseña;
    private Role role;

    public User() {
    }

    public User(Long id_user, String correo, String contraseña, Role role) {
        this.id_user = id_user;
        this.correo = correo;
        this.contraseña = contraseña;
        this.role = role;
    }


    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", role=" + role +
                '}';
    }
}