package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionPresion;
import com.klarios.sistemaweb.models.enums.SentidoCirculacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ensayo_presion_diferencial")
public class EnsayoPresionDiferencial extends Ensayo{

    String descripcion = "Ensayo de presión diferencial";

    @ElementCollection
    @CollectionTable(name = "registro_medicion_presion",
            joinColumns=@JoinColumn(name = "ensayo_id"))
    public List<RegistroMedicionPresion> medicionesPresion = new ArrayList<>();

    @Override
    public void calcularValores(){
        //DEFINIR
    }
    @Override
    public boolean esValido(){

        boolean aprobado = true;

        for(int i = 0 ; i < this.medicionesPresion.size() ; i++){
            RegistroMedicionPresion registroMedicionPresion = this.medicionesPresion.get(i);
            if((registroMedicionPresion.medicion.valor.compareTo(BigDecimal.ZERO) < 0 && registroMedicionPresion.sentidoEsperado == SentidoCirculacion.SOBREPRESION) ||
                    (registroMedicionPresion.medicion.valor.compareTo(BigDecimal.ZERO) > 0 && registroMedicionPresion.sentidoEsperado == SentidoCirculacion.DEPRESION))
            {
                aprobado = false;
                break;
            }
        }
        return aprobado;
    }

    @Override
    public int cantidadMedicionesNecesarias() {
        return this.material.cantidadDePuertas();
    }

    @Override
    public void configurarValoresComparacion(ValoresComparacion valoresComparacion) {

    }


}
