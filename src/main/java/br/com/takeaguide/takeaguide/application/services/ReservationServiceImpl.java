package br.com.takeaguide.takeaguide.application.services;

import br.com.takeaguide.takeaguide.domain.entities.Reservation;
import br.com.takeaguide.takeaguide.domain.repositories.ReservationRepository;
import br.com.takeaguide.takeaguide.domain.services.ReservationService;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.reservation.CreateReservationRequest;
import br.com.takeaguide.takeaguide.dtos.reservation.ChangeReservationStatusRequest;
import br.com.takeaguide.takeaguide.dtos.reservation.ReservationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createReservation(CreateReservationRequest request) {
        Reservation reservation = new Reservation(
                UUID.randomUUID().toString(),
                request.getGuideCpf(),
                request.getUserCpf(),
                request.getReservationDate(),
                request.getTourName(),
                "PENDING",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseObject.success("Reservation created successfully"));
    }

    @Override
    public ResponseEntity<ResponseObject> changeReservationStatus(String reservationId, ChangeReservationStatusRequest request) {
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.error("Reservation not found"));
        }
        reservation.setStatus(request.getStatus());
        reservation.setUpdatedAt(LocalDateTime.now());
        reservationRepository.update(reservation);
        return ResponseEntity.ok(ResponseObject.success("Reservation status updated successfully"));
    }

    @Override
    public ResponseEntity<ResponseObject> getReservationById(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.error("Reservation not found"));
        }
        ReservationResponse response = mapToResponse(reservation);
        return ResponseEntity.ok(ResponseObject.withData(response));
    }

    @Override
    public ResponseEntity<ResponseObject> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> responses = reservations.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseObject.withData(responses));
    }

    @Override
    public ResponseEntity<ResponseObject> getReservationsByUserCpf(String userCpf) {
        List<Reservation> reservations = reservationRepository.findByUserCpf(userCpf);
        List<ReservationResponse> responses = reservations.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseObject.withData(responses));
    }

    @Override
    public ResponseEntity<ResponseObject> getReservationsByGuideCpf(String guideCpf) {
        List<Reservation> reservations = reservationRepository.findByGuideCpf(guideCpf);
        List<ReservationResponse> responses = reservations.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseObject.withData(responses));
    }

    private ReservationResponse mapToResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getGuideCpf(),
                reservation.getUserCpf(),
                reservation.getReservationDate(),
                reservation.getTourName(),
                reservation.getStatus(),
                reservation.getCreatedAt(),
                reservation.getUpdatedAt()
        );
    }
}
