package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.models.Contacto;
import com.klarios.sistemaweb.models.Laboratorio;
import com.klarios.sistemaweb.repositories.ContactosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactosController {

    @Autowired
    ContactosDAO contactosDAO;

    @GetMapping("contactos")
    public String getContactos(Model model) {

        System.out.println("Se solicitaron los contactos");

        List<Contacto> contactos = contactosDAO.findAll();
        model.addAttribute("contactos", contactos);

        return "contactos";
    }
    @GetMapping("contactos/{id}")
    public String getContacto(Contacto contacto, Model model) {
        System.out.println("Alguien pidió el detalle de un contacto");

        Optional<Contacto> contactoOptional = contactosDAO.findById(contacto.getId());

        if(contactoOptional.isPresent()){
            contacto = contactoOptional.get();
            model.addAttribute("contacto",contacto);
            return "contacto_detalle";
        }
        else{
            return "not_found_error";
        }
    }
}
