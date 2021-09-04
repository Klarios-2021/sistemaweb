package com.klarios.sistemaweb.forms;

import com.klarios.sistemaweb.models.Material;
import com.klarios.sistemaweb.models.Trabajo;
import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariableAmbiental;
import com.klarios.sistemaweb.models.ensayos.mediciones.Medicion;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionAmbiental;
import com.klarios.sistemaweb.models.ensayos.mediciones.RegistroMedicionRecuperacion;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import com.klarios.sistemaweb.models.enums.VariableAmbiental;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FormRealizacionEnsayo {
    String descripcion;
    Long id;
    String realizo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaRealizacion;
    String controlo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaControl;
    String protocolo;
    String observaciones;
    EstadoEnsayo estado;
    Material material;
    Trabajo trabajoAsociado;
    Ensayo ensayo;

    //Ensayo variables ambientales
    VariableAmbiental variableAmbiental;
    List<RegistroMedicionAmbiental> medicionesAmbientales = new ArrayList<>();
    BigDecimal limiteSuperior;
    BigDecimal limiteInferior;

    //Ensayo renovación
    Medicion concentracionInicial;
    Medicion concentracionMaxima;
    List<RegistroMedicionRecuperacion> medicionesRecuperacion = new ArrayList<>();
}
