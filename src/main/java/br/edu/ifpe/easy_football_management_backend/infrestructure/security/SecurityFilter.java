package br.edu.ifpe.easy_football_management_backend.infrestructure.security;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.NotFoundException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import br.edu.ifpe.easy_football_management_backend.features.auth.LoginUserQuery;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(
           HttpServletRequest request,
           HttpServletResponse response,
           FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null && !token.isBlank()) {
            var login = tokenService.validateToken(token);
            if (login != null) {
                var user = userRepository.findById(UUID.fromString(login))
                        .orElseThrow(() -> new NotFoundException("User Not Found"));
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}