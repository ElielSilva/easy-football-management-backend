package br.edu.ifpe.easy_football_management_backend.infrestructure.security;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Role;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.repository.findById(UUID.fromString(name)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRole().stream().map(Role::name).map(org.springframework.security.core.authority.SimpleGrantedAuthority::new).toList()
        );
    }
}