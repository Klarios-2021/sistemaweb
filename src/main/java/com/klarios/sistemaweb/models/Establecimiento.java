package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.enums.TipoDivision;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Establecimiento")
public class Establecimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "establecimiento_id")
    Long id;
    @Column(name = "establecimiento_nombre")
    String nombre;
    @Column(name = "establecimiento_direccion")
    String direccion;

    @OneToMany
    @JoinColumn(name = "division_establecimiento")
    List<Division> divisiones = new ArrayList<>();

}
