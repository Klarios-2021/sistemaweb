package com.klarios.sistemaweb.models;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariableAmbiental;
import com.klarios.sistemaweb.models.normas.Norma;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "material_norma")
    Norma clasificacion;

    @OneToMany
    @JoinColumn(name = "puerta_material")
    List<Puerta> puertas = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "filtro_material")
    List<Filtro> filtros = new ArrayList<>();

    public int cantidadDePuertas(){
        return this.puertas.size();
    };

    public void agregarEnsayo(Ensayo ensayo){this.ensayos.add(ensayo);}


}
