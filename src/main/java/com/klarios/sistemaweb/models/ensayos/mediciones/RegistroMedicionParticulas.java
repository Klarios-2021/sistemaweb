package com.klarios.sistemaweb.models.ensayos.mediciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Embeddable
public class RegistroMedicionParticulas {
    @OneToOne
    @JoinColumn(name = "registro_medicion_05")
    Medicion medicion05;
    @OneToOne
    @JoinColumn(name = "registro_medicion_5")
    Medicion medicion5;
}
