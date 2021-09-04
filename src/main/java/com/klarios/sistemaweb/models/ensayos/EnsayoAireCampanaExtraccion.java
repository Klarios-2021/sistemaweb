package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionAire;
import com.klarios.sistemaweb.models.enums.AperturaVentana;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "ensayo_campana_extraccion")
public class EnsayoAireCampanaExtraccion extends Ensayo{


    //EVALUAR SI HACER 3 LISTAS DE MEDICIONES (UNA POR CADA APERTURA) O
    //SEPARAR LOGICAMENTE LA MISMA LISTA GENERAL (0 - 2 PARA APERTURA OPTIMA, 3 - 5 PARA APERTURA MÁXIMA, ETC)
    public String descripcion = "Ensayo de aire para campana de extracción";

    @ElementCollection
    @CollectionTable(name = "registro_medicion_aire",
            joinColumns=@JoinColumn(name = "ensayo_id"))
    List<RegistroMedicionAire> mediciones = new ArrayList<>();

    @Override
    public void calcularValores() {

    }
    @Override
    public boolean esValido() {
        //Necesitamos los valores fijos de comparación (supuestamente son siempre los mismos para todos los equipos)
        return true;
    }

    public BigDecimal promedioSegunApertura(AperturaVentana aperturaVentana){
        return sumaMedicionesSegunApertura(aperturaVentana).divide(BigDecimal.valueOf(3));
    }

    public BigDecimal sumaMedicionesSegunApertura(AperturaVentana aperturaVentana){
        return this.mediciones.stream().filter(m -> m.apertura == aperturaVentana).
                map(m -> m.medicion.valor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int cantidadMedicionesNecesarias() {
        return 9;
    }

    @Override
    public void configurarValoresComparacion(ValoresComparacion valoresComparacion) {

    }
}
