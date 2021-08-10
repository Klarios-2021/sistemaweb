package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.filters.FiltroEnsayos;
import com.klarios.sistemaweb.models.*;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariablesAmbientales;
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
import java.util.Optional;

@Controller
public class EnsayosController {

    @Autowired
    EnsayosDAO ensayosDAO;

    @Autowired
    LaboratoriosDAO laboratoriosDAO;

    @Autowired
    SalasDAO salasDAO;

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

    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/ensayos/nuevo")
    public String getFormNuevoEnsayoSala(@PathVariable("idLaboratorio") String idLaboratorio,
                                         @PathVariable("idEstablecimiento") String idEstablecimiento,
                                         @PathVariable("idSector") String idSector,
                                         @PathVariable("idSala") String idSala,
                                         FiltroEnsayos filtroEnsayos,
                                         VersionDatos versionDatos,
                                         Ensayo ensayo,
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
        model.addAttribute("ensayo",ensayo);
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

    @PostMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/ensayos")
    public String crearEnsayoSala(@PathVariable("idSala") String idSala,
                              @Valid Ensayo ensayo, VersionDatos versionDatos, Errors errores, Model model) {

        System.out.println("Alguien está guardando un nuevo ensayo en una sala");

        if(errores.hasErrors()){
            return "form_nuevo_ensayo_sala";
        }

        Optional<Sala> salaOptional = salasDAO.findById(Long.parseLong(idSala));

        if(salaOptional.isPresent()){
            versionDatos.setFecha(LocalDateTime.now());
            versionDatos.setObservaciones("Versión inicial");

            ensayo.setId(null);
            ensayo.agregarVersionDatos(versionDatos);

            Sala sala = salaOptional.get();
            ensayo.setMaterial(sala);
            sala.agregarEnsayo(ensayo);

            versionDatosDAO.save(versionDatos);
            ensayosDAO.save(ensayo);
            salasDAO.save(sala);
        }
        else{
            return "not_found_error";
        }

        return "redirect:";
    }

    @PostMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/equipos/{idEquipo}/ensayos")
    public String crearEnsayoEquipo(@PathVariable("idEquipo") String idEquipo,
                              @Valid Ensayo ensayo, VersionDatos versionDatos, Errors errores, Model model) {

        System.out.println("Alguien está guardando un nuevo ensayo en un equipo");

        if(errores.hasErrors()){
            return "form_nuevo_ensayo_equipo";
        }

        Optional<Equipo> equipoOptional = equiposDAO.findById(Long.parseLong(idEquipo));

        if(equipoOptional.isPresent()){
            versionDatos.setFecha(LocalDateTime.now());
            versionDatos.setObservaciones("Versión inicial");

            ensayo.setId(null);
            ensayo.agregarVersionDatos(versionDatos);

            Equipo equipo = equipoOptional.get();
            ensayo.setMaterial(equipo);
            equipo.agregarEnsayo(ensayo);

            versionDatosDAO.save(versionDatos);
            ensayosDAO.save(ensayo);
            equiposDAO.save(equipo);
        }
        else{
            return "not_found_error";
        }

        return "redirect:";
    }



}
