package com.klarios.sistemaweb.filters;

import com.klarios.sistemaweb.models.Laboratorio;
import com.klarios.sistemaweb.models.Trabajo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FiltroTrabajos {
    String palabraClave = "";

    /*
    public List<Trabajo> filtrarTrabajos(List<Trabajo> trabajos) {
        return palabraClave == "" ? trabajos : trabajos.stream().
                filter(trabajo -> trabajo.getNombre().toLowerCase().contains(palabraClave.toLowerCase())
                        || lab.getRazonSocial().toLowerCase().contains(palabraClave.toLowerCase())).collect(Collectors.toList());
    }
     */
    public List<Trabajo> filtrarTrabajos(List<Trabajo> trabajos){
        return trabajos;
    }
}
