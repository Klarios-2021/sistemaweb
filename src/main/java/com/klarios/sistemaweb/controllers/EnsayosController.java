package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.filters.FiltroEnsayos;
import com.klarios.sistemaweb.models.*;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariablesAmbientales;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import com.klarios.sistemaweb.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Controller
public class EnsayosController {

    @Autowired
    EnsayosDAO ensayosDAO;

    @Autowired
    LaboratoriosDAO laboratoriosDAO;

    @Autowired
    SalasDAO salasDAO;

    @Autowired
    MaterialesDAO materialesDAO;

    @Autowired
    VersionDatosDAO versionDatosDAO;

    @Autowired
    EquiposDAO equiposDAO;

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

            Map<String,String> datos = new TreeMap<String,String>(ensayoOptional.get().getVersionesDatos().get(0).getDatos());
            model.addAttribute("ensayo",ensayoOptional.get());

            model.addAttribute("datos",datos);
            return "ensayo_detalle";
        }
        else{
            return "not_found_error";
        }

    }
    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/ensayos/nuevo")
    public String getFormNuevoEnsayoSala(@PathVariable("idLaboratorio") String idLaboratorio,
                                         @PathVariable("idEstablecimiento") String idEstablecimiento,
                                         @PathVariable("idSector") String idSector,
                                         @PathVariable("idSala") String idSala,
                                         Ensayo ensayo,
                                         FiltroEnsayos filtroEnsayos,
                                         VersionDatos versionDatos,
                                         Model model) {

        System.out.println("Se solicitó el formulario para crear un nuevo ensayo de sala");
        System.out.println("Ensayo seleccionado: " + filtroEnsayos.getTipoEnsayo());

        Optional<Sala> salaOptional = salasDAO.findById(Long.parseLong(idSala));

        if(salaOptional.isPresent() && filtroEnsayos.getTipoEnsayo().equals("var-amb")){
            EnsayoVariablesAmbientales ensayoVariablesAmbientales = new EnsayoVariablesAmbientales();
            ensayoVariablesAmbientales.setMaterial(salaOptional.get());
            model.addAttribute("cantMediciones", ensayoVariablesAmbientales.obtenerMedicionesNecesarias());
        }

        model.addAttribute("idLaboratorio", idLaboratorio);
        model.addAttribute("idEstablecimiento", idEstablecimiento);
        model.addAttribute("idSector", idSector);
        model.addAttribute("idSala", idSala);
        model.addAttribute("filtroEnsayos", filtroEnsayos);
        model.addAttribute("ensayo", ensayo);

        model.addAttribute("versionDatos",versionDatos);

        return "form_nuevo_ensayo_sala";
    }

    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/equipos/{idEquipo}/ensayos/nuevo")
    public String getFormNuevoEnsayoEquipo(@PathVariable("idLaboratorio") String idLaboratorio,
                                         @PathVariable("idEstablecimiento") String idEstablecimiento,
                                         @PathVariable("idSector") String idSector,
                                         @PathVariable("idSala") String idSala,
                                         @PathVariable("idEquipo") String idEquipo,
                                         FiltroEnsayos filtroEnsayos,
                                         VersionDatos versionDatos,
                                         Ensayo ensayo,
                                         Model model) {

        System.out.println("Se solicitó el formulario para crear un nuevo ensayo de equipo");
        System.out.println("Ensayo seleccionado: " + filtroEnsayos.getTipoEnsayo());

        Optional<Equipo> equipoOptional = equiposDAO.findById(Long.parseLong(idEquipo));

        if(equipoOptional.isPresent() && filtroEnsayos.getTipoEnsayo().equals("var-amb")){
            EnsayoVariablesAmbientales ensayoVariablesAmbientales = new EnsayoVariablesAmbientales();
            ensayoVariablesAmbientales.setMaterial(equipoOptional.get());
            model.addAttribute("cantMediciones", ensayoVariablesAmbientales.obtenerMedicionesNecesarias());
        }

        model.addAttribute("idLaboratorio", idLaboratorio);
        model.addAttribute("idEstablecimiento", idEstablecimiento);
        model.addAttribute("idSector", idSector);
        model.addAttribute("idSala", idSala);
        model.addAttribute("idEquipo", idEquipo);
        model.addAttribute("filtroEnsayos", filtroEnsayos);
        model.addAttribute("ensayo",ensayo);
        model.addAttribute("versionDatos",versionDatos);

        return "form_nuevo_ensayo_equipo";
    }

    public void guardarEnsayo(Material material, Ensayo ensayo, String tipoEnsayo){
        switch (tipoEnsayo){
            case "var-amb":
                EnsayoVariablesAmbientales ensayoVariablesAmbientales = new EnsayoVariablesAmbientales();
                ensayoVariablesAmbientales.setMaterial(ensayo.getMaterial());
                ensayoVariablesAmbientales.setProtocolo(ensayo.getProtocolo());
                ensayoVariablesAmbientales.setControlo(ensayo.getControlo());
                ensayoVariablesAmbientales.setFechaControl(ensayo.getFechaControl());
                ensayoVariablesAmbientales.setFechaRealizacion(ensayo.getFechaRealizacion());
                ensayoVariablesAmbientales.setRealizo(ensayo.getRealizo());
                ensayoVariablesAmbientales.setVersionesDatos(ensayo.getVersionesDatos());
                ensayoVariablesAmbientales.setMaterial(material);
                ensayoVariablesAmbientales.setEstado(EstadoEnsayo.APROBADO);
                ensayosDAO.save(ensayoVariablesAmbientales);
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
                                  VersionDatos versionDatos,
                                  FiltroEnsayos filtroEnsayos,
                                  Errors errores,
                                  Model model) {

        System.out.println("Alguien está guardando un nuevo ensayo en una sala");

        if(errores.hasErrors()){
            return "form_nuevo_ensayo_sala";
        }

        Optional<Sala> salaOptional = salasDAO.findById(Long.parseLong(idSala));

        if(salaOptional.isPresent()){
            versionDatos.setFecha(LocalDateTime.now());
            versionDatos.setObservaciones("Versión inicial");
            versionDatosDAO.save(versionDatos);

            ensayo.setId(null);
            ensayo.agregarVersionDatos(versionDatos);

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
                                    VersionDatos versionDatos,
                                    FiltroEnsayos filtroEnsayos,
                                    Errors errores,
                                    Model model) {

        System.out.println("Alguien está guardando un nuevo ensayo en un equipo");

        if(errores.hasErrors()){
            return "form_nuevo_ensayo_equipo";
        }

        Optional<Equipo> equipoOptional = equiposDAO.findById(Long.parseLong(idEquipo));

        if(equipoOptional.isPresent()){
            versionDatos.setFecha(LocalDateTime.now());
            versionDatos.setObservaciones("Versión inicial");
            versionDatosDAO.save(versionDatos);

            ensayo.setId(null);
            ensayo.agregarVersionDatos(versionDatos);

            guardarEnsayo(equipoOptional.get(), ensayo, filtroEnsayos.getTipoEnsayo());
        }
        else{
            return "not_found_error";
        }

        return "redirect:";
    }



}
