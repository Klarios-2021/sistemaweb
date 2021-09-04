package com.klarios.sistemaweb.services;

import com.klarios.sistemaweb.models.*;
import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.repositories.*;
import com.klarios.sistemaweb.security.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataRepository {
    @Autowired
    ContactosDAO contactosDAO;
    @Autowired
    EnsayosDAO ensayosDAO;
    @Autowired
    EquiposDAO equiposDAO;
    @Autowired
    EstablecimientosDAO establecimientosDAO;
    @Autowired
    LaboratoriosDAO laboratoriosDAO;
    @Autowired
    MaterialesDAO materialesDAO;
    @Autowired
    SalasDAO salasDAO;
    @Autowired
    SectoresDAO sectoresDAO;
    @Autowired
    TrabajosDAO trabajosDAO;
    @Autowired
    UsuariosDAO usuariosDAO;

    public void guardarContacto(Contacto contacto){
        contactosDAO.save(contacto);
    }
    public void guardarEnsayo(Ensayo ensayo){
        ensayosDAO.save(ensayo);
    }
    public void guardarEquipo(Equipo equipo){
        equiposDAO.save(equipo);
    }
    public void guardarEstablecimiento(Establecimiento establecimiento){
        establecimientosDAO.save(establecimiento);
    }
    public void guardarLaboratorio(Laboratorio laboratorio){
        laboratoriosDAO.save(laboratorio);
    }
    public void guardarMaterial(Material material){
        materialesDAO.save(material);
    }
    public void guardarSala(Sala sala){
        salasDAO.save(sala);
    }
    public void guardarSector(Sector sector){
        sectoresDAO.save(sector);
    }
    public void guardarTrabajo(Trabajo trabajo){
        trabajosDAO.save(trabajo);
    }
    public void guardarUsuario(Usuario usuario){
        usuariosDAO.save(usuario);
    }
}
