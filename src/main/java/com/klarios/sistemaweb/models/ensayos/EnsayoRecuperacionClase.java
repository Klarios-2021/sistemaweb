package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.forms.FormRealizacionEnsayo;
import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.ensayos.mediciones.Medicion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionRecuperacion;
import com.klarios.sistemaweb.models.enums.Unidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "ensayo_recuperacion_clase")
public class EnsayoRecuperacionClase extends Ensayo{

    public String descripcion = "Ensayo de recuperación de clase";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ensayo_concentracion_inicial" )
    Medicion concentracionInicial;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ensayo_concentracion_maxima" )
    Medicion concentracionMaxima;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ensayo_tiempo_recuperacion_clase" )
    Medicion tiempoRecuperacionClase;

    @ElementCollection
    @CollectionTable(name = "medicion_ensayo_recuperacion_clase", joinColumns = @JoinColumn(name = "ensayo_id"))
    List<RegistroMedicionRecuperacion> registros = new ArrayList<>();

    @Override
    public void calcularValores(){

    }
    @Override
    public boolean esValido(){
        return this.registros.get(this.registros.size()-1).medicion.valor.compareTo(concentracionInicial.valor) <= 0;
    }

    @Override
    public int cantidadMedicionesNecesarias() {
        return 0;
    }

    @Override
    public void configurarValoresComparacion(ValoresComparacion valoresComparacion) {
    }

    @Override
    public void setearValores(FormRealizacionEnsayo formRealizacionEnsayo){
        super.setearValores(formRealizacionEnsayo);
        this.registros = formRealizacionEnsayo.getMedicionesRecuperacion().stream().filter(r -> r.medicion.valor != null).collect(Collectors.toList());
        this.concentracionInicial = formRealizacionEnsayo.getConcentracionInicial();
        this.concentracionMaxima = formRealizacionEnsayo.getConcentracionMaxima();
    }

    @Override
    public Unidad unidadMediciones(){
        return Unidad.PARTICULAS_M3;
    }

}
