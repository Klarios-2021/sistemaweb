package com.klarios.sistemaweb;

import com.klarios.sistemaweb.models.*;
import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariableAmbiental;
import com.klarios.sistemaweb.models.enums.EstadoEnsayo;
import com.klarios.sistemaweb.security.EncripcionPassword;
import com.klarios.sistemaweb.security.Rol;
import com.klarios.sistemaweb.security.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class DataLoader {

    public static void main(String[] args) {


        Contacto contacto = new Contacto();
        contacto.setNombre("Gonzalo");
        contacto.setApellido("Avila");
        contacto.setEmail("goner96@yahoo.com.ar");
        contacto.setTelefono("1134993753");
        contacto.setRol("Tester");

        Equipo equipo = new Equipo();
        equipo.setNombre("Rotopercutor");
        equipo.setTag("RPT351");
        equipo.setDimension(new Dimension(1.0,1.0,1.0));
        equipo.setMarca("Patito");
        equipo.setModelo("BMW500");
        //equipo.setEnsayos(List.of(ensayo));

        Ensayo ensayo = new EnsayoVariableAmbiental();
        ensayo.setFechaRealizacion(LocalDate.now());
        ensayo.setRealizo("Juan Perez");
        ensayo.setFechaControl(LocalDate.now());
        ensayo.setControlo("Martin Lopez");
        ensayo.setProtocolo("V500124");
        //ensayo.setVersionesDatos(List.of(versionDatosVieja,versionDatosActual));
        ensayo.setMaterial(equipo);
        ensayo.setEstado(EstadoEnsayo.DESAPROBADO);

        Sala sala = new Sala();
        sala.setNombre("Sala 1");
        sala.setTag("UX-25");
        sala.setDimension(new Dimension(3.0,4.0,5.0));
        sala.setUma("UMA BUENO");
        sala.setEquipos(List.of(equipo));

        Sector sector = new Sector();
        sector.setNombre("Sector 1");
        sector.setSalas(List.of(sala));

        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setNombre("Establecimiento 1");
        establecimiento.setDireccion("Av. Mortadela 1925");
        establecimiento.setSectores(List.of(sector));

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
        em.persist(ensayo);
        em.persist(equipo);
        em.persist(sala);
        em.persist(contacto);
        em.persist(sector);
        em.persist(establecimiento);
        em.persist(laboratorio);
        em.persist(usuario);
        em.getTransaction().commit();

    }


}
