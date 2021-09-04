package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.forms.FormRealizacionEnsayo;
import com.klarios.sistemaweb.models.Material;
import com.klarios.sistemaweb.models.Trabajo;
import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.ensayos.mediciones.Medicion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionAmbiental;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import com.klarios.sistemaweb.models.enums.Unidad;
import com.klarios.sistemaweb.models.enums.VariableAmbiental;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ensayo_variables_ambientales")
public class EnsayoVariableAmbiental extends Ensayo {

    String observaciones = "Ensayo de variables ambientales realizado en base a normas X";

    @Enumerated(EnumType.STRING)
    @Column(name = "ensayo_variable_ambiental")
    VariableAmbiental variableAmbiental;

    @ElementCollection
    @CollectionTable(name = "medicion_ensayo_variables_ambientales", joinColumns = @JoinColumn(name = "ensayo_id"))
    List<RegistroMedicionAmbiental> registros = new ArrayList<>();

    @Column(name = "ensayo_limite_superior")
    BigDecimal limiteSuperior;
    @Column(name = "ensayo_limite_inferior")
    BigDecimal limiteInferior;

    public EnsayoVariableAmbiental(){ }

    public EnsayoVariableAmbiental(VariableAmbiental variableAmbiental){
        this.variableAmbiental = variableAmbiental;
        this.descripcion = "Ensayo de variable ambiental " + variableAmbiental.toString();
    }
    @Override
    public int cantidadMedicionesNecesarias(){
        Double area = getMaterial().getDimension().obtenerArea();
        return area < 4.0 ? 2 :
               area < 14.0 ? 2 + (int)Math.floor((area - 4.0) / 2) : 7;
    }

    @Override
    public void configurarValoresComparacion(ValoresComparacion valoresComparacion) {
        this.limiteInferior = switch(variableAmbiental){
            case TEMPERATURA -> valoresComparacion.getLimiteInferiorTemperatura();
            case ILUMINACION -> valoresComparacion.getLimiteInferiorIluminacion();
            case HUMEDAD -> valoresComparacion.getLimiteInferiorHumedad();
            case RUIDO -> valoresComparacion.getLimiteInferiorRuido();
        };
        this.limiteSuperior = switch(variableAmbiental){
            case TEMPERATURA -> valoresComparacion.getLimiteSuperiorTemperatura();
            case ILUMINACION -> valoresComparacion.getLimiteSuperiorIluminacion();
            case HUMEDAD -> valoresComparacion.getLimiteSuperiorHumedad();
            case RUIDO -> valoresComparacion.getLimiteSuperiorRuido();
        };
    }
    @Override
    public void setearValores(FormRealizacionEnsayo formRealizacionEnsayo){
        super.setearValores(formRealizacionEnsayo);
        this.registros = formRealizacionEnsayo.getMedicionesAmbientales();
    }

    @Override
    public boolean esValido(){

        return this.promedioMediciones().compareTo(this.limiteInferior != null ? this.limiteInferior : BigDecimal.ZERO) >= 0
                && this.promedioMediciones().compareTo(this.limiteSuperior != null ? this.limiteSuperior : BigDecimal.ZERO) <= 0;

    }
    @Override
    public Unidad unidadMediciones(){
        return switch (this.variableAmbiental){
            case RUIDO -> Unidad.DECIBELES;
            case TEMPERATURA -> Unidad.GRADOS;
            case HUMEDAD -> Unidad.UNIDAD_HUMEDAD;
            case ILUMINACION -> Unidad.UNIDAD_ILUMINACION;
        };
    }
    public BigDecimal promedioMediciones (){
        BigDecimal p = registros.size() != 0 ?
                this.sumaMediciones().divide(BigDecimal.valueOf(registros.size()),2, RoundingMode.HALF_UP) :
                BigDecimal.ZERO;
        return p;
    }

    public BigDecimal sumaMediciones(){
        BigDecimal m = registros.stream().map(registro -> registro.medicion.valor).reduce(BigDecimal.ZERO, BigDecimal::add);
        return m;
    }

}
