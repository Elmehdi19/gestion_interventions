package com.pgi.pgic.service;

import com.pgi.pgic.entity.Notification;
import com.pgi.pgic.entity.Utilisateur;
import com.pgi.pgic.repository.NotificationRepository;
import com.pgi.pgic.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByDateCreationDesc(userId);
    }

    public long getUnreadCount(Long userId) {
        return notificationRepository.countByUserIdAndLuFalse(userId);
    }

    @Transactional
    public void markAsRead(Long notificationId, Long userId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
        if (!notification.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès non autorisé");
        }
        notification.setLu(true);
        notificationRepository.save(notification);
    }

    /** Nouvelle méthode pour créer une notification */
    @Transactional
    public void createNotification(Long userId, String message, String type, String link) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setType(type);
        notification.setLink(link);
        notification.setLu(false);
        notification.setDateCreation(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}