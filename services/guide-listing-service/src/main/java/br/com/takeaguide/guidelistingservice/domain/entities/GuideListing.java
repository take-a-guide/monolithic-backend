package br.com.takeaguide.guidelistingservice.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import br.com.takeaguide.guidelistingservice.dtos.ChangeGuideListingRequest;

public class GuideListing {

    private String id;
    private String cpf;
    private String title;
    private String description;
    private String location;
    private List<String> tours;
    private List<String> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GuideListing() {}

    public GuideListing(String id, String cpf, String title, String description, String location,
                        List<String> tours, List<String> categories, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.cpf = cpf;
        this.title = title;
        this.description = description;
        this.location = location;
        this.tours = tours;
        this.categories = categories;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTours() {
        return tours;
    }

    public void setTours(List<String> tours) {
        this.tours = tours;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateFromRequest(ChangeGuideListingRequest request) {
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }
        if (request.getDescription() != null) {
            this.description = request.getDescription();
        }
        if (request.getLocation() != null) {
            this.location = request.getLocation();
        }
        if (request.getTours() != null) {
            this.tours = request.getTours();
        }
        if (request.getCategories() != null) {
            this.categories = request.getCategories();
        }
        this.updatedAt = LocalDateTime.now();
    }
}