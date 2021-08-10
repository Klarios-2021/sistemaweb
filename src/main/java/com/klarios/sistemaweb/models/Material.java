package com.klarios.sistemaweb.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "material")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    Long id;

    @Column(name = "material_nombre")
    String nombre;
    @Column(name = "material_tag")
    String tag;

    @AttributeOverrides({
            @AttributeOverride(name="altura",
                    column=@Column(name="material_altura")),
            @AttributeOverride(name="ancho",
                    column=@Column(name="material_ancho")),
            @AttributeOverride(name="largo",
                    column=@Column(name="material_largo"))
    })
    @Embedded
    Dimension dimension;

    @OneToMany(mappedBy = "material")
    List<Ensayo> ensayos = new ArrayList<>();

    public void agregarEnsayo(Ensayo ensayo){this.ensayos.add(ensayo);}
}
