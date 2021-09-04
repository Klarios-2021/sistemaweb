package com.klarios.sistemaweb.models.ensayos;
import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionParticulas;
import com.klarios.sistemaweb.models.enums.EstadoOcupacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ensayo_conteo_particulas")
public class EnsayoConteoParticulas extends Ensayo {

    public String descripcion = "Ensayo de conteo de particulas";

    @Enumerated(EnumType.STRING)
    @Column(name = "ensayo_estado_ocupacion")
    EstadoOcupacion estadoOcupacion;

    @ElementCollection
    @CollectionTable(name = "registro_medicion_particulas",
            joinColumns=@JoinColumn(name = "ensayo_id"))
    List<RegistroMedicionParticulas> mediciones = new ArrayList<>();

    @Override
    public boolean esValido(){

        boolean aprobado = true;
        for(RegistroMedicionParticulas medicion : this.mediciones)
        {
            if(medicion.getMedicion05().valor.compareTo(this.limite05()) > 0 || medicion.getMedicion5().valor.compareTo(this.limite5()) > 0){
                aprobado = false;
                break;
            }
        }
        return aprobado;
    }

    @Override
    public int cantidadMedicionesNecesarias(){
        Double area = getMaterial().getDimension().obtenerArea();
        return area < 4.0 ? 2 :
                area < 14.0 ? 2 + (int)Math.floor((area - 4.0) / 2) : 7;
    }

    @Override
    public void configurarValoresComparacion(ValoresComparacion valoresComparacion) {

    }

    public BigDecimal limite05(){
        return this.material.getClasificacion().limite05(estadoOcupacion);
    }
    public BigDecimal limite5(){
        return this.material.getClasificacion().limite5(estadoOcupacion);
    }

}
