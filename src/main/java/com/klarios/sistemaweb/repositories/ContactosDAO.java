package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactosDAO extends JpaRepository<Contacto,Long> {
}
