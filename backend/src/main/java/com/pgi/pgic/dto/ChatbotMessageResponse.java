package com.pgi.pgic.dto;

import lombok.Data;

@Data
public class ChatbotMessageResponse {
    private Long sessionId;
    private String response;
    private boolean intendToRedirect;
    private String redirectUrl;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isIntendToRedirect() {
        return intendToRedirect;
    }

    public void setIntendToRedirect(boolean intendToRedirect) {
        this.intendToRedirect = intendToRedirect;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}