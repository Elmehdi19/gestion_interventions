package com.pgi.pgic.service;

import com.pgi.pgic.dto.ReclamationRequest;
import com.pgi.pgic.dto.ReclamationResponse;
import com.pgi.pgic.dto.ReclamationStatutResponse;
import com.pgi.pgic.entity.Client;
import com.pgi.pgic.entity.Reclamation;
import com.pgi.pgic.entity.Role;
import com.pgi.pgic.entity.Utilisateur;
import com.pgi.pgic.repository.ReclamationRepository;
import com.pgi.pgic.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public ReclamationResponse creerReclamation(ReclamationRequest request, Long clientId) {
        Client client = (Client) utilisateurRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Reclamation reclamation = new Reclamation();
        reclamation.setDescription(request.getDescription());
        reclamation.setUrgente(request.isUrgente());
        reclamation.setPiecesJointes(request.getPiecesJointes());
        reclamation.setStatut(Reclamation.StatutReclamation.NOUVELLE);
        reclamation.setDateCreation(LocalDateTime.now());
        reclamation.setClient(client);

        if (request.getSpecialiteConcerne() != null) {
            reclamation.setSpecialiteConcernee(request.getSpecialiteConcerne());
        }

        String annee = String.valueOf(Year.now().getValue());
        long count = reclamationRepository.count() + 1;
        String numero = String.format("REC-%s-%05d", annee, count);
        reclamation.setNumero(numero);

        Reclamation saved = reclamationRepository.save(reclamation);

        // --- Notifier les ordonnanceurs concernés ---
if (saved.getSpecialiteConcernee() != null) {
    Role ordRole = null;
    if ("ELECTRICITE".equals(saved.getSpecialiteConcernee().name())) {
        ordRole = Role.ORDONNANCEUR_ELECTRICITE;
    } else if ("EAU_ASSAINISSEMENT".equals(saved.getSpecialiteConcernee().name())) {
        ordRole = Role.ORDONNANCEUR_EAU_ASSAINISSEMENT;
    }
    if (ordRole != null) {
        List<Utilisateur> ordonnanceurs = utilisateurRepository.findByRole(ordRole);
        for (Utilisateur u : ordonnanceurs) {
            notificationService.createNotification(
                    u.getId(),
                    "Nouvelle réclamation " + saved.getNumero() + " (" + saved.getSpecialiteConcernee() + ")",
                    "RECLAMATION",
                    "/ordonnanceur/claims"   // lien vers la liste des réclamations
            );
        }
    }
}
// --- Fin notification ---}    


        return new ReclamationResponse(
                saved.getId(),
                saved.getNumero(),
                saved.getDescription(),
                saved.isUrgente(),
                saved.getStatut().name(),
                saved.getDateCreation()
        );
    }

    public ReclamationStatutResponse getStatutParNumero(String numero) {
        Reclamation reclamation = reclamationRepository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
        String interventionNumero = (reclamation.getIntervention() != null) ? reclamation.getIntervention().getNumero() : null;
        return new ReclamationStatutResponse(
                reclamation.getNumero(),
                reclamation.getStatut().name(),
                reclamation.getDescription(),
                reclamation.getDateCreation(),
                interventionNumero
        );
    }

    public List<ReclamationResponse> getReclamationsByClient(Long clientId) {
        Client client = (Client) utilisateurRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        List<Reclamation> reclamations = reclamationRepository.findByClientOrderByDateCreationDesc(client);
        return reclamations.stream()
                .map(r -> new ReclamationResponse(
                        r.getId(),
                        r.getNumero(),
                        r.getDescription(),
                        r.isUrgente(),
                        r.getStatut().name(),
                        r.getDateCreation()
                ))
                .collect(Collectors.toList());
    }
}