package com.klarios.sistemaweb.models.ensayos.mediciones;

import com.klarios.sistemaweb.models.Puerta;
import com.klarios.sistemaweb.models.enums.SentidoCirculacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class RegistroMedicionPresion {
    @OneToOne
    @JoinColumn(name = "registro_medicion")
    public Medicion medicion;
    @Enumerated(EnumType.STRING)
    @Column(name = "registro_sentido_esperado")
    public SentidoCirculacion sentidoEsperado;
    @OneToOne
    @JoinColumn(name = "registro_puerta_medida")
    public Puerta puerta;
}
