package com.pgi.pgic.controller;

import com.pgi.pgic.dto.ReclamationRequest;
import com.pgi.pgic.dto.ReclamationResponse;
import com.pgi.pgic.dto.ReclamationStatutResponse;
import com.pgi.pgic.entity.Reclamation;
import com.pgi.pgic.security.UserPrincipal;
import com.pgi.pgic.service.ReclamationService;
import com.pgi.pgic.repository.ReclamationRepository;
import com.pgi.pgic.service.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired private ReclamationService reclamationService;
    @Autowired private ReclamationRepository reclamationRepository;
    @Autowired private FileUploadService fileUploadService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ReclamationResponse> creerReclamation(
            @Valid @RequestBody ReclamationRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        ReclamationResponse response = reclamationService.creerReclamation(request, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/statut")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ReclamationStatutResponse> getStatut(@PathVariable String id) {
        ReclamationStatutResponse response = reclamationService.getStatutParNumero(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<ReclamationResponse>> getMesReclamations(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(reclamationService.getReclamationsByClient(currentUser.getId()));
    }

    @PostMapping("/{id}/upload")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> uploadPieceJointe(@PathVariable Long id,
                                                    @RequestParam("file") MultipartFile file,
                                                    @AuthenticationPrincipal UserPrincipal currentUser) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
        if (!reclamation.getClient().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Vous n'êtes pas autorisé à modifier cette réclamation");
        }
        try {
            String filePath = fileUploadService.saveFile(file);
            reclamation.setPiecesJointes(filePath);
            reclamationRepository.save(reclamation);
            return ResponseEntity.ok("Fichier téléchargé avec succès");
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du téléchargement du fichier: " + e.getMessage());
        }
    }
}