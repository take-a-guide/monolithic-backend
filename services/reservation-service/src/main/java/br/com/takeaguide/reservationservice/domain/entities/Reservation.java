package br.com.takeaguide.reservationservice.domain.entities;

import java.time.LocalDateTime;

public class Reservation {

    private String id;
    private String guideCpf;
    private String userCpf;
    private LocalDateTime reservationDate;
    private String tourName;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Reservation() {}

    public Reservation(String id, String guideCpf, String userCpf, LocalDateTime reservationDate, 
                       String tourName, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.guideCpf = guideCpf;
        this.userCpf = userCpf;
        this.reservationDate = reservationDate;
        this.tourName = tourName;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuideCpf() {
        return guideCpf;
    }

    public void setGuideCpf(String guideCpf) {
        this.guideCpf = guideCpf;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
