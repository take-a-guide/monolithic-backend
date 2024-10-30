package br.com.takeaguide.guidelistingservice.application.controllers;

import br.com.takeaguide.guidelistingservice.application.services.GuideListingServiceImpl;

import br.com.takeaguide.guidelistingservice.domain.services.GuideListingService;
import br.com.takeaguide.guidelistingservice.dtos.ResponseObject;
import br.com.takeaguide.guidelistingservice.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/take_a_guide/guide_listing")
@Tag(name = "APIs-TAKE-A-GUIDE: GUIDE LISTING", description = "CONTAINS ALL GUIDE LISTING-RELATED ENDPOINTS")
public class GuideListingController {

    private final GuideListingService guideListingService;

    public GuideListingController(GuideListingService guideListingService) {
        this.guideListingService = guideListingService;
    }

    @PostMapping
    @Operation(summary = "API USED TO CREATE A NEW GUIDE LISTING", responses = {
        @ApiResponse(responseCode = "201", description = "GUIDE LISTING CREATED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = CreateGuideListingResponse.class)))
    })
    public ResponseEntity<ResponseObject> createGuideListing(@RequestBody CreateGuideListingRequest request) {
        return guideListingService.createGuideListing(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "API USED TO UPDATE A GUIDE LISTING", responses = {
        @ApiResponse(responseCode = "200", description = "GUIDE LISTING UPDATED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> updateGuideListing(@PathVariable String id, @RequestBody ChangeGuideListingRequest request) {
        return guideListingService.updateGuideListing(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "API USED TO DELETE A GUIDE LISTING", responses = {
        @ApiResponse(responseCode = "200", description = "GUIDE LISTING DELETED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> deleteGuideListing(@PathVariable String id) {
        return guideListingService.deleteGuideListing(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "API USED TO GET A GUIDE LISTING BY ID", responses = {
        @ApiResponse(responseCode = "200", description = "GUIDE LISTING RETRIEVED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> getGuideListingById(@PathVariable String id) {
        return guideListingService.getGuideListingById(id);
    }

    @GetMapping
    @Operation(summary = "API USED TO GET ALL GUIDE LISTINGS", responses = {
        @ApiResponse(responseCode = "200", description = "ALL GUIDE LISTINGS RETRIEVED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> getAllGuideListings() {
        return guideListingService.getAllGuideListings();
    }

    @GetMapping("/search")
    @Operation(summary = "API USED TO GET FILTERED GUIDE LISTINGS", responses = {
        @ApiResponse(responseCode = "200", description = "FILTERED GUIDE LISTINGS RETRIEVED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> getFilteredGuideListings(
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "location", required = false) String location) {
        return guideListingService.getFilteredGuideListings(searchTerm, category, location);
    }
    
    
}
