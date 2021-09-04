package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.filters.FiltroEnsayos;
import com.klarios.sistemaweb.forms.FormRealizacionEnsayo;
import com.klarios.sistemaweb.models.*;
import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariableAmbiental;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import com.klarios.sistemaweb.models.enums.Unidad;
import com.klarios.sistemaweb.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EnsayosController {

    @Autowired
    EnsayosDAO ensayosDAO;

    @Autowired
    EnsayosVariablesAmbientalesDAO ensayosVariablesAmbientalesDAO;

    @Autowired
    LaboratoriosDAO laboratoriosDAO;

    @Autowired
    SalasDAO salasDAO;

    @Autowired
    MaterialesDAO materialesDAO;

    @Autowired
    EquiposDAO equiposDAO;

    @Autowired
    MedicionesDAO medicionesDAO;


    @GetMapping("ensayos")
    public String getEnsayos(Model model) {

        System.out.println("Se solicitaron los ensayos");

        List<Ensayo> ensayos = ensayosDAO.findAll();
        model.addAttribute("ensayos", ensayos);

        return "ensayos";
    }

    @GetMapping("ensayos/{idEnsayo}")
    public String getEnsayo(@PathVariable("idEnsayo") String idEnsayo,
                             Model model) {

        System.out.println("Se solicito un ensayo");

        Optional<Ensayo> ensayoOptional = ensayosDAO.findById(Long.parseLong(idEnsayo));

        if(ensayoOptional.isPresent()){
            model.addAttribute("ensayo",ensayoOptional.get());
            return "ensayo_detalle";
        }
        else{
            return "not_found_error";
        }
    }

    @GetMapping("trabajos/{idTrabajo}/ensayos/{idEnsayo}")
    public String getEnsayoTrabajo(@PathVariable("idEnsayo") String idEnsayo,
                            Model model) {

        System.out.println("Se solicito un ensayo");

        Optional<Ensayo> ensayoOptional = ensayosDAO.findById(Long.parseLong(idEnsayo));

        if(ensayoOptional.isPresent()){
            model.addAttribute("ensayo",ensayoOptional.get());
            return "ensayo_detalle";
        }
        else{
            return "not_found_error";
        }
    }

    @GetMapping("trabajos/{idTrabajo}/ensayos/{idEnsayo}/realizacion")
    public String getFormRealizacionEnsayo(@PathVariable("idTrabajo") String idTrabajo, @PathVariable("idEnsayo") String idEnsayo, Model model) {

        System.out.println("Se solicito el formulario para completar ensayo");

        Optional<Ensayo> ensayoOptional = ensayosDAO.findById(Long.parseLong(idEnsayo));

        if(ensayoOptional.isPresent()){
            FormRealizacionEnsayo formRealizacionEnsayo = new FormRealizacionEnsayo();
            formRealizacionEnsayo.setEnsayo(ensayoOptional.get());
            model.addAttribute("formRealizacionEnsayo", formRealizacionEnsayo);
            model.addAttribute("idTrabajo",idTrabajo);
            return "form_realizacion_ensayo";
        }
        else{
            return "not_found_error";
        }
    }

    @PostMapping("trabajos/{idTrabajo}/ensayos/{idEnsayo}/realizacion")
    public String cargarRealizacionEnsayo(@PathVariable("idTrabajo") String idTrabajo,
                                          @PathVariable("idEnsayo") String idEnsayo,
                                          @Valid FormRealizacionEnsayo formRealizacionEnsayo,
                                          BindingResult bindingResult,
                                          Model model) {

        System.out.println("Se está cargando la realización de un ensayo");

        Optional<Ensayo> ensayoOptional = ensayosDAO.findById(Long.parseLong(idEnsayo));

        if(ensayoOptional.isPresent()){
            if(bindingResult.hasErrors()){
                formRealizacionEnsayo.setEnsayo(ensayoOptional.get());
                model.addAttribute("formRealizacionEnsayo", formRealizacionEnsayo);
                model.addAttribute("idTrabajo",idTrabajo);
                return "form_realizacion_ensayo";
            }
            else{
                Ensayo ensayo = ensayoOptional.get();
                ensayo.setearValores(formRealizacionEnsayo);
                ensayo.validar();
                ensayosDAO.save(ensayo);
                return "redirect:/trabajos/" + idTrabajo;
            }
        }
        else{
            return "not_found_error";
        }

    }

    /*
    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/ensayos/nuevo")
    public String getFormNuevoEnsayoSala(@PathVariable("idLaboratorio") String idLaboratorio,
                                         @PathVariable("idEstablecimiento") String idEstablecimiento,
                                         @PathVariable("idSector") String idSector,
                                         @PathVariable("idSala") String idSala,
                                         Ensayo ensayo,
                                         FiltroEnsayos filtroEnsayos,
                                         Model model) {

        System.out.println("Se solicitó el formulario para crear un nuevo ensayo de sala");
        System.out.println("Ensayo seleccionado: " + filtroEnsayos.getTipoEnsayo());

        Optional<Sala> salaOptional = salasDAO.findById(Long.parseLong(idSala));

        if(salaOptional.isPresent() && filtroEnsayos.getTipoEnsayo().equals("var-amb")){
            EnsayoVariableAmbiental ensayoVariableAmbiental = new EnsayoVariableAmbiental();
            ensayoVariableAmbiental.setMaterial(salaOptional.get());
            model.addAttribute("cantMediciones", ensayoVariableAmbiental.cantidadMedicionesNecesarias());
        }

        model.addAttribute("idLaboratorio", idLaboratorio);
        model.addAttribute("idEstablecimiento", idEstablecimiento);
        model.addAttribute("idSector", idSector);
        model.addAttribute("idSala", idSala);
        model.addAttribute("filtroEnsayos", filtroEnsayos);
        model.addAttribute("ensayo", ensayo);
        
        return "form_nuevo_ensayo_sala";
    }

    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/equipos/{idEquipo}/ensayos/nuevo")
    public String getFormNuevoEnsayoEquipo(@PathVariable("idLaboratorio") String idLaboratorio,
                                         @PathVariable("idEstablecimiento") String idEstablecimiento,
                                         @PathVariable("idSector") String idSector,
                                         @PathVariable("idSala") String idSala,
                                         @PathVariable("idEquipo") String idEquipo,
                                         FiltroEnsayos filtroEnsayos,
                                         Ensayo ensayo,
                                         Model model) {

        System.out.println("Se solicitó el formulario para crear un nuevo ensayo de equipo");
        System.out.println("Ensayo seleccionado: " + filtroEnsayos.getTipoEnsayo());

        Optional<Equipo> equipoOptional = equiposDAO.findById(Long.parseLong(idEquipo));

        if(equipoOptional.isPresent() && filtroEnsayos.getTipoEnsayo().equals("var-amb")){
            EnsayoVariableAmbiental ensayoVariableAmbiental = new EnsayoVariableAmbiental();
            ensayoVariableAmbiental.setMaterial(equipoOptional.get());
            model.addAttribute("cantMediciones", ensayoVariableAmbiental.cantidadMedicionesNecesarias());
        }

        model.addAttribute("idLaboratorio", idLaboratorio);
        model.addAttribute("idEstablecimiento", idEstablecimiento);
        model.addAttribute("idSector", idSector);
        model.addAttribute("idSala", idSala);
        model.addAttribute("idEquipo", idEquipo);
        model.addAttribute("filtroEnsayos", filtroEnsayos);
        model.addAttribute("ensayo",ensayo);

        return "form_nuevo_ensayo_equipo";
    }

    public void guardarEnsayo(Material material, Ensayo ensayo, String tipoEnsayo){
        switch (tipoEnsayo){
            case "var-amb":
                EnsayoVariableAmbiental ensayoVariableAmbiental = new EnsayoVariableAmbiental();
                ensayoVariableAmbiental.setMaterial(ensayo.getMaterial());
                ensayoVariableAmbiental.setProtocolo(ensayo.getProtocolo());
                ensayoVariableAmbiental.setControlo(ensayo.getControlo());
                ensayoVariableAmbiental.setFechaControl(ensayo.getFechaControl());
                ensayoVariableAmbiental.setFechaRealizacion(ensayo.getFechaRealizacion());
                ensayoVariableAmbiental.setRealizo(ensayo.getRealizo());
                //ensayoVariablesAmbientales.setVersionesDatos(ensayo.getVersionesDatos());
                ensayoVariableAmbiental.setMaterial(material);
                ensayoVariableAmbiental.setEstado(EstadoEnsayo.APROBADO);
                ensayosDAO.save(ensayoVariableAmbiental);
                break;
            default:
                ensayo.setEstado(EstadoEnsayo.APROBADO);
                material.agregarEnsayo(ensayo);
                ensayo.setMaterial(material);
                ensayosDAO.save(ensayo);
                break;
        }
    }
    @PostMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/ensayos")
    public String crearEnsayoSala (@PathVariable("idSala") String idSala,
                                  @Valid Ensayo ensayo,
                                  FiltroEnsayos filtroEnsayos,
                                  Errors errores,
                                  Model model) {

        System.out.println("Alguien está guardando un nuevo ensayo en una sala");

        if(errores.hasErrors()){
            return "form_nuevo_ensayo_sala";
        }

        Optional<Sala> salaOptional = salasDAO.findById(Long.parseLong(idSala));

        if(salaOptional.isPresent()){
            ensayo.setId(null);
            //ensayo.agregarVersionDatos(versionDatos);

            guardarEnsayo(salaOptional.get(), ensayo, filtroEnsayos.getTipoEnsayo());
        }
        else{
            return "not_found_error";
        }

        return "redirect:";
    }

    @PostMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/equipos/{idEquipo}/ensayos")
    public String crearEnsayoEquipo(@PathVariable("idEquipo") String idEquipo,
                                    @Valid Ensayo ensayo,
                                    FiltroEnsayos filtroEnsayos,
                                    Errors errores,
                                    Model model) {

        System.out.println("Alguien está guardando un nuevo ensayo en un equipo");

        if(errores.hasErrors()){
            return "form_nuevo_ensayo_equipo";
        }

        Optional<Equipo> equipoOptional = equiposDAO.findById(Long.parseLong(idEquipo));

        if(equipoOptional.isPresent()){

            ensayo.setId(null);
            //ensayo.agregarVersionDatos(versionDatos);

            guardarEnsayo(equipoOptional.get(), ensayo, filtroEnsayos.getTipoEnsayo());
        }
        else{
            return "not_found_error";
        }

        return "redirect:";
    }

     */



}
