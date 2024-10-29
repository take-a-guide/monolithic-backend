package br.com.takeaguide.takeaguide.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Guide {

    private String cpf;
    private String education;
    private String description;
    private String location;
    private boolean isVerified;
    private List<String> availableDates;
    private List<String> tours;
    private List<String> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Guide() {}

    public Guide(String cpf, String education, String description, String location, boolean isVerified, 
                 List<String> availableDates, List<String> tours, List<String> categories, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.cpf = cpf;
        this.education = education;
        this.description = description;
        this.location = location;
        this.isVerified = isVerified;
        this.availableDates = availableDates;
        this.tours = tours;
        this.categories = categories;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public List<String> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<String> availableDates) {
        this.availableDates = availableDates;
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
}
