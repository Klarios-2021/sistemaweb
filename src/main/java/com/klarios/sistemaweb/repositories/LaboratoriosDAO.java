package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.Contacto;
import com.klarios.sistemaweb.models.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoriosDAO extends JpaRepository<Laboratorio,Long> {
}
