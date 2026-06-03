package com.pgi.pgic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pgi.pgic.entity.Utilisateur;
import com.pgi.pgic.repository.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Ajout de logs pour déboguer
        System.out.println("=== UserDetailsService: recherche de l'utilisateur avec email: " + email);
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec email: " + email));
        System.out.println("=== Utilisateur trouvé: " + utilisateur.getEmail() + ", actif=" + utilisateur.isActif());
        System.out.println("=== Mot de passe stocké (BCrypt ou clair) : " + utilisateur.getMotDePasse());
        return new UserPrincipal(utilisateur);
    }
}