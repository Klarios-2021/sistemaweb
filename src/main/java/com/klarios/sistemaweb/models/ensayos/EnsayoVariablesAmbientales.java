package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.models.Ensayo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("VAR_AMB")
public class EnsayoVariablesAmbientales extends Ensayo {

    public int obtenerMedicionesNecesarias(){
        Double area = getMaterial().getDimension().obtenerArea();
        return area < 4.0 ? 2 :
               area < 14.0 ? 2 + (int)Math.floor((area - 4.0) / 2) : 7;
    }
}
