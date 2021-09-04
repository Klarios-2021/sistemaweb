package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionFiltro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ensayo_integridad_filtros")
public class EnsayoIntegridadFiltros extends Ensayo{

    public String descripcion = "Ensayo de integridad de filtros";

    @ElementCollection
    @CollectionTable(name = "registro_medicion_filtro",
            joinColumns=@JoinColumn(name = "ensayo_id"))
    List<RegistroMedicionFiltro> mediciones = new ArrayList<>();

    @Override
    public boolean esValido() {
        return false;
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
}
