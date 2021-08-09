package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Equipo;
import com.klarios.sistemaweb.models.Establecimiento;
import com.klarios.sistemaweb.models.Sala;
import com.klarios.sistemaweb.models.Sector;
import com.klarios.sistemaweb.repositories.EstablecimientosDAO;
import com.klarios.sistemaweb.repositories.SalasDAO;
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
public class SalasController {

    @Autowired
    public SalasDAO salasDao;
    @Autowired
    public SectoresDAO sectoresDAO;

    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}")
    public String getSala(
            @PathVariable("idLaboratorio") String idLaboratorio,
            @PathVariable("idEstablecimiento") String idEstablecimiento,
            @PathVariable("idSector") String idSector,
            @PathVariable("idSala") String idSala, Equipo equipo, Model model) {
        System.out.println("Alguien pidió el detalle de una sala");

        Optional<Sala> salaOptional = salasDao.findById(Long.parseLong(idSala));

        if(salaOptional.isPresent()){
            model.addAttribute("sala", salaOptional.get());
            model.addAttribute("equipo", equipo);
            model.addAttribute("idLaboratorio", idLaboratorio);
            model.addAttribute("idEstablecimiento",idEstablecimiento);
            model.addAttribute("idSector",idSector);
            return "sala_detalle";
        }
        else{
            return "not_found_error";
        }
    }

    @PostMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas")
    public String crearSector(@PathVariable("idSector") String idSector,
                              @Valid Sala sala, Errors errores, Model model) {

        System.out.println("Alguien está guardando una nueva sala");

        if(errores.hasErrors()){
            return "sector_detalle";
        }

        Optional<Sector> sectorOptional = sectoresDAO.findById(Long.parseLong(idSector));

        if(sectorOptional.isPresent()){
            sala.setId(null);
            Sector sector = sectorOptional.get();
            sector.agregarSala(sala);
            salasDao.save(sala);
            sectoresDAO.save(sector);
        }
        else{
            return "not_found_error";
        }

        return "redirect:";
    }

}
