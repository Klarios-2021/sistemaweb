package com.klarios.sistemaweb.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Equipo")
@PrimaryKeyJoinColumn(name = "equipo_id")
public class Equipo extends ObjetoEstudio{

    @Column(name = "equipo_marca")
    String marca;
    @Column(name = "equipo_modelo")
    String modelo;

}
