package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariableAmbiental;
import com.klarios.sistemaweb.models.enums.VariableAmbiental;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "equipo")
@PrimaryKeyJoinColumn(name = "equipo_id", referencedColumnName = "material_id")
public class Equipo extends Material {

    @Column(name = "equipo_marca")
    String marca;
    @Column(name = "equipo_modelo")
    String modelo;

    @Transient
    //Solo se usa para el front
    List<Ensayo> ensayosHabilitados = List.of(
            new EnsayoVariableAmbiental(VariableAmbiental.ILUMINACION),
            new EnsayoVariableAmbiental(VariableAmbiental.RUIDO),
            new EnsayoVariableAmbiental(VariableAmbiental.TEMPERATURA),
            new EnsayoVariableAmbiental(VariableAmbiental.HUMEDAD));

    public List<Ensayo> ensayosHabilitados (){
        return this.ensayosHabilitados;
    }

}
