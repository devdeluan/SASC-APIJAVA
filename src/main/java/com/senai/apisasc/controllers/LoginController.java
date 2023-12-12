package com.senai.apisasc.controllers;

import com.senai.apisasc.dtos.TokenDto;
import com.senai.apisasc.models.FuncionarioModel;
import com.senai.apisasc.services.TokenService;
import com.senai.apisasc.dtos.LoginDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController; // Adicione esta importação

@RestController // Adicione esta anotação para marcar a classe como um controlador Spring
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto dados) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        var auth = authenticationManager.authenticate(userNamePassword);

        var token = tokenService.gerarToken((FuncionarioModel) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
    }
}
