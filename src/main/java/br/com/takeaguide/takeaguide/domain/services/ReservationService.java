package br.com.takeaguide.takeaguide.domain.services;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.reservation.CreateReservationRequest;
import br.com.takeaguide.takeaguide.dtos.reservation.ChangeReservationStatusRequest;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    ResponseEntity<ResponseObject> createReservation(CreateReservationRequest request);
    ResponseEntity<ResponseObject> changeReservationStatus(String reservationId, ChangeReservationStatusRequest request);
    ResponseEntity<ResponseObject> getReservationById(String id);
    ResponseEntity<ResponseObject> getAllReservations();
    ResponseEntity<ResponseObject> getReservationsByUserCpf(String userCpf);
    ResponseEntity<ResponseObject> getReservationsByGuideCpf(String guideCpf);
}
