package com.klarios.sistemaweb.forms;

import com.klarios.sistemaweb.models.ValoresComparacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TrabajoForm {
    public List<EnsayosSala> ensayosXsala = new ArrayList<>();
    public ValoresComparacion valoresComparacion;
}
