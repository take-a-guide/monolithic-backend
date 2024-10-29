package br.com.takeaguide.takeaguide.adapters.controllers;

import br.com.takeaguide.takeaguide.domain.services.GuideService;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.guide.GuideRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/take_a_guide/guides")
@Tag(name = "APIs-TAKE-A-GUIDE: GUIDE", description = "CONTAINS ALL GUIDE-RELATED ENDPOINTS")
public class GuideController {

    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @PostMapping
    @Operation(summary = "API USED TO CREATE A NEW GUIDE")
    @ApiResponse(responseCode = "201", description = "GUIDE CREATED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    @ApiResponse(responseCode = "409", description = "SOME OF THE ITEMS IN THE REQUEST HAVE CONFLICTS", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    @ApiResponse(responseCode = "400", description = "SOME OF THE ITEMS IN THE REQUEST ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    @ApiResponse(responseCode = "500", description = "SOME PROBLEM OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    public ResponseEntity<ResponseObject> createGuide(@RequestBody GuideRequest guideRequest) {
        return guideService.createGuide(guideRequest);
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "API USED TO GET A GUIDE BY CPF")
    @ApiResponse(responseCode = "200", description = "GUIDE RETRIEVED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    public ResponseEntity<ResponseObject> getGuideByCpf(@PathVariable String cpf) {
        return guideService.getGuideByCpf(cpf);
    }

    @GetMapping
    @Operation(summary = "API USED TO GET ALL GUIDES")
    @ApiResponse(responseCode = "200", description = "GUIDES RETRIEVED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    public ResponseEntity<ResponseObject> getAllGuides() {
        return guideService.getAllGuides();
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "API USED TO UPDATE A GUIDE")
    @ApiResponse(responseCode = "200", description = "GUIDE UPDATED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    public ResponseEntity<ResponseObject> updateGuide(@PathVariable String cpf, @RequestBody GuideRequest guideRequest) {
        return guideService.updateGuide(cpf, guideRequest);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "API USED TO DELETE A GUIDE")
    @ApiResponse(responseCode = "200", description = "GUIDE DELETED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    public ResponseEntity<ResponseObject> deleteGuide(@PathVariable String cpf) {
        return guideService.deleteGuide(cpf);
    }
}
