package com.klarios.sistemaweb.repositories;

import com.klarios.sistemaweb.models.Sala;
import com.klarios.sistemaweb.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosDAO  extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);
}
