package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Ensayo;
import com.klarios.sistemaweb.models.Laboratorio;
import com.klarios.sistemaweb.repositories.EnsayosDAO;
import com.klarios.sistemaweb.repositories.LaboratoriosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EnsayosController {

    @Autowired
    EnsayosDAO ensayosDAO;

    @Autowired
    LaboratoriosDAO laboratoriosDAO;

    @GetMapping("ensayos")
    public String getEnsayos(Model model) {

        System.out.println("Se solicitaron los ensayos");

        List<Ensayo> ensayos = ensayosDAO.findAll();
        model.addAttribute("ensayos", ensayos);

        return "ensayos";
    }

    @GetMapping("ensayos/nuevo")
    public String getFormNuevoEnsayo(Ensayo ensayo, Model model) {

        System.out.println("Se solicitó el formulario para crear un nuevo ensayo");

        List<Laboratorio> laboratorios = laboratoriosDAO.findAll();
        model.addAttribute("laboratorios", laboratorios);
        //model.addAttribute("laboratorioElegido", new Laboratorio());

        return "form_nuevo_ensayo";
    }

}
