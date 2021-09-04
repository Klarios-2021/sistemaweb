package com.klarios.sistemaweb.repositories;
import com.klarios.sistemaweb.models.Material;
import com.klarios.sistemaweb.models.ensayos.mediciones.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicionesDAO extends JpaRepository<Medicion,Long> {
}
