package br.edu.ifpe.easy_football_management_backend.infrestructure.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorDetails.put("error", "Unauthorized");

        String message = "Autenticação necessária ou falha na autenticação.";
        
        Throwable cause = authException.getCause();
        if (cause instanceof TokenExpiredException) {
            message = "Token JWT expirado. Por favor, faça login novamente.";
        } else if (cause instanceof JWTVerificationException) {
            message = "Token JWT inválido: " + cause.getMessage();
        } else if (authException.getMessage() != null && !authException.getMessage().isEmpty()) {
            message = authException.getMessage();
        }

        errorDetails.put("message", message);
        response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
    }
}
