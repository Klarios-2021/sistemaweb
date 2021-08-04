package com.klarios.sistemaweb.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "VersionDatos")
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
    @CollectionTable(name = "Datos", joinColumns = {@JoinColumn(name = "datos_version_datos",
            referencedColumnName = "version_datos_id")})
    @MapKeyColumn(name="datos_nombre_campo")
    @Column(name="datos_valor_campo")
    Map<String,String> datos;

}
