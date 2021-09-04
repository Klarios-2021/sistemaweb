package com.klarios.sistemaweb.models.ensayos.mediciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class RegistroMedicionFiltro {
    @OneToOne
    @JoinColumn(name = "registro_medicion_background")
    Medicion medicionBackground;
    @OneToOne
    @JoinColumn(name = "registro_medicion_downstream")
    Medicion medicionDownstream;
    @OneToOne
    @JoinColumn(name = "registro_medicion_upstream")
    Medicion medicionUpstream;

    public Medicion resultadoMedicion(){
        Medicion medicion = new Medicion();
        medicion.valor = medicionDownstream.valor.add(medicionBackground.valor.negate()).divide(medicionUpstream.valor);
        medicion.unidad = medicionDownstream.unidad;
        return medicion;
    }


}
