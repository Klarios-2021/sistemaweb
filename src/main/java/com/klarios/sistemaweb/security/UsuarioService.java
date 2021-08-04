package com.klarios.sistemaweb.security;

import com.klarios.sistemaweb.repositories.UsuariosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuariosDAO usuariosDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = usuariosDAO.findByUsername(username);

        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        for(Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority("ROLE_" + rol.toString()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
