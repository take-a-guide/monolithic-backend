package br.com.takeaguide.reservationservice.application.controllers;

import br.com.takeaguide.reservationservice.domain.services.ReservationService;
import br.com.takeaguide.reservationservice.dtos.ResponseObject;
import br.com.takeaguide.reservationservice.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
@RequestMapping("/api/v1/take_a_guide/reservation")
@Tag(name = "APIs-TAKE-A-GUIDE: RESERVATION", description = "CONTAINS ALL RESERVATION-RELATED ENDPOINTS")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    @Operation(summary = "API USED TO CREATE A NEW RESERVATION", responses = {
        @ApiResponse(responseCode = "201", description = "RESERVATION CREATED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "400", description = "INVALID REQUEST DATA", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "SERVER ERROR", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> createReservation(@RequestBody CreateReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "API USED TO GET RESERVATION BY ID", responses = {
        @ApiResponse(responseCode = "200", description = "RESERVATION FOUND", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
        @ApiResponse(responseCode = "404", description = "RESERVATION NOT FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "SERVER ERROR", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id);
    }

    @PutMapping("/update-status")
    @Operation(summary = "API USED TO UPDATE RESERVATION STATUS", responses = {
        @ApiResponse(responseCode = "200", description = "RESERVATION STATUS UPDATED", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "404", description = "RESERVATION NOT FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "400", description = "INVALID REQUEST DATA", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "SERVER ERROR", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> updateReservationStatus(@PathVariable String reservationId, @RequestBody ChangeReservationStatusRequest request) {
        return reservationService.changeReservationStatus(reservationId, request);
    }   

    @GetMapping("/user/{userCpf}")
    @Operation(summary = "API USED TO GET RESERVATIONS BY USER CPF", responses = {
        @ApiResponse(responseCode = "200", description = "RESERVATIONS FOUND", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
        @ApiResponse(responseCode = "404", description = "NO RESERVATIONS FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "SERVER ERROR", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> getReservationsByUserCpf(@PathVariable String userCpf) {
        return reservationService.getReservationsByUserCpf(userCpf);
    }

    @GetMapping("/guide/{guideCpf}")
    @Operation(summary = "API USED TO GET RESERVATIONS BY GUIDE CPF", responses = {
        @ApiResponse(responseCode = "200", description = "RESERVATIONS FOUND", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
        @ApiResponse(responseCode = "404", description = "NO RESERVATIONS FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "SERVER ERROR", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> getReservationsByGuideCpf(@PathVariable String guideCpf) {
        return reservationService.getReservationsByGuideCpf(guideCpf);
    }
}
