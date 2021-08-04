package com.klarios.sistemaweb.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Dimension {

    Double altura;
    Double ancho;
    Double largo;

    public Dimension(){ }

    public Dimension(Double altura, Double ancho, Double largo) {
        this.altura = altura;
        this.ancho = ancho;
        this.largo = largo;
    }
}
