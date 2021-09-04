package com.klarios.sistemaweb.models.normas;

import com.klarios.sistemaweb.models.enums.EstadoOcupacion;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "norma_tipo")
@Table(name = "norma")
public abstract class Norma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "norma_id")
    Long id;

    public abstract BigDecimal limite05 (EstadoOcupacion estadoOcupacion);
    public abstract BigDecimal limite5 (EstadoOcupacion estadoOcupacion);
}
