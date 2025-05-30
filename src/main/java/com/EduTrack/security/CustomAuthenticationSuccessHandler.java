package com.EduTrack.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String userEmail = authentication.getName();

        String jsonResponse = new ObjectMapper().writeValueAsString(Map.of(
                "message", "Login exitoso",
                "user", userEmail,
                "roles", roles
        ));

        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
