package com.klarios.sistemaweb.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Laboratorio")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laboratorio_id")
    Long id;
    @Column(name = "laboratorio_nombre")
    String nombre;
    @Column(name = "laboratorio_razon_social")
    String razonSocial;
    @Column(name = "laboratorio_cuit")
    String cuit;
    @Column(name = "laboratorio_website")
    String website;
    @Column(name = "laboratorio_telefono")
    String telefono;
    @Column(name = "laboratorio_email")
    String email;

    @OneToMany
    @JoinColumn(name = "contacto_laboratorio")
    List<Contacto> contactos = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "establecimiento_laboratorio")
    List<Establecimiento> establecimientos = new ArrayList<>();


}
