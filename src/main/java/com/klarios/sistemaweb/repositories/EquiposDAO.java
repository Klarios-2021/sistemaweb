package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquiposDAO extends JpaRepository<Equipo,Long> {
}
