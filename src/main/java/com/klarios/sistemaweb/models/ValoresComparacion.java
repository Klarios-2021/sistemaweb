package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.ensayos.mediciones.Medicion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class ValoresComparacion {
    BigDecimal limiteSuperiorTemperatura, limiteInferiorTemperatura,
             limiteSuperiorRuido, limiteInferiorRuido,
             limiteSuperiorIluminacion, limiteInferiorIluminacion,
             limiteSuperiorHumedad, limiteInferiorHumedad,
             limiteSuperiorConteoReposo, limiteSuperiorConteoOperacion,
             limiteInferiorCaudalEntrante, limiteInferiorCaudalSaliente;
}
