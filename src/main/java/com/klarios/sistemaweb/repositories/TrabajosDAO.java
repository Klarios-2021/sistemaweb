package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.Equipo;
import com.klarios.sistemaweb.models.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajosDAO extends JpaRepository<Trabajo,Long> {
}
