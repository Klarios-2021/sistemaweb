package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.ensayos.EnsayoRecuperacionClase;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariableAmbiental;
import com.klarios.sistemaweb.models.enums.VariableAmbiental;
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

    @Transient
    //Solo se usa para el front
    List<Ensayo> ensayosHabilitados = List.of(
            new EnsayoVariableAmbiental(VariableAmbiental.ILUMINACION),
            new EnsayoVariableAmbiental(VariableAmbiental.RUIDO),
            new EnsayoVariableAmbiental(VariableAmbiental.TEMPERATURA),
            new EnsayoVariableAmbiental(VariableAmbiental.HUMEDAD),
            new EnsayoRecuperacionClase());

    public List<Ensayo> ensayosHabilitados (){
        return this.ensayosHabilitados;
    }

}
