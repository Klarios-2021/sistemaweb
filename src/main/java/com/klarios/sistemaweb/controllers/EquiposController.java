package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Equipo;
import com.klarios.sistemaweb.models.Sala;
import com.klarios.sistemaweb.models.Sector;
import com.klarios.sistemaweb.repositories.EquiposDAO;
import com.klarios.sistemaweb.repositories.SalasDAO;
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
public class EquiposController {

    @Autowired
    public EquiposDAO equiposDAO;

    @Autowired
    public SalasDAO salasDAO;

    @GetMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/equipos/{idEquipo}")
    public String getEquipo(@PathVariable("idLaboratorio") String idLaboratorio,
                            @PathVariable("idEstablecimiento") String idEstablecimiento,
                            @PathVariable("idSector") String idSector,
                            @PathVariable("idSala") String idSala,
                            @PathVariable("idEquipo") String idEquipo,
                            Equipo equipo, Model model) {
        System.out.println("Alguien pidió el detalle de un equipo");

        Optional<Equipo> equipoOptional = equiposDAO.findById(Long.parseLong(idEquipo));

        if(equipoOptional.isPresent()){
            equipo = equipoOptional.get();
            model.addAttribute("idLaboratorio", idLaboratorio);
            model.addAttribute("idEstablecimiento",idEstablecimiento);
            model.addAttribute("idSector",idSector);
            model.addAttribute("idSala",idSala);
            model.addAttribute("equipo", equipo);
            return "equipo_detalle";
        }
        else{
            return "not_found_error";
        }
    }

    @PostMapping("laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/salas/{idSala}/equipos")
    public String crearEquipo(@PathVariable("idSala") String idSala,
                              @Valid Equipo equipo, Errors errores, Model model) {

        System.out.println("Alguien está guardando un nuevo equipo");

        if(errores.hasErrors()){
            return "sala_detalle";
        }

        Optional<Sala> salaOptional = salasDAO.findById(Long.parseLong(idSala));

        if(salaOptional.isPresent()){
            equipo.setId(null);
            Sala sala = salaOptional.get();
            sala.agregarEquipo(equipo);
            equiposDAO.save(equipo);
            salasDAO.save(sala);
        }
        else{
            return "not_found_error";
        }

        return "redirect:";
    }

}
