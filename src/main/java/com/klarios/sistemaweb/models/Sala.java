package com.klarios.sistemaweb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sala")
@PrimaryKeyJoinColumn(name = "sala_id", referencedColumnName = "material_id")
public class Sala extends Material {

    @Column(name = "sala_uma")
    String uma;

    @OneToMany
    @JoinColumn(name = "equipo_sala")
    List<Equipo> equipos = new ArrayList<>();

    public void agregarEquipo(Equipo equipo){this.equipos.add(equipo);}
}
