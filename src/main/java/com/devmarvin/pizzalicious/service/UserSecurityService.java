package com.devmarvin.pizzalicious.service;

import com.devmarvin.pizzalicious.persistence.entity.UserEntity;
import com.devmarvin.pizzalicious.persistence.entity.UserRoleEntity;
import com.devmarvin.pizzalicious.persistence.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    private  final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Vamos a buscar un UserEntity
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con username: " + username));

        String [] roles = userEntity.getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .toArray(String[]::new);


        //Si lo encontramos el usuario va seguir la implementación normal
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }

    //Método que retorna un array de Strings con los permisos específicos
    private String [] getAuthorities(String role){ //Recibe un rol puntual
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[]{"RANDOM_ORDER"};
        }

        return new String[]{};
    }

    //Este método va a recibir los roles que tiene el usuario y va a retornar una lista de GrantedAuthority
    private List<GrantedAuthority> grantedAuthorities(String[] roles) {

        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority : this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }

}


