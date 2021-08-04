package com.klarios.sistemaweb;

import com.klarios.sistemaweb.models.*;
import com.klarios.sistemaweb.models.enums.TipoDivision;
import com.klarios.sistemaweb.repositories.ContactosDAO;
import com.klarios.sistemaweb.security.EncripcionPassword;
import com.klarios.sistemaweb.security.Rol;
import com.klarios.sistemaweb.security.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class DataLoader {

    public static void main(String[] args) {

        Contacto contacto = new Contacto();
        contacto.setNombre("Gonzalo");
        contacto.setApellido("Avila");
        contacto.setEmail("goner96@yahoo.com.ar");
        contacto.setTelefono("1134993753");
        contacto.setRol("Tester");

        VersionDatos versionDatosVieja = new VersionDatos();
        versionDatosVieja.setFecha(LocalDateTime.now());
        versionDatosVieja.setObservaciones("Datos reales");
        HashMap<String,String> datosViejos = new HashMap<>();
        datosViejos.put("Medicion 1", "125");
        datosViejos.put("Medicion 2", "Mala");
        datosViejos.put("Medicion 3", "25.35");
        versionDatosVieja.setDatos(datosViejos);

        VersionDatos versionDatosActual = new VersionDatos();
        versionDatosActual.setFecha(LocalDateTime.now());
        versionDatosActual.setObservaciones("Datos retocados a solicitud del cliente");
        HashMap<String,String> datosNuevos = new HashMap<>();
        datosNuevos.put("Medicion 1", "500");
        datosNuevos.put("Medicion 2", "Buena");
        datosNuevos.put("Medicion 3", "25.35");
        versionDatosActual.setDatos(datosNuevos);

        Ensayo ensayo = new Ensayo();
        ensayo.setFechaRealizacion(LocalDateTime.now());
        ensayo.setRealizo("Juan Perez");
        ensayo.setFechaControl(LocalDateTime.now());
        ensayo.setControlo("Martin Lopez");
        ensayo.setProtocolo("V500124");
        ensayo.setVersionesDatos(List.of(versionDatosVieja,versionDatosActual));

        Equipo equipo = new Equipo();
        equipo.setNombre("Rotopercutor");
        equipo.setTag("RPT351");
        equipo.setDimension(new Dimension(1.0,1.0,1.0));
        equipo.setMarca("Patito");
        equipo.setModelo("BMW500");
        equipo.setEnsayos(List.of(ensayo));

        Sala sala = new Sala();
        sala.setNombre("Sala 1");
        sala.setTag("UX-25");
        sala.setDimension(new Dimension(3.0,4.0,5.0));
        sala.setUma("UMA BUENO");
        sala.setEquipos(List.of(equipo));

        Division division2 = new Division();
        division2.setNombre("Division secundaria");
        division2.setTipo(TipoDivision.AREA);
        division2.setSalas(List.of(sala));

        Division division1 = new Division();
        division1.setNombre("Division principal");
        division1.setTipo(TipoDivision.SECTOR);
        division1.setSubdivisiones(List.of(division2));

        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setNombre("Establecimiento 1");
        establecimiento.setDireccion("Av. Mortadela 1925");
        establecimiento.setDivisiones(List.of(division1));

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNombre("Sinopharm");
        laboratorio.setRazonSocial("Sinopharm SRL");
        laboratorio.setCuit("303412240944");
        laboratorio.setEmail("sinopharm@gmail.com");
        laboratorio.setTelefono("42223333");
        laboratorio.setWebsite("www.sinopharm.com");
        laboratorio.setContactos(List.of(contacto));
        laboratorio.setEstablecimientos(List.of(establecimiento));

        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword(EncripcionPassword.encriptar("123456"));
        usuario.setRoles(List.of(Rol.ADMIN, Rol.USER));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("klarios");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(versionDatosVieja);
        em.persist(versionDatosActual);
        em.persist(ensayo);
        em.persist(equipo);
        em.persist(sala);
        em.persist(contacto);
        em.persist(division2);
        em.persist(division1);
        em.persist(establecimiento);
        em.persist(laboratorio);
        em.persist(usuario);
        em.getTransaction().commit();

    }


}
