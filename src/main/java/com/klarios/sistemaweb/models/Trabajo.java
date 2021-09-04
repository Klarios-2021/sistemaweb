package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "trabajo")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trabajo_id")
    Long id;

    @OneToMany
    @JoinColumn(name = "ensayo_trabajo")
    List<Ensayo> ensayos = new ArrayList<>();

    @Column(name = "trabajo_fecha")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fecha;

    @Column(name = "trabajo_observaciones")
    String observaciones;

    //@Embedded
    //ValoresComparacion valoresComparacion;

    public void agregarEnsayo(Ensayo ensayo){
        this.ensayos.add(ensayo);
    }

    public double porcentajeCompletitud(){
        return (double) this.ensayos.stream().filter(ensayo -> ensayo.getEstado() != EstadoEnsayo.PENDIENTE).count() / (double) this.ensayos.size() * 100;
    }
    public String estadoTrabajo(){
        double porcentajeCompletitud = porcentajeCompletitud();
        return porcentajeCompletitud == 0 ? "Pendiente" : porcentajeCompletitud < 100 ? "En proceso" : "Finalizado";
    }

}
