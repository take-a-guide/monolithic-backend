package br.com.takeaguide.reservationservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Schema(name = "reservation_response", description = "Response object containing reservation details")
public class ReservationResponse {

    @Schema(name = "id", description = "Unique identifier of the reservation", required = true)
    @JsonProperty("id")
    private String id;

    @Schema(name = "guide_cpf", description = "CPF of the guide", required = true)
    @JsonProperty("guide_cpf")
    private String guideCpf;

    @Schema(name = "user_cpf", description = "CPF of the user who made the reservation", required = true)
    @JsonProperty("user_cpf")
    private String userCpf;

    @Schema(name = "reservation_date", description = "Date and time of the reservation", required = true)
    @JsonProperty("reservation_date")
    private LocalDateTime reservationDate;

    @Schema(name = "tour_name", description = "Name of the tour", required = true)
    @JsonProperty("tour_name")
    private String tourName;

    @Schema(name = "status", description = "Current status of the reservation", required = true)
    @JsonProperty("status")
    private String status;

    @Schema(name = "created_at", description = "Timestamp when the reservation was created", required = true)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Schema(name = "updated_at", description = "Timestamp when the reservation was last updated", required = true)
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public ReservationResponse() {}

    public ReservationResponse(String id, String guideCpf, String userCpf, LocalDateTime reservationDate, 
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

    // Getters and Setters

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
