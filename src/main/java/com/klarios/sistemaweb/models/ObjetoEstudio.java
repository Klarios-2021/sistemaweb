package com.klarios.sistemaweb.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ObjetoEstudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objeto_estudio_id")
    Long id;

    @Column(name = "objeto_estudio_nombre")
    String nombre;
    @Column(name = "objeto_estudio_tag")
    String tag;

    @AttributeOverrides({
            @AttributeOverride(name="altura",
                    column=@Column(name="objeto_estudio_altura")),
            @AttributeOverride(name="ancho",
                    column=@Column(name="objeto_estudio_ancho")),
            @AttributeOverride(name="largo",
                    column=@Column(name="objeto_estudio_largo"))
    })
    @Embedded
    Dimension dimension;

    @OneToMany
    @JoinColumn(name = "ensayo_objeto_estudio")
    List<Ensayo> ensayos = new ArrayList<>();

}
