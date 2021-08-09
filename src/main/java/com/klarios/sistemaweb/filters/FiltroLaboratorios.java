package com.klarios.sistemaweb.filters;

import com.klarios.sistemaweb.models.Laboratorio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FiltroLaboratorios {
    String palabraClave = "";

    public List<Laboratorio> filtrarLaboratorios(List<Laboratorio> laboratorios) {
        return palabraClave == "" ? laboratorios : laboratorios.stream().
                filter(lab -> lab.getNombre().toLowerCase().contains(palabraClave.toLowerCase())
                || lab.getRazonSocial().toLowerCase().contains(palabraClave.toLowerCase())).collect(Collectors.toList());
    }
}
