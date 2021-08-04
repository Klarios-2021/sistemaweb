package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Laboratorio;
import com.klarios.sistemaweb.repositories.LaboratoriosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

}
