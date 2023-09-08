package com.devmarvin.pizzalicious.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String secretKey;
    private Algorithm algorithm;

    @PostConstruct //Este método se debe ejecutar después de que se haya creado el bean
    public void init() { //está destinado a inicializar la variable algorithm con el algoritmo adecuado para firmar los tokens JWT. Además, al anotar el método con @PostConstruct, garantizas que este se ejecutará automáticamente después de que el bean de la clase JwtUtil se haya creado y todas las dependencias hayan sido inyectadas, lo cual asegura que el algoritmo se configure correctamente antes de que se utilice en otros métodos.
        this.algorithm = Algorithm.HMAC256(secretKey);
    }


    //Vamos a crear un método que nos permita crear un token
    public String create(String username) {
        // vamos a crear un jwt
        return JWT.create()
                .withSubject(username)
                .withIssuer("platzi-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
                .sign(algorithm);
    }

    //Validar un token
    public boolean isValidToken(String token) {

        //Controlar si un jwt es valido o no
        try {
            JWT.require(algorithm)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("El token no es valido");
            return false;
        }
    }

    //Obtener el usuario al cual pertenece un token
    public String getUsername(String token) {

        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }

}
