package com.klarios.sistemaweb.models.ensayos.mediciones;

import com.klarios.sistemaweb.models.enums.SentidoCirculacion;
import com.klarios.sistemaweb.models.enums.Unidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "medicion")
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicion_id")
    Long id;

    @Column(name = "medicion_valor")
    public BigDecimal valor;
    @Enumerated(EnumType.STRING)
    @Column(name = "medicion_unidad")
    public Unidad unidad;
    //SentidoCirculacion sentidoCirculacion = SentidoCirculacion.NA;
}
