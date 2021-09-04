package com.klarios.sistemaweb.models.normas;

import com.klarios.sistemaweb.models.enums.ClaseANMAT;
import com.klarios.sistemaweb.models.enums.EstadoOcupacion;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("ANMAT3827")
public class ANMAT3827 extends Norma {
    @Enumerated(EnumType.STRING)
    @Column(name = "norma_clase_ANMAT")
    ClaseANMAT claseANMAT;

    @Override
    public BigDecimal limite05(EstadoOcupacion estadoOcupacion) {
        return switch (claseANMAT){
            case A -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(3520);
                        case OPERACION -> BigDecimal.valueOf(3520);
                    };
            case B -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(3520);
                        case OPERACION -> BigDecimal.valueOf(352000);
                    };
            case C -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(352000);
                        case OPERACION -> BigDecimal.valueOf(3520000);
                    };
            case D -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(3520000);
                        case OPERACION -> null;
                    };
        };
    }

    @Override
    public BigDecimal limite5(EstadoOcupacion estadoOcupacion) {
        return switch (claseANMAT){
            case A -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(20);
                        case OPERACION -> BigDecimal.valueOf(20);
                    };
            case B -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(29);
                        case OPERACION -> BigDecimal.valueOf(2900);
                    };
            case C -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(2900);
                        case OPERACION -> BigDecimal.valueOf(29000);
                    };
            case D -> switch (estadoOcupacion)
                    {
                        case REPOSO -> BigDecimal.valueOf(29000);
                        case OPERACION -> null;
                    };
        };
    }
}
