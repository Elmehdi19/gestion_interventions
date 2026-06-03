package com.pgi.pgic.repository;

import com.pgi.pgic.entity.ChatbotSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotSessionRepository extends JpaRepository<ChatbotSession, Long> {
}