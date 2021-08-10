package com.klarios.sistemaweb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "equipo")
@PrimaryKeyJoinColumn(name = "equipo_id", referencedColumnName = "material_id")
public class Equipo extends Material {

    @Column(name = "equipo_marca")
    String marca;
    @Column(name = "equipo_modelo")
    String modelo;

}
