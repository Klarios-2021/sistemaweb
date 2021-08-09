package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Establecimiento;
import com.klarios.sistemaweb.models.Laboratorio;
import com.klarios.sistemaweb.models.Sector;
import com.klarios.sistemaweb.repositories.EstablecimientosDAO;
import com.klarios.sistemaweb.repositories.LaboratoriosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EstablecimientosController {

    @Autowired
    public EstablecimientosDAO establecimientosDAO;

    @Autowired
    public LaboratoriosDAO laboratoriosDAO;

    @GetMapping("laboratorios/{idlaboratorio}/establecimientos/{idEstablecimiento}")
    public String getEstablecimiento(@PathVariable("idEstablecimiento") String idLaboratorio,
                                     @PathVariable("idEstablecimiento") String idEstablecimiento, Sector sector, Model model) {
        System.out.println("Alguien pidió el detalle de un establecimiento");

        Optional<Establecimiento> establecimientoOptional = establecimientosDAO.findById(Long.parseLong(idEstablecimiento));

        if(establecimientoOptional.isPresent()){
            model.addAttribute("establecimiento",establecimientoOptional.get());
            model.addAttribute("sector", sector);
            model.addAttribute("idEstablecimiento", idEstablecimiento);
            model.addAttribute("idLaboratorio", idLaboratorio);
            return "establecimiento_detalle";
        }
        else{
            return "not_found_error";
        }
    }
    @PostMapping("laboratorios/{idLaboratorio}/establecimientos")
    public String crearEstablecimiento(@PathVariable("idLaboratorio") String idLaboratorio,
                                       @Valid Establecimiento establecimiento, Errors errores,
                                       Model model) {
        System.out.println("Alguien está guardando un nuevo establecimiento");

        if(errores.hasErrors()){
            return "laboratorio_detalle";
        }

        Optional<Laboratorio> laboratorioOptional = laboratoriosDAO.findById(Long.parseLong(idLaboratorio));

        if(laboratorioOptional.isPresent()){
            establecimiento.setId(null);
            Laboratorio laboratorio = laboratorioOptional.get();
            laboratorio.agregarEstablecimiento(establecimiento);
            establecimientosDAO.save(establecimiento);
            laboratoriosDAO.save(laboratorio);
        }
        else{
            return "not_found_error";
        }

        return "redirect:/laboratorios";
    }

}
