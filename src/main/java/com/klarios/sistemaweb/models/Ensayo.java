package com.klarios.sistemaweb.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaRealizacion;
    @Column(name = "ensayo_controlo")
    String controlo;
    @Column(name = "ensayo_fecha_control")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaControl;
    @Column(name = "ensayo_protocolo")
    String protocolo;

    @OneToMany
    @JoinColumn(name = "version_datos_ensayo")
    List<VersionDatos> versionesDatos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ensayo_material")
    Material material;

    public void agregarVersionDatos(VersionDatos versionDatos){this.versionesDatos.add(versionDatos);}



}
