package br.com.takeaguide.takeaguide.dtos.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "change_reservation_status_request", description = "Request containing the data needed to change the status of a reservation")
public class ChangeReservationStatusRequest {

    @Schema(name = "reservation_id", description = "Unique identifier for the reservation", required = true)
    @JsonProperty("reservation_id")
    private String reservationId;

    @Schema(name = "status", description = "New status for the reservation", required = true)
    @JsonProperty("status")
    private String status;

    public ChangeReservationStatusRequest() {}

    public ChangeReservationStatusRequest(String reservationId, String status) {
        this.reservationId = reservationId;
        this.status = status;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
