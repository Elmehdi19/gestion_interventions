package com.pgi.pgic.service;

import com.pgi.pgic.dto.UserRequest;
import com.pgi.pgic.dto.UserResponse;
import com.pgi.pgic.entity.*;
import com.pgi.pgic.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers() {
        return utilisateurRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        Utilisateur user = utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return toResponse(user);
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (utilisateurRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        Utilisateur user = createEntityFromRequest(request);
        user.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        user.setActif(true);
        user.setDateInscription(java.time.LocalDateTime.now());
        Utilisateur saved = utilisateurRepository.save(user);
        return toResponse(saved);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        Utilisateur existing = utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        // Mise à jour des champs communs
        existing.setNom(request.getNom());
        existing.setPrenom(request.getPrenom());
        existing.setEmail(request.getEmail());
        existing.setTelephone(request.getTelephone());
        existing.setAdresse(request.getAdresse());
        if (request.getMotDePasse() != null && !request.getMotDePasse().isEmpty()) {
            existing.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        }
        // Gestion des champs spécifiques selon le rôle (à adapter)
        if (existing instanceof Ordonnanceur && request.getSpecialite() != null) {
            ((Ordonnanceur) existing).setSpecialite(request.getSpecialite());
        } else if (existing instanceof Client && request.getNumeroContrat() != null) {
            ((Client) existing).setNumeroContrat(request.getNumeroContrat());
        }
        Utilisateur updated = utilisateurRepository.save(existing);
        return toResponse(updated);
    }

    @Transactional
    public void deleteUser(Long id) {
        Utilisateur user = utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        utilisateurRepository.delete(user);
    }

    private Utilisateur createEntityFromRequest(UserRequest request) {
        switch (request.getRole()) {
            case ADMIN: return new Admin();
            case CLIENT:
                Client client = new Client();
                client.setNumeroContrat(request.getNumeroContrat());
                return client;
            case ORDONNANCEUR_ELECTRICITE:
            case ORDONNANCEUR_EAU_ASSAINISSEMENT:
                Ordonnanceur ord = new Ordonnanceur();
                ord.setSpecialite(request.getSpecialite());
                return ord;
            case CHEF_EQUIPE: return new ChefEquipe();
            case TECHNICIEN: return new Technicien();
            default: throw new RuntimeException("Rôle non supporté");
        }
    }

    private UserResponse toResponse(Utilisateur u) {
        UserResponse resp = new UserResponse();
        resp.setId(u.getId());
        resp.setNom(u.getNom());
        resp.setPrenom(u.getPrenom());
        resp.setEmail(u.getEmail());
        resp.setTelephone(u.getTelephone());
        resp.setAdresse(u.getAdresse());
        resp.setActif(u.isActif());
        resp.setRole(u.getRole()); // nécessite d'avoir un champ role dans Utilisateur
        resp.setDateInscription(u.getDateInscription());
        if (u instanceof Ordonnanceur) resp.setSpecialite(((Ordonnanceur) u).getSpecialite());
        if (u instanceof Client) resp.setNumeroContrat(((Client) u).getNumeroContrat());
        return resp;
    }
}