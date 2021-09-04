package com.klarios.sistemaweb.controllers;

import org.springframework.validation.BindingResult;

import java.util.Optional;

public class ControllerValidacionesGenerales {
    public static void validarFormulario(Optional optional, BindingResult bindingResult, String templateRedireccion) throws Exception {
        if(!optional.isPresent()){
            throw new Exception("not_found_error");
        }
        if(bindingResult.hasErrors()){
            throw new Exception(templateRedireccion);
        }
    }
}
