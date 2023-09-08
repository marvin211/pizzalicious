package com.devmarvin.pizzalicious.persistence.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Obtener el usuario que esta realizando la inserción o modificación de la tabla pizza
@Service
public class AuditUsername implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        //Capturamos el usuario
        String username = authentication.getPrincipal().toString();

        return Optional.of(username);
    }


}
