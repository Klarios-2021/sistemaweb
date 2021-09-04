package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.models.enums.VariableAmbiental;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnsayoConverter implements Converter<String, Ensayo> {
    @Override
    public Ensayo convert(String tipoEnsayo) {
        switch(tipoEnsayo){
            case "Ensayo de variable ambiental iluminación":
                return new EnsayoVariableAmbiental(VariableAmbiental.ILUMINACION);
            case "Ensayo de variable ambiental ruido":
                return new EnsayoVariableAmbiental(VariableAmbiental.RUIDO);
            case "Ensayo de variable ambiental temperatura":
                return new EnsayoVariableAmbiental(VariableAmbiental.TEMPERATURA);
            case "Ensayo de variable ambiental humedad":
                return new EnsayoVariableAmbiental(VariableAmbiental.HUMEDAD);
            case "Ensayo de presión diferencial":
                return new EnsayoPresionDiferencial();
            case "Ensayo de conteo de particulas":
                return new EnsayoConteoParticulas();
            case "Ensayo de recuperación de clase":
                return new EnsayoRecuperacionClase();
        }
        return null;
    }
}