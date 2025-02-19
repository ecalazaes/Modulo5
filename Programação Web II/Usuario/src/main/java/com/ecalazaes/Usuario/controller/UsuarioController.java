package com.ecalazaes.Usuario.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario") // Define o caminho base para este controlador
public class UsuarioController {

    @GetMapping
    public String getUsuario() {
        return "Olá Usuário";
    }
}
