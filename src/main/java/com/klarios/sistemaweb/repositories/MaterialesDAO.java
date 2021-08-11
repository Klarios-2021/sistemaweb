package com.klarios.sistemaweb.repositories;
import com.klarios.sistemaweb.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialesDAO extends JpaRepository<Material,Long> {
}
