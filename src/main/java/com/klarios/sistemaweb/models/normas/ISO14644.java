package com.klarios.sistemaweb.models.normas;

import com.klarios.sistemaweb.models.enums.ClaseISO;
import com.klarios.sistemaweb.models.enums.EstadoOcupacion;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("ISO14644")
public class ISO14644 extends Norma {

    @Enumerated(EnumType.STRING)
    @Column(name = "norma_clase_ISO")
    ClaseISO claseISO;

    @Override
    public BigDecimal limite05(EstadoOcupacion estadoOcupacion) {
        return null;
    }

    @Override
    public BigDecimal limite5(EstadoOcupacion estadoOcupacion) {
        return null;
    }
}
