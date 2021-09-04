package com.klarios.sistemaweb.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "puerta")
public class Puerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puerta_id")
    Long id;
    /*Para simplicidad del modelo, la puerta pertenece a una sala y conecta con otra. La misma puerta real
      será una puerta distinta dependiendo de en que sala esté.
     */
    @ManyToOne
    @JoinColumn(name = "puerta_sala_adyacente")
    Sala salaAdyacente; //null -> la puerta sale al "exterior" (sector, establecimiento, etc)
}
