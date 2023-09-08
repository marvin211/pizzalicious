package com.devmarvin.pizzalicious.web.controller;

import com.devmarvin.pizzalicious.service.dto.LoginDto;
import com.devmarvin.pizzalicious.web.config.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    //Método que nos permita iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()); //Creamos un objeto de tipo UsernamePasswordAuthenticationToken que es el que nos permite iniciar sesión

        //Autenticar el usuario
        Authentication authentication = this.authenticationManager.authenticate(login);
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        String jwt = this.jwtUtil.create(loginDto.getUsername()); //Creamos el jwt

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}

