package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.enums.TipoFiltro;

import javax.persistence.*;

@Entity
@Table(name = "filtro")
public class Filtro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filtro_id")
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "filtro_tipo")
    TipoFiltro tipoFiltro;
}
