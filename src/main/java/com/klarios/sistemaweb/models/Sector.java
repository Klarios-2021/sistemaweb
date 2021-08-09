package com.klarios.sistemaweb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sector")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sector_id")
    Long id;
    @Column(name = "sector_nombre")
    String nombre;

    @OneToMany
    @JoinColumn(name = "sala_sector")
    List<Sala> salas = new ArrayList<>();

    public void agregarSala(Sala sala){
        this.salas.add(sala);
    }


}
