package br.com.takeaguide.takeaguide.domain.entities;

import java.time.LocalDateTime;

public class Ad {

    private String cpf;
    private String adContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public Ad(String cpf, String adContent) {
        this.cpf = cpf;
        this.adContent = adContent;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    
    public void markAsDeleted() {
        this.deletedAt = LocalDateTime.now();
    }


    public boolean isValid() {
        return cpf != null && !cpf.isEmpty() && adContent != null && !adContent.isEmpty();
    }
}
