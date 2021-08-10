package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.Ensayo;
import com.klarios.sistemaweb.models.VersionDatos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionDatosDAO extends JpaRepository<VersionDatos,Long> {
}
