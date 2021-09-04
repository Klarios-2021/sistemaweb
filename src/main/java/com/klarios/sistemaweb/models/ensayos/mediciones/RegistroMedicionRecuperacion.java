package com.klarios.sistemaweb.models.ensayos.mediciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Getter
@Setter
@Embeddable
public class RegistroMedicionRecuperacion {
    @OneToOne(cascade = CascadeType.ALL)
    public Medicion medicion;
}
