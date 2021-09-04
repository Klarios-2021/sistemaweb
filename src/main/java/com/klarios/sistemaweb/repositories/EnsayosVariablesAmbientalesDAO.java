package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariableAmbiental;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnsayosVariablesAmbientalesDAO extends JpaRepository<EnsayoVariableAmbiental,Long> {
}
