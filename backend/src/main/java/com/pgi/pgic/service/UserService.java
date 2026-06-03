package com.pgi.pgic.service;

import com.pgi.pgic.dto.AuthResponse;
import com.pgi.pgic.dto.RegisterRequest;
import com.pgi.pgic.entity.Client;
import com.pgi.pgic.entity.Role;
import com.pgi.pgic.repository.ClientRepository;
import com.pgi.pgic.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pgi.pgic.security.UserPrincipal;

@Service
public class UserService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        // extraire userId et role depuis authentication principal (UserPrincipal)
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return new AuthResponse( jwt, principal.getId(), principal.getRole());
    }


    public AuthResponse register(RegisterRequest request) {
        // Vérifier si email existe déjà
        if (clientRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        Client client = new Client();
        client.setNom(request.getNom());
        client.setPrenom(request.getPrenom());
        client.setEmail(request.getEmail());
        client.setMotDePasse(passwordEncoder.encode(request.getPassword()));
        client.setTelephone(request.getTelephone());
        client.setAdresse(request.getAdresse());
        client.setNumeroContrat(request.getNumeroContrat());
        client.setRole(Role.CLIENT);
        client.setActif(true);
        Client saved = clientRepository.save(client);
        // Après inscription, on peut connecter automatiquement
        return login(request.getEmail(), request.getPassword());
    }
}