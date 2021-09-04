package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.ensayos.mediciones.Medicion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionRenovaciones;
import com.klarios.sistemaweb.models.enums.SentidoCaudal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ensayo_renovaciones")
public class EnsayoRenovaciones extends Ensayo{

    @Enumerated(EnumType.STRING)
    @Column(name = "ensayo_sentido_caudal")
    SentidoCaudal sentidoCaudal;

    @OneToOne
    @JoinColumn(name = "ensayo_renovaciones_por_hora")
    Medicion renovacionesPorHora;

    @ElementCollection
    @CollectionTable(name = "medicion_ensayo_renovaciones", joinColumns = @JoinColumn(name = "ensayo_id"))
    List<RegistroMedicionRenovaciones> mediciones = new ArrayList<>();

    @Column(name = "ensayo_limite_inferior")
    BigDecimal limiteInferior;

    @Override
    public void calcularValores(){
        this.renovacionesPorHora.valor =
                this.sumaMediciones().divide(BigDecimal.valueOf(this.material.getDimension().obtenerVolumen()),2, RoundingMode.HALF_UP);

    }
    @Override
    public boolean esValido(){
        return this.renovacionesPorHora.valor.compareTo(this.limiteInferior) >= 0;
    }
    /*
    @Override
    public boolean esValido(){
        return switch (sentidoCaudal){
            case EGRESO -> this.renovacionesPorHora.valor.compareTo(this.trabajoAsociado.getValoresComparacion().getLimiteInferiorCaudalEntrante()) >= 0;
            case INGRESO -> this.renovacionesPorHora.valor.compareTo(this.trabajoAsociado.getValoresComparacion().getLimiteInferiorCaudalSaliente()) >= 0;
        };
    }*/

    public BigDecimal sumaMediciones(){
        return mediciones.stream().map(medicion -> medicion.medicion.valor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int cantidadMedicionesNecesarias() {
        return 0;
    }

    @Override
    public void configurarValoresComparacion(ValoresComparacion valoresComparacion) {

    }
}
