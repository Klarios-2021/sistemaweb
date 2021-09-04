package com.klarios.sistemaweb.forms;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.Sala;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EnsayosSala {
    public List<EnsayosEquipo> ensayoXequipo = new ArrayList<>();;
    public List<Ensayo> ensayos = new ArrayList<>();;
    public Sala sala;
}
