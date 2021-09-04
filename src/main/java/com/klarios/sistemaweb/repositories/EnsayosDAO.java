package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.ensayos.Ensayo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnsayosDAO <T extends Ensayo> extends JpaRepository<T,Long> {
}
