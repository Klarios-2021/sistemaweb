package com.klarios.sistemaweb.forms;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.Equipo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EnsayosEquipo {
    public List<Ensayo> ensayos = new ArrayList<>();
    public Equipo equipo;
}
