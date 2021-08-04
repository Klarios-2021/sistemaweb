package com.klarios.sistemaweb.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacto_id")
    Long id;
    @Column(name = "contacto_nombre")
    String nombre;
    @Column(name = "contacto_apellido")
    String apellido;
    @Column(name = "contacto_email")
    String email;
    @Column(name = "contacto_telefono")
    String telefono;
    @Column(name = "contacto_rol")
    String rol;
}
