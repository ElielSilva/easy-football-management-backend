package br.edu.ifpe.easy_football_management_backend.Infrestructure.Security;

import br.edu.ifpe.easy_football_management_backend.Domain.Entity.User;
import br.edu.ifpe.easy_football_management_backend.Infrestructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.repository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}