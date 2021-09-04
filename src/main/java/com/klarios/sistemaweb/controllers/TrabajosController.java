package com.klarios.sistemaweb.controllers;

import com.klarios.sistemaweb.filters.FiltroTrabajos;
import com.klarios.sistemaweb.forms.EnsayosSala;
import com.klarios.sistemaweb.forms.TrabajoForm;
import com.klarios.sistemaweb.models.*;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import com.klarios.sistemaweb.repositories.EnsayosDAO;
import com.klarios.sistemaweb.repositories.SectoresDAO;
import com.klarios.sistemaweb.repositories.TrabajosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TrabajosController {

    @Autowired
    SectoresDAO sectoresDAO;

    @Autowired
    TrabajosDAO trabajosDAO;

    @Autowired
    EnsayosDAO ensayosDao;

    @GetMapping("/trabajos")
    public String getTrabajos(FiltroTrabajos filtro, Model model) {

        System.out.println("Se solicitó el listado de trabajos");

        List<Trabajo> trabajos = trabajosDAO.findAll();

        List<Trabajo> trabajosFiltrados = filtro.filtrarTrabajos(trabajos);

        model.addAttribute("trabajos", trabajosFiltrados);
        model.addAttribute("filtro", filtro);

        return "trabajos";
    }
    @GetMapping("/trabajos/{idTrabajo}")
    public String getTrabajo(@PathVariable("idTrabajo") String idTrabajo, Model model) {

        System.out.println("Se solicitó un trabajo");

        Optional<Trabajo> trabajoOptional = trabajosDAO.findById(Long.parseLong(idTrabajo));

        if(trabajoOptional.isPresent()){
            Trabajo trabajo = trabajoOptional.get();
            model.addAttribute("trabajo",trabajo);
            return "trabajo_detalle";
        }
        else{
            return "not_found_error";
        }
    }

    @GetMapping("/laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/trabajos/nuevo")
    public String getFormNuevoTrabajo(@PathVariable("idLaboratorio") String idLaboratorio,
                                      @PathVariable("idEstablecimiento") String idEstablecimiento,
                                      @PathVariable("idSector") String idSector,
                                      TrabajoForm trabajoForm,
                                      Model model) {

        System.out.println("Se solicitó el formulario para crear un nuevo trabajo");

        Optional<Sector> sectorOptional = sectoresDAO.findById(Long.parseLong(idSector));

        if(sectorOptional.isPresent()){
            model.addAttribute("sector",sectorOptional.get());
            model.addAttribute("trabajoForm", trabajoForm);
            return "form_nuevo_trabajo";
        }
        else{
            return "not_found_error";
        }

    }

    @PostMapping("/laboratorios/{idLaboratorio}/establecimientos/{idEstablecimiento}/sectores/{idSector}/trabajos")
    public String agregarNuevoTrabajo(@PathVariable("idSector") String idSector,
                                      TrabajoForm trabajoForm,
                                      Model model) {

        System.out.println("Se está guardando un nuevo trabajo");

        Trabajo nuevoTrabajo = new Trabajo();
        List<EnsayosSala> ensayosARealizarEnSalas = trabajoForm.getEnsayosXsala();

        nuevoTrabajo.setFecha(LocalDate.now());

        ensayosARealizarEnSalas.forEach(ensayosSala -> {
            ensayosSala.getEnsayos().forEach(ensayo -> {
                ensayo.setMaterial(ensayosSala.getSala());
                ensayo.configurarValoresComparacion(trabajoForm.valoresComparacion);
                nuevoTrabajo.agregarEnsayo(ensayo);
                ensayosDao.save(ensayo);
            });
            ensayosSala.getEnsayoXequipo().forEach(ensayosEquipo -> {
                ensayosEquipo.getEnsayos().forEach(ensayo -> {
                    ensayo.setMaterial(ensayosEquipo.getEquipo());
                    ensayo.configurarValoresComparacion(trabajoForm.valoresComparacion);
                    nuevoTrabajo.agregarEnsayo(ensayo);
                    ensayosDao.save(ensayo);
                        });
            });
        });

        trabajosDAO.save(nuevoTrabajo);

        return "redirect:";

    }
}
