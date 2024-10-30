package br.com.takeaguide.reservationservice.domain.services;

import br.com.takeaguide.reservationservice.dtos.ResponseObject;
import br.com.takeaguide.reservationservice.dtos.CreateReservationRequest;
import br.com.takeaguide.reservationservice.dtos.ChangeReservationStatusRequest;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    ResponseEntity<ResponseObject> createReservation(CreateReservationRequest request);
    ResponseEntity<ResponseObject> changeReservationStatus(String reservationId, ChangeReservationStatusRequest request);
    ResponseEntity<ResponseObject> getReservationById(String id);
    ResponseEntity<ResponseObject> getAllReservations();
    ResponseEntity<ResponseObject> getReservationsByUserCpf(String userCpf);
    ResponseEntity<ResponseObject> getReservationsByGuideCpf(String guideCpf);
}
