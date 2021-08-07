package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.enums.TipoDivision;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    Long id;
    @Column(name = "division_nombre")
    String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "division_tipo")
    TipoDivision tipo;

    @OneToMany
    @JoinColumn(name = "division_padre")
    List<Division> subdivisiones = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "sala_division")
    List<Sala> salas = new ArrayList<>();


}
