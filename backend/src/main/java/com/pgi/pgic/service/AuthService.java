package com.pgi.pgic.service;

import com.pgi.pgic.dto.LoginRequest;
import com.pgi.pgic.dto.LoginResponse;
import com.pgi.pgic.dto.RegisterRequest;
import com.pgi.pgic.entity.Client;
import com.pgi.pgic.entity.Role;
import com.pgi.pgic.entity.Utilisateur;
import com.pgi.pgic.repository.UtilisateurRepository;
import com.pgi.pgic.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.pgi.pgic.dto.ChangePasswordRequest;
import com.pgi.pgic.security.JwtUtils;
import com.pgi.pgic.security.UserPrincipal;
@Service
public class AuthService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return new LoginResponse(jwt, userPrincipal.getId(), userPrincipal.getEmail(), userPrincipal.getRole().name());
    }

    public void register(RegisterRequest request) {
        if (utilisateurRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        Client user = new Client();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(request.getPassword())); // hachage
        user.setRole(Role.CLIENT); // par défaut, ou selon votre besoin
        utilisateurRepository.save(user);
    }

    public void changePassword(Long userId, ChangePasswordRequest request) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getMotDePasse())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mot de passe actuel incorrect");
        }
        user.setMotDePasse(passwordEncoder.encode(request.getNewPassword()));
        utilisateurRepository.save(user);
    }
}