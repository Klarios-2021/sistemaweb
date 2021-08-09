package com.klarios.sistemaweb.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ensayo")
public class Ensayo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ensayo_id")
    Long id;

    @Column(name = "ensayo_realizo")
    String realizo;
    @Column(name = "ensayo_fecha_realizacion")
    LocalDateTime fechaRealizacion;
    @Column(name = "ensayo_controlo")
    String controlo;
    @Column(name = "ensayo_fecha_control")
    LocalDateTime fechaControl;
    @Column(name = "ensayo_protocolo")
    String protocolo;

    @OneToMany
    @JoinColumn(name = "version_datos_ensayo")
    List<VersionDatos> versionesDatos = new ArrayList<>();




}
