package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Division;
import com.klarios.sistemaweb.models.Laboratorio;
import com.klarios.sistemaweb.models.enums.TipoDivision;
import com.klarios.sistemaweb.repositories.LaboratoriosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LaboratoriosController {

    @Autowired
    public LaboratoriosDAO laboratoriosDAO;

    @GetMapping("laboratorios")
    public String getLaboratorios(Model model) {

        System.out.println("Se solicitaron los laboratorios");

        List<Laboratorio> laboratorios = laboratoriosDAO.findAll();
        model.addAttribute("laboratorios", laboratorios);

        return "laboratorios";
    }

    @GetMapping("laboratorios/{id}")
    public String getLaboratorio(Laboratorio laboratorio, Division division, Model model) {
        System.out.println("Alguien pidió el detalle de un laboratorio");

        Optional<Laboratorio> laboratorioOptional = laboratoriosDAO.findById(laboratorio.getId());

        if(laboratorioOptional.isPresent()){
            laboratorio = laboratorioOptional.get();
            model.addAttribute("laboratorio",laboratorio);
            model.addAttribute("division",division);
            model.addAttribute("tipos", TipoDivision.values());
            return "laboratorio_detalle";
        }
        else{
            return "not_found_error";
        }
    }

    @GetMapping("laboratorios/nuevo")
    public String getFormNuevoLaboratorio(Laboratorio laboratorio, Model model) {

        System.out.println("Se solicitó el formulario para crear un nuevo laboratorio");

        return "form_nuevo_laboratorio";
    }

    @PostMapping("laboratorios")
    public String crearLaboratorio(@Valid Laboratorio laboratorio, Errors errores, Model model) {
        System.out.println("Alguien está guardando un nuevo laboratorio");

        if(errores.hasErrors()){
            return "form_nuevo_laboratorio";
        }

        laboratoriosDAO.save(laboratorio);
        return "redirect:/laboratorios";
    }

}
