package com.klarios.sistemaweb.security;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @NotNull
    @Column(name = "usuario_username")
    String username;

    @NotNull
    @Column(name = "usuario_password")
    String password;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "Roles",
            joinColumns=@JoinColumn(name = "rol_usuario"))
    @Column(name = "rol_descripcion")
    @Enumerated(EnumType.STRING)
    List<Rol> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
