package com.klarios.sistemaweb.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
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

    public Double obtenerArea(){
        return ancho * largo;
    }
    public Double obtenerVolumen(){
        return ancho * largo * altura;
    }
}
