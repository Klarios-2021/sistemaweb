package com.klarios.sistemaweb.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Sala")
@PrimaryKeyJoinColumn(name = "sala_id")
public class Sala extends ObjetoEstudio {

    @Column(name = "sala_uma")
    String uma;

    @OneToMany
    @JoinColumn(name = "equipo_sala")
    List<Equipo> equipos = new ArrayList<>();

}