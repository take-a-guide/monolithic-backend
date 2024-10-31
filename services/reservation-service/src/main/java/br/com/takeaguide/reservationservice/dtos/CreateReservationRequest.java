package br.com.takeaguide.reservationservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Schema(name = "create_reservation_request", description = "Request containing the data needed to create a new reservation")
public class CreateReservationRequest {

    @Schema(name = "guide_cpf", description = "CPF of the guide", required = true)
    @JsonProperty("guide_cpf")
    private String guideCpf;

    @Schema(name = "user_cpf", description = "CPF of the user making the reservation", required = true)
    @JsonProperty("user_cpf")
    private String userCpf;

    @Schema(name = "reservation_date", description = "Date and time of the reservation", required = true)
    @JsonProperty("reservation_date")
    private LocalDateTime reservationDate;

    @Schema(name = "tour_name", description = "Name of the tour", required = true)
    @JsonProperty("tour_name")
    private String tourName;

    public CreateReservationRequest() {}

    public CreateReservationRequest(String guideCpf, String userCpf, LocalDateTime reservationDate, String tourName) {
        this.guideCpf = guideCpf;
        this.userCpf = userCpf;
        this.reservationDate = reservationDate;
        this.tourName = tourName;
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
}
