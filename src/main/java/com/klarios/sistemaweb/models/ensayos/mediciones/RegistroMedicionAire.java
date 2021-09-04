package com.klarios.sistemaweb.models.ensayos.mediciones;

import com.klarios.sistemaweb.models.enums.AperturaVentana;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class RegistroMedicionAire {
    @OneToOne
    public Medicion medicion;
    @Enumerated(EnumType.STRING)
    public AperturaVentana apertura;
}
