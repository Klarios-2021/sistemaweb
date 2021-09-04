package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Establecimiento;
import com.klarios.sistemaweb.models.Laboratorio;
import com.klarios.sistemaweb.models.Sala;
import com.klarios.sistemaweb.models.Sector;
import com.klarios.sistemaweb.repositories.EstablecimientosDAO;
import com.klarios.sistemaweb.repositories.LaboratoriosDAO;
import com.klarios.sistemaweb.repositories.SectoresDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SectoresController {

    @Autowired
    public SectoresDAO sectoresDAO;

    @Autowired
    public EstablecimientosDAO establecimientosDAO;


    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}")
    public String getSector(@PathVariable("idLaboratorio") String idLaboratorio,
                            @PathVariable("idEstablecimiento") String idEstablecimiento,
                            @PathVariable("idSector") String idSector, Sala sala, Model model) {
        System.out.println("Alguien pidió el detalle de un sector");

        Optional<Sector> sectorOptional = sectoresDAO.findById(Long.parseLong(idSector));

        if (sectorOptional.isPresent()) {
            model.addAttribute("sector", sectorOptional.get());
            model.addAttribute("sala", sala);
            model.addAttribute("idLaboratorio", idLaboratorio);
            model.addAttribute("idEstablecimiento", idEstablecimiento);
            return "sector_detalle";
        } else {
            return "not_found_error";
        }
    }

    @PostMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores")
    public String crearSector(@PathVariable("idEstablecimiento") String idEstablecimiento,
                              @Valid Sector sector, Errors errores, Model model) {

        System.out.println("Alguien está guardando un nuevo sector");

        if (errores.hasErrors()) {
            return "establecimiento_detalle";
        }

        Optional<Establecimiento> establecimientoOptional = establecimientosDAO.findById(Long.parseLong(idEstablecimiento));

        if (establecimientoOptional.isPresent()) {
            sector.setId(null);
            Establecimiento establecimiento = establecimientoOptional.get();
            establecimiento.agregarSector(sector);
            sectoresDAO.save(sector);
            establecimientosDAO.save(establecimiento);

        } else {
            return "not_found_error";
        }

        return "redirect:";
    }
}
