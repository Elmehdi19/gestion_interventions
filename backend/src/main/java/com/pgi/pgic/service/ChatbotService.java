package com.pgi.pgic.service;

import com.pgi.pgic.dto.ChatbotMessageRequest;
import com.pgi.pgic.dto.ChatbotMessageResponse;
import com.pgi.pgic.entity.ChatbotSession;
import com.pgi.pgic.entity.Client;
import com.pgi.pgic.repository.ChatbotSessionRepository;
import com.pgi.pgic.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotSessionRepository sessionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public ChatbotMessageResponse processMessage(ChatbotMessageRequest request) {
        ChatbotSession session = null;
        if (request.getSessionId() != null) {
            Optional<ChatbotSession> opt = sessionRepository.findById(request.getSessionId());
            if (opt.isPresent()) {
                session = opt.get();
            }
        }

        if (session == null) {
            ChatbotSession newSession = new ChatbotSession();
            newSession.setDateDebut(LocalDateTime.now());
            newSession.setResolu(false);
            newSession.setMessagesJson("[]");
            if (request.getClientId() != null) {
                utilisateurRepository.findById(request.getClientId()).ifPresent(user -> {
                    if (user instanceof Client) {
                        newSession.setClient((Client) user);
                    }
                });
            }
            session = sessionRepository.save(newSession);
        }

        String message = request.getMessage().toLowerCase();
        String responseText;
        boolean redirect = false;
        String redirectUrl = null;

        if (message.contains("reclamation") || message.contains("probleme") || message.contains("panne")) {
            responseText = "Pour déposer une réclamation, veuillez décrire le problème, l'adresse et l'urgence.";
        } else if (message.contains("statut") || message.contains("suivi")) {
            responseText = "Veuillez fournir le numéro de votre réclamation.";
            redirect = true;
            redirectUrl = "/client/reclamations/suivi";
        } else if (message.contains("facture") || message.contains("payer")) {
            responseText = "Consultez vos factures dans votre espace client.";
            redirect = true;
            redirectUrl = "/client/factures";
        } else if (message.contains("horaires") || message.contains("contact")) {
            responseText = "Nos services sont disponibles 8h-20h. Contact : 0xxx xx xx xx";
        } else {
            responseText = "Je n'ai pas compris. Veuillez reformuler.";
        }

        String newEntry = "{\"msg\":\"" + request.getMessage() + "\", \"resp\":\"" + responseText + "\"}";
        String old = session.getMessagesJson();
        if (old == null || old.equals("[]")) {
            session.setMessagesJson("[" + newEntry + "]");
        } else {
            session.setMessagesJson(old.substring(0, old.length() - 1) + "," + newEntry + "]");
        }
        session.setDateFin(LocalDateTime.now());
        sessionRepository.save(session);
        
        ChatbotMessageResponse resp = new ChatbotMessageResponse();
        resp.setSessionId(session.getId());
        resp.setResponse(responseText);
        resp.setIntendToRedirect(redirect);
        resp.setRedirectUrl(redirectUrl);
        return resp;
    }
}