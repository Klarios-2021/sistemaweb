package com.klarios.sistemaweb.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "version_datos")
public class VersionDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_datos_id")
    Long id;

    @Column(name = "version_datos_fecha")
    LocalDateTime fecha;

    @Column(name = "version_datos_observaciones")
    String observaciones;

    @ElementCollection
    @CollectionTable(name = "dato", joinColumns = {@JoinColumn(name = "dato_version_datos",
            referencedColumnName = "version_datos_id")})
    @MapKeyColumn(name="dato_nombre_campo")
    @Column(name="dato_valor_campo")
    Map<String,String> datos;

}
