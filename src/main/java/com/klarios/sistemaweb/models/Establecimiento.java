package com.klarios.sistemaweb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "establecimiento")
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
    @JoinColumn(name = "sector_establecimiento")
    List<Sector> sectores = new ArrayList<>();

    public void agregarSector(Sector sector){
        this.sectores.add(sector);
    }
}
