package com.senai.apisasc.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.senai.apisasc.models.FuncionarioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(FuncionarioModel funcionario){

        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("api-vsconnect")
                    .withSubject(funcionario.getEmail())
                    .withExpiresAt(gerarValidadeToken())
                    // adicionar claim para adicionar nome ou outro coisa para o token
                    .withClaim("nomeUsuario", funcionario.getNome())
                    .sign(algoritimo);
            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na criação do token", exception);
        }
    }
    public String validarToken(String token) {
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);

            return JWT.require(algoritimo)
                    .withIssuer("api-sasc")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTCreationException exception){
            return "";
        }
    }

    public Instant gerarValidadeToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
