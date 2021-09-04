package com.klarios.sistemaweb.models.ensayos;

import com.klarios.sistemaweb.forms.FormRealizacionEnsayo;
import com.klarios.sistemaweb.models.Material;
import com.klarios.sistemaweb.models.Trabajo;
import com.klarios.sistemaweb.models.ValoresComparacion;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import com.klarios.sistemaweb.models.enums.Unidad;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ensayo")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ensayo {

    @Column(name = "ensayo_descripcion")
    String descripcion = "Ensayo genérico";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ensayo_id")
    Long id;

    @Column(name = "ensayo_realizo")
    String realizo;
    @Column(name = "ensayo_fecha_realizacion")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaRealizacion;
    @Column(name = "ensayo_controlo")
    String controlo;
    @Column(name = "ensayo_fecha_control")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaControl;
    @Column(name = "ensayo_protocolo")
    String protocolo;
    @Column(name = "ensayo_observaciones")
    String observaciones = "Observaciones genericas";
    @Enumerated(EnumType.STRING)
    @Column(name = "ensayo_estado")
    EstadoEnsayo estado = EstadoEnsayo.PENDIENTE;

    @ManyToOne
    @JoinColumn(name = "ensayo_material")
    Material material;

    @Transient
    Trabajo trabajoAsociado;

    public void procesarInformacion(){
        /* Mi idea es que este metodo cada ensayo lo implemente como corresponde, y que cambie su estado interno en
           base a la información cargada manualmente.
           - Contemplar que estos campos calculados sean privados para que solo puedan ser modificados desde este método.
           - Contemplar que siempre que se modifiquen los valores cargados, los campos calculables deben ser recalculados
             para mantener la consistencia.

           Otra opción es no guardar en el estado los valores calculables, y calcularlos en tiempo real vía metodos,
           aunque se pierde acceso a cierta información, por ejemplo en la BD.
         */
        this.calcularValores();
        this.validar();
    }
    public void calcularValores(){};
    public void validar(){
        this.estado = this.esValido() ? EstadoEnsayo.APROBADO : EstadoEnsayo.DESAPROBADO;
    };

    public boolean esValido(){return false;};
    public int cantidadMedicionesNecesarias(){return -1;};
    public void configurarValoresComparacion(ValoresComparacion valoresComparacion){};
    public Unidad unidadMediciones(){return null;};

//    public abstract boolean esValido();
//    public abstract int cantidadMedicionesNecesarias();
//    public abstract void configurarValoresComparacion(ValoresComparacion valoresComparacion);
//    public abstract Unidad unidadMediciones();

    public void setearValores(FormRealizacionEnsayo formRealizacionEnsayo){
        this.observaciones = formRealizacionEnsayo.getObservaciones();
        this.realizo = formRealizacionEnsayo.getRealizo();
        this.fechaRealizacion = formRealizacionEnsayo.getFechaRealizacion();
        this.controlo = formRealizacionEnsayo.getControlo();
        this.fechaControl = formRealizacionEnsayo.getFechaControl();
        this.protocolo = formRealizacionEnsayo.getProtocolo();
        this.observaciones = formRealizacionEnsayo.getObservaciones();
    }


}
